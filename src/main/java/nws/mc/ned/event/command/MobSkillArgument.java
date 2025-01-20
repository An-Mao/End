package nws.mc.ned.event.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import nws.mc.ned.NED;
import nws.mc.ned.mob$skill.MobSkill;
import nws.mc.ned.mob$skill.MobSkillHelper;
import nws.mc.ned.mob$skill.MobSkillRegister;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class MobSkillArgument implements ArgumentType<MobSkillInput> {
    private static final Collection<String> EXAMPLES = Arrays.asList("skill", "modid:skill");
    private static final DynamicCommandExceptionType ERROR_INVALID_SKILL = new DynamicCommandExceptionType(
            (name) -> Component.literal("Unknown skill: " + name)
    );
    private final Parser parser;

    public MobSkillArgument(CommandBuildContext pContext) {
        this.parser = new Parser(pContext);
    }
    @Override
    public MobSkillInput parse(StringReader reader) throws CommandSyntaxException {
        StringBuilder sb = new StringBuilder();
        while (reader.canRead() && !Character.isWhitespace(reader.peek())) {
            sb.append(reader.read());
        }
        String key = sb.toString();//reader.getRemaining();
        reader.skip();
        ResourceLocation res = ResourceLocation.tryParse(key);
        if (res == null) throw ERROR_INVALID_SKILL.create(key);
        return new MobSkillInput(this.parser.parse(res));
    }
    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String in = context.getInput().replace("/"+NED.MOD_ID+" addSkill ","");
        MobSkillHelper.getSkills(builder,in);
        return builder.buildFuture();
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    public static MobSkillArgument mobSkill(CommandBuildContext pContext) {
        return new MobSkillArgument(pContext);
    }
    static class Parser{
        final HolderLookup.RegistryLookup<MobSkill> skills;
        public Parser(HolderLookup.Provider provider){
            skills = provider.lookupOrThrow(MobSkillRegister.REGISTRY_KEY);
        }
        public Holder<MobSkill> parse(ResourceLocation reader){
            return skills.getOrThrow(ResourceKey.create(MobSkillRegister.REGISTRY_KEY, reader));
        }

    }
    public static <S> MobSkillInput getMobSkill(CommandContext<S> pContext, String pName) {
        return pContext.getArgument(pName, MobSkillInput.class);
    }
}
