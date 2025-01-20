package nws.mc.ned.config.invasion;

import com.google.gson.reflect.TypeToken;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import nws.dev.core.json._JsonConfig;
import nws.dev.core.math._Math;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.NED;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvasionMobList extends _JsonConfig<Map<String,InvasionMobListData>> {
    public static final InvasionMobList INSTANCE = new InvasionMobList();
    public static final String SAVE_INVASION_KEY = "ned.invasion.is";
    public static final int SPAWN_MIN_RADIUS = 16;
    private static final String DEF = """
            {
              "0":{
                "mid": "minecraft:zombie",
                "min": 10,
                "max": 20,
                "probability": 1.0
              },
              "1":{
                "mid": "minecraft:skeleton",
                "min": 5,
                "max": 10,
                "probability": 1.0
              },
              "2":{
                "mid": "minecraft:slime",
                "min": 1,
                "max": 3,
                "probability": 0.5
              },
              "3":{
                "mid": "minecraft:vex",
                "min": 5,
                "max": 15,
                "probability": 0.7
              },
              "4":{
                "mid": "minecraft:vindicator",
                "min": 3,
                "max": 7,
                "probability": 0.6
              },
              "5":{
                "mid": "minecraft:blaze",
                "min": 3,
                "max": 7,
                "probability": 0.6
              },
              "6":{
                "mid": "minecraft:illusioner",
                "min": 3,
                "max": 7,
                "probability": 0.6
              },
              "7":{
                "mid": "minecraft:ravager",
                "min": 1,
                "max": 3,
                "probability": 0.3
              },
              "8":{
                "mid": "minecraft:piglin_brute",
                "min": 1,
                "max": 3,
                "probability": 0.3
              },
              "9":{
                "mid": "minecraft:phantom",
                "min": 3,
                "max": 7,
                "probability": 0.4
              },
              "10":{
                "mid": "minecraft:pillager",
                "min": 3,
                "max": 7,
                "probability": 0.7
              }
            }""";
    public List<InvasionMobListData> mobList = new ArrayList<>();
    public InvasionMobList(){
        super(NED.CONFIG_DIR + "InvasionMobList.json", DEF,new TypeToken<>(){});
        dataToList();
    }

    public void dataToList(){
        if (!mobList.isEmpty()){
            mobList = new ArrayList<>();
        }
        getDatas().forEach((s, invasionMobListData) -> mobList.add(invasionMobListData));
    }


    public void summonMob(ServerLevel level,double x,double y,double z){
        for (InvasionMobListData monsterData : mobList) {
            if (monsterData.getProbability() > _Math.RD.getRandomDouble()) {
                int count = _Math.RD.getIntRandomNumber(monsterData.getMin(), monsterData.getMax());
                for (int i = 0; i < count; i++) {
                    int radius = SPAWN_MIN_RADIUS + (int) (Math.random() * 17);
                    double angle = Math.random() * 2 * Math.PI;
                    x += Math.cos(angle) * radius;
                    z += Math.sin(angle) * radius;
                    EntityType<?> entityType = EntityHelper.getEntityType(monsterData.getMid());
                    Entity entity = entityType.create(level);
                    if (entity != null) {
                        entity.setPos(x,y,z);
                        entity.getPersistentData().putBoolean(SAVE_INVASION_KEY,true);
                        level.addFreshEntity(entity);
                    }
                }
            }
        }
    }
}
