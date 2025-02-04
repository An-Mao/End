package nws.mc.ned.register.net;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nws.mc.ned.NekoEnd;
import nws.mc.net.core.NetHandle;
import nws.mc.net.core.NetReg;

public class NetRegister {
    public static final DeferredRegister<NetHandle> EASY_NET = DeferredRegister.create(NetReg.KEY, NekoEnd.MOD_ID);
    public static final DeferredHolder<NetHandle, MobSkillNet> MOB_SKILL = EASY_NET.register("mob_skill",MobSkillNet::new);
    public static final DeferredHolder<NetHandle, EasyEntityDataNet> EASY_ENTITY_DATA_NET = EASY_NET.register("easy_entity_data",EasyEntityDataNet::new);
    public static void reg(IEventBus eventBus){
        EASY_NET.register(eventBus);
    }
}
