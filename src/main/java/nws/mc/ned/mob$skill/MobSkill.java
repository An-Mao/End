package nws.mc.ned.mob$skill;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import nws.mc.ned.NED;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public abstract class MobSkill extends MobSkillCDT implements MobSkillInterface, Serializable {
    private final String id;
    private final String fullId;
    protected final ResourceLocation texture ;
    protected MobSkill(String id){
        this.id = id;
        this.fullId = "skill.ned." + id;
        texture = createTexture(id );
    }
    @Override
    public boolean canAdd(String[] skills) {
        return true;
    }
    public String getId() {
        return id;
    }
    public String getFullId(){
        return fullId;
    }

    public Component getName() {
        return Component.translatable(getFullId());
    }
    public ResourceLocation getTexture() {
        return texture;
    }

    public static ResourceLocation createTexture(String id) {
        //ClassLoader classLoader = NED.class.getClassLoader();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("assets/ned/textures/skill/" + id+".png")) {
            if (inputStream == null) {
                return ResourceLocation.fromNamespaceAndPath(NED.MOD_ID, "textures/skill/null_skill.png");
            }
            return ResourceLocation.tryBuild(NED.MOD_ID, "textures/skill/" + id+".png");
        } catch (IOException e) {
            return ResourceLocation.fromNamespaceAndPath(NED.MOD_ID, "textures/skill/null_skill.png");
        }
    }
}
