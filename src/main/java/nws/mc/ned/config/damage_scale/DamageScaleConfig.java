package nws.mc.ned.config.damage_scale;

import com.google.gson.reflect.TypeToken;
import nws.dev.core.json._JsonConfig;
import nws.mc.ned.NED;

public class DamageScaleConfig extends _JsonConfig<DamageScaleData> {
    public static final DamageScaleConfig INSTANCE = new DamageScaleConfig();
    public DamageScaleConfig() {
        super(NED.CONFIG_DIR +"DamageScale.json", """
                {
                  "ApplicableTarget": 1,
                  "ScaleWithHealth": 0.2,
                  "MinDamage": 5.0
                }""", new TypeToken<>(){});
    }
}
