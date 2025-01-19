package nws.mc.ned.config.general;

import com.google.gson.reflect.TypeToken;
import nws.dev.core.json._JsonConfig;
import nws.dev.core.math._Math;
import nws.mc.ned.NED;


public class GeneralConfig extends _JsonConfig<GeneralConfigData> {
    public static final GeneralConfig INSTANCE = new GeneralConfig();
    public GeneralConfig() {
        super(NED.CONFIG_DIR + "general.json", """
                {
                  "renderEndEye": false
                }""", new TypeToken<>(){});
    }
}
