package nws.mc.ned.config.general;

import com.google.gson.reflect.TypeToken;
import nws.dev.core.json._JsonConfig;
import nws.mc.ned.NekoEnd;


public class GeneralConfig extends _JsonConfig<GeneralConfigData> {
    public static final GeneralConfig INSTANCE = new GeneralConfig();
    public GeneralConfig() {
        super(NekoEnd.CONFIG_DIR + "general.json", """
                {
                  "renderEndEye": false
                }""", new TypeToken<>(){});
    }
}
