package nws.mc.ned.mob$enchant.fighting_strength;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.EasyEntityData;
import nws.mc.ned.register.net.NetRegister;
import nws.mc.net.core.NetCore;
import nws.mc.net.core.NetPack;

public class FightingStrengthHelper {
    public static void requestData(LivingEntity entity) {
        if (!entity.hasData(DataRegister.EASY_ENTITY_DATA)) {
            entity.setData(DataRegister.EASY_ENTITY_DATA, new EasyEntityData());
            refreshData(entity);
        }
    }
    public static void refreshData(LivingEntity entity) {
        CompoundTag data = new CompoundTag();
        data.putInt("entity", entity.getId());
        NetCore.sendToServer(NetPack.createClientPack(data, NetRegister.EASY_ENTITY_DATA_NET.get()));
    }
    public static void sendData(LivingEntity entity) {
        NetCore.sendToEntity(NetPack.createClientPack(entity.getData(DataRegister.EASY_ENTITY_DATA).getSynData(), NetRegister.EASY_ENTITY_DATA_NET.get()), entity);
    }

}
