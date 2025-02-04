package nws.mc.ned.invasion;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import nws.dev.core.math._Math;
import nws.mc.cores.time.TimeHelper;
import nws.mc.ned.ConstantDataTable;
import nws.mc.ned.config.invasion.InvasionConfig;
import nws.mc.ned.config.invasion.InvasionMobList;
import org.slf4j.Logger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvasionTimer extends InvasionCDT {
    private final Logger log = LogUtils.getLogger();
    private static boolean run = false;
    public static final InvasionTimer I = new InvasionTimer();
    public static final Thread timer = new Thread(() -> {
        int i = 100;
        while (run) {
            try {
                i--;
                I.check();
                if (i < 0){
                    I.saveData();
                    i = 100;
                }
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });

    private long dayTime = -1;
    private int type = TYPE_STOP;//Invasion Type
    private int lastInvasionDay = 0;//Invasion Last Day
    private int startTime = -1;//Invasion Last Start Time
    private int spawnTime = 0;//summon mob time
    private int lastCheckDay = 0;//last check day
    private int duration = 0;//invasion duration time
    private int wave = 0;//invasion wave
    private boolean saving = false;
    private final int singleWavesTime = InvasionConfig.INSTANCE.getDatas().getDuration() / (InvasionConfig.INSTANCE.getDatas().getWaves() + 1);
    private final List<String> msg = new ArrayList<>();
    private boolean canSpawn = false;
    private HashMap<String,Integer> oldData = new HashMap<>();
    public InvasionTimer(){
        loadNBTData(InvasionConfig.getData());
    }
    public void check(){
        if (dayTime < 0 || saving) return;
        int time = (int) dayTime;
        int day = TimeHelper.TickToDay(time);

        if (day < lastInvasionDay) lastInvasionDay = -1;
        if (day < lastCheckDay) lastCheckDay = -1;

        if (type == TYPE_PRE) {
            if (day == lastInvasionDay) {
                if (startTime <= TimeHelper.GetDayTime(time)) {
                    type = TYPE_START;
                    setMsg(MSG_START);
                }
            } else {
                setMsg(MSG_STOP_ERROR);
                type = TYPE_STOP;
            }
        } else if (type == TYPE_START) {
            duration--;
            if (duration <= 0) {
                setMsg(MSG_STOP);
                type = TYPE_STOP;
                return;
            }
            spawnTime--;
            if (spawnTime <= 0) {
                canSpawn = true;
            }
        } else if (type == TYPE_STOP) {
            if (canInvasion(day)) {
                type = TYPE_PRE;
                if (InvasionConfig.INSTANCE.getDatas().getDayTime() == -1) {
                    startTime = _Math.RD.getIntRandomNumber(ConstantDataTable.MinecraftDayMinTick, ConstantDataTable.MinecraftDayMaxTick);
                } else {
                    startTime = InvasionConfig.INSTANCE.getDatas().getDayTime();
                }
                duration = InvasionConfig.INSTANCE.getDatas().getDuration();
                lastInvasionDay = day;
                spawnTime = 0;
                wave = -1;
                setMsg(MSG_PRE, TimeHelper.FormatDate(TimeHelper.GetDayTime(time)), TimeHelper.FormatDate(startTime), TimeHelper.tickToTime(duration), InvasionConfig.INSTANCE.getDatas().getWaves());
            }
        }
    }
    public void setDayTime(long newDayTime) {
        dayTime = newDayTime;
    }



    public void preInvasion(ServerLevel serverLevel){
        type = TYPE_PRE;
        int time = (int) serverLevel.getDayTime();
        int day = TimeHelper.TickToDay(time);

        if (InvasionConfig.INSTANCE.getDatas().getDayTime() == -1) {
            startTime = _Math.RD.getIntRandomNumber(ConstantDataTable.MinecraftDayMinTick, ConstantDataTable.MinecraftDayMaxTick);
        } else {
            startTime = InvasionConfig.INSTANCE.getDatas().getDayTime();
        }
        duration = InvasionConfig.INSTANCE.getDatas().getDuration();
        lastInvasionDay = day;
        spawnTime = 0;
        wave = -1;
        setMsg(MSG_PRE, TimeHelper.FormatDate(TimeHelper.GetDayTime(time)), TimeHelper.FormatDate(startTime), TimeHelper.tickToTime(duration), InvasionConfig.INSTANCE.getDatas().getWaves());
    }
    public void spawn(MinecraftServer server){
        if (canSpawn){
            if (server != null) {
                wave++;
                spawnTime = singleWavesTime;
                if (wave > 0) {
                    setMsg(MSG_WAVES, wave);
                    List<ServerPlayer> players = server.getPlayerList().getPlayers();
                    for (ServerPlayer player : players) {
                        InvasionMobList.INSTANCE.summonMob(player.serverLevel(), player.getX(), player.getY(), player.getZ());
                    }
                }
            }
            canSpawn = false;
        }
    }


    private void setMsg(String msgKey, Object... data){
        String s = Component.translatable(msgKey).getString();
        String ns = MessageFormat.format(s, data);
        msg.clear();
        msg.addAll(List.of(ns.split("/n")));
    }
    public void sendMsg(MinecraftServer server){
        if (server == null) return;
        List<ServerPlayer> players = server.getPlayerList().getPlayers();
        for (ServerPlayer player:players) {
            msg.forEach(s -> player.sendSystemMessage(Component.literal(s)));
        }
        msg.clear();
    }
    private boolean isInvasion(){
        return type != TYPE_STOP;
    }

    public boolean canInvasion(int day){
        if (isInvasion() || day <= lastCheckDay){
            return false;
        }
        lastCheckDay = day;
        if (day - lastInvasionDay >= InvasionConfig.INSTANCE.getDatas().getMaxDayInterval()){
            return true;
        }
        return day > lastInvasionDay && day - lastInvasionDay > InvasionConfig.INSTANCE.getDatas().getMinDayInterval() && _Math.RD.getRandomFloat() < InvasionConfig.INSTANCE.getDatas().getProbability();
    }
    public static void setRun(boolean canRun) {
        if (run && canRun) return;
        run = canRun;
        if (run) timer.start();
    }






    public void saveData()
    {
        saving = true;
        HashMap<String,Integer> tag = new HashMap<>();
        tag.put("type", type);
        tag.put("lastInvasionDay", lastInvasionDay);
        tag.put("startTime", startTime);
        tag.put("spawnTime", spawnTime);
        tag.put("lastCheckDay", lastCheckDay);
        tag.put("duration", duration);
        tag.put("wave", wave);
        if (tag.equals(oldData)) return;
        oldData = tag;
        InvasionConfig.saveData(tag);
        saving = false;
    }
    public void loadNBTData(HashMap<String,Integer> nbt)
    {
        if (nbt == null) return;
        type = nbt.getOrDefault("type",TYPE_STOP);
        lastInvasionDay = nbt.getOrDefault("lastInvasionDay",0);
        startTime = nbt.getOrDefault("startTime",-1);
        spawnTime = nbt.getOrDefault("spawnTime",0);
        lastCheckDay = nbt.getOrDefault("lastCheckDay",0);
        duration = nbt.getOrDefault("duration",0);
        wave = nbt.getOrDefault("wave",0);
    }
}
