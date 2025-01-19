package nws.mc.ned.config.client;

import com.google.gson.reflect.TypeToken;
import nws.dev.core.json._JsonConfig;
import nws.mc.ned.NED;

public class ClientConfig extends _JsonConfig<ClientData> {
    public static final String File = NED.CONFIG_DIR + "Client.json";
    public static final ClientConfig INSTANCE = new ClientConfig();
    public ClientConfig() {
        super(File, """
                {
                   "MobSkillRenderType": 2,
                   "MobSkillRenderRotationAngle": 10000,
                   "MobSkillOnlyRenderWithView": true,
                   "MobSkillRenderRadius": 70,
                   "MobSkillRenderOffsetX": -16,
                   "MobSkillRenderOffsetY": 0,
                   "MobSkillRenderOffsetZ": 0
                }""", new TypeToken<>(){});
    }
}
