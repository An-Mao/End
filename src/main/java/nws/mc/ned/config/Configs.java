package nws.mc.ned.config;

import com.mojang.logging.LogUtils;
import net.minecraft.core.RegistryAccess;
import nws.mc.ned.data$pack.DataPackReg;
import nws.mc.ned.data$pack.MobSkillConfigDataPack;
import org.slf4j.Logger;

public class Configs {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static MobSkillConfigDataPack MOB_SKILL_CONFIG = null;


    public static MobSkillConfigDataPack mobSkill(){
        return MOB_SKILL_CONFIG;
    }

    public static void loadDataPack(RegistryAccess registryAccess) {
        MOB_SKILL_CONFIG = registryAccess.holderOrThrow(DataPackReg.MOB_SKILL_CONFIG_DATA_PACK_RESOURCE_KEY).value();
        LOGGER.debug("mob skill config {}", MOB_SKILL_CONFIG);
    }
}
