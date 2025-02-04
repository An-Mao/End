package nws.mc.ned.config.client;

import com.google.gson.reflect.TypeToken;
import nws.dev.core.json._JsonConfig;
import nws.mc.ned.NekoEnd;

public class ClientConfig extends _JsonConfig<ClientData> {
    public static final String File = NekoEnd.CONFIG_DIR + "Client.json";
    public static final ClientConfig INSTANCE = new ClientConfig();
    public ClientConfig() {
        super(File, """
                {
                   "RenderFightingStrength": true,
                   "FightingStrengthColor": "0x00ff00",
                   "FightingStrengthOnlyRenderWithView": true,
                   "FightingStrengthY": 0.7,
                   "MobSkillRenderType": 2,
                   "MobSkillRenderRotationAngle": 10000,
                   "MobSkillOnlyRenderWithView": true,
                   "MobSkillTextRenderColor": "0xFF0000",
                   "MobSkillRenderRadius": 66,
                   "MobSkillRenderOffsetX": -16,
                   "MobSkillRenderOffsetY": 0,
                   "MobSkillRenderOffsetZ": 0
                }""", new TypeToken<>(){});
    }
}
