package nws.mc.ned.config.invasion;

import com.google.gson.reflect.TypeToken;
import nws.dev.core.json._JsonConfig;
import nws.mc.ned.NekoEnd;

import java.util.HashMap;

public class InvasionConfig extends _JsonConfig<InvasionConfigData> {
    public static final InvasionConfig INSTANCE = new InvasionConfig();
    public InvasionConfig() {
        super(NekoEnd.CONFIG_DIR + "Invasion.json", """
                {
                  "enable": true,
                  "immunityNonPlayerDamage": true,
                  "MinDayInterval": 3,
                  "MaxDayInterval": 7,
                  "Probability": 0.2,
                  "Duration": 10000,
                  "DayTime": -1,
                  "Waves": 10,
                  "MobSingleLimit": 35
                }""", new TypeToken<>(){});
    }

    @Override
    public InvasionConfigData getDatas() {
        if (datas == null) datas = new InvasionConfigData();
        return super.getDatas();
    }

    public static boolean isEnable(){
        return INSTANCE.getDatas().isEnable();
    }
    public static boolean isImmunityNonPlayerDamage(){
        return INSTANCE.getDatas().isImmunityNonPlayerDamage();
    }

    public static HashMap<String,Integer> getData() {
        return INSTANCE.getDatas().getData();
    }

    public static void saveData(HashMap<String,Integer> tag) {
        INSTANCE.getDatas().setData(tag);
        INSTANCE.save();
    }
}
