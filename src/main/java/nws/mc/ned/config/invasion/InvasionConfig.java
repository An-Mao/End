package nws.mc.ned.config.invasion;

import com.google.gson.reflect.TypeToken;
import nws.dev.core.json._JsonConfig;
import nws.mc.ned.NED;

public class InvasionConfig extends _JsonConfig<InvasionConfigData> {
    public static final InvasionConfig INSTANCE = new InvasionConfig();
    public InvasionConfig() {
        super(NED.CONFIG_DIR + "Invasion.json", """
                {
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
}
