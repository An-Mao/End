package nws.mc.ned.config.damage_scale;

import com.google.gson.reflect.TypeToken;
import nws.dev.core.json._JsonConfig;
import nws.mc.ned.NekoEnd;

public class DamageScaleConfig extends _JsonConfig<DamageScaleData> {
    public static final DamageScaleConfig INSTANCE = new DamageScaleConfig();
    public DamageScaleConfig() {
        super(NekoEnd.CONFIG_DIR +"DamageScale.json", """
                {
                  "enable": true,
                  "scaleWithHealth": 0.2,
                  "minDamage": 5.0
                }""", new TypeToken<>(){});
    }
}
