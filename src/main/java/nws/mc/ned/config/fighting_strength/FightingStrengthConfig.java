package nws.mc.ned.config.fighting_strength;

import com.google.gson.reflect.TypeToken;
import net.minecraft.world.entity.LivingEntity;
import nws.dev.core.json._JsonConfig;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.mob$enchant.fighting_strength.FightingStrengthData;

public class FightingStrengthConfig extends _JsonConfig<FightingStrengthData> {
    public static final FightingStrengthConfig I = new FightingStrengthConfig();
    public FightingStrengthConfig() {
        super(NekoEnd.CONFIG_DIR + "FightingStrength.json", """
                {
                  "enable": true,
                  "min": 5,
                  "max": 20,
                  "health": 0.1,
                  "attackDamage": 0.05,
                  "attackSpeed": 0.01,
                  "moveSpeed": 0.0002,
                  "timeMin": 0.01,
                  "timeMax": 0.02,
                  "distanceMin": 0.2,
                  "distanceMax": 0.5,
                  "onlyWhitelist": false,
                  "dimensionRatio":{
                    "minecraft:overworld":1,
                    "minecraft:the_nether":1.5,
                    "minecraft:the_end":2
                  },
                  "whitelist": [],
                  "blacklist": []
                }""", new TypeToken<>(){});
    }
    public static void addFightingStrength(LivingEntity entity){
        I.getDatas().addFightingStrength(entity);
    }
}
