package nws.mc.ned.register.net;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.MobSkillData;
import nws.mc.net.core.NetCore;
import nws.mc.net.core.NetHandle;
import nws.mc.net.core.NetPack;

public class MobSkillNet extends NetHandle {

    @Override
    public void client(IPayloadContext context, CompoundTag dat) {
        if (Minecraft.getInstance().level != null) {
            Entity entity = Minecraft.getInstance().level.getEntity(dat.getInt("entity"));
            if (entity != null) {
                //System.out.println(dat);
                entity.getData(DataRegister.MOB_SKILLS).handlePacket(dat);
            }
        }
        super.client(context, dat);
    }

    @Override
    public void server(IPayloadContext context, CompoundTag dat) {
        Player player = context.player();
        if (player instanceof ServerPlayer sender){
            if (sender.level() instanceof ServerLevel serverLevel) {
                Entity blockEntity =  serverLevel.getEntity(dat.getInt("entity"));
                if (blockEntity != null) {
                    MobSkillData mobSkillData = blockEntity.getData(DataRegister.MOB_SKILLS);
                    CompoundTag data = mobSkillData.getCompoundTag();
                    data.putInt("entity", dat.getInt("entity"));
                    NetCore.sendToPlayer(NetPack.createClientPack(data, NetRegister.MOB_SKILL.get()),sender);
                }
            }
        }
        super.server(context, dat);
    }


}
