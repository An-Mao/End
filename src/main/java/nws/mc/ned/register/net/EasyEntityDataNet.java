package nws.mc.ned.register.net;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.EasyEntityData;
import nws.mc.net.core.NetCore;
import nws.mc.net.core.NetHandle;
import nws.mc.net.core.NetPack;

public class EasyEntityDataNet extends NetHandle {
    @Override
    public void client(IPayloadContext context, CompoundTag dat) {
        if (Minecraft.getInstance().level != null) {
            Entity entity = Minecraft.getInstance().level.getEntity(dat.getInt("entity"));
            if (entity != null) {
                entity.getData(DataRegister.EASY_ENTITY_DATA).handlePacket(dat);
            }
        }
        super.client(context, dat);
    }

    @Override
    public void server(IPayloadContext context, CompoundTag dat) {
        Player player = context.player();
        if (player instanceof ServerPlayer sender){
            if (sender.level() instanceof ServerLevel serverLevel) {
                Entity entity =  serverLevel.getEntity(dat.getInt("entity"));
                if (entity != null) {
                    EasyEntityData easyEntityData = entity.getData(DataRegister.EASY_ENTITY_DATA);
                    CompoundTag data = easyEntityData.getSynData();
                    data.putInt("entity", dat.getInt("entity"));
                    NetCore.sendToPlayer(NetPack.createClientPack(data, NetRegister.EASY_ENTITY_DATA_NET.get()),sender);
                }
            }
        }
        super.server(context, dat);
    }
}
