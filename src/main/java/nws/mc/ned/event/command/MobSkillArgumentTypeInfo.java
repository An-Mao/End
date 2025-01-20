package nws.mc.ned.event.command;

import com.google.gson.JsonObject;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.network.FriendlyByteBuf;

public class MobSkillArgumentTypeInfo implements ArgumentTypeInfo<MobSkillArgument, MobSkillArgumentTypeInfo.Template> {

    @Override
    public Template deserializeFromNetwork(FriendlyByteBuf buffer) {
        return new Template();
    }

    @Override
    public void serializeToJson(Template pTemplate, JsonObject pJson) {

    }

    @Override
    public void serializeToNetwork(Template pTemplate, FriendlyByteBuf pBuffer) {

    }

    @Override
    public Template unpack(MobSkillArgument argument) {
        return new Template();
    }

    public final class Template implements ArgumentTypeInfo.Template<MobSkillArgument> {

        @Override
        public MobSkillArgument instantiate(CommandBuildContext pContext) {
            return new MobSkillArgument(pContext);
        }

        @Override
        public ArgumentTypeInfo<MobSkillArgument, ?> type() {
            return  MobSkillArgumentTypeInfo.this;
        }

    }
}
