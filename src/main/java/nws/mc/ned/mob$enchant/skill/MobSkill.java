package nws.mc.ned.mob$enchant.skill;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import nws.mc.ned.NekoEnd;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public abstract class MobSkill implements MobSkillInterface, Serializable {
    private final String id;
    private final String fullId;
    protected final ResourceLocation texture ;
    private final HashMap<String,ResourceLocation> attritubes = new HashMap<>();
    protected MobSkill(String id){
        this.id = id;
        this.fullId = "skill."+ NekoEnd.MOD_ID+"." + id;
        texture = createTexture(id );
    }
    @Override
    public boolean canAdd(List<MobSkill> skills) {
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
                return ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, "textures/skill/null_skill.png");
            }
            return ResourceLocation.tryBuild(NekoEnd.MOD_ID, "textures/skill/" + id+".png");
        } catch (IOException e) {
            return ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, "textures/skill/null_skill.png");
        }
    }

    public void loadDataPack(CompoundTag dat){
        if (dat == null || dat.isEmpty()) return;
        loadConfig(dat);
    }
    public void loadConfig(CompoundTag dat){}
    public CompoundTag getDefaultConfig(){
        return new CompoundTag();
    }

    public ResourceLocation getAttributeKey(String type){
        if (!attritubes.containsKey(type)) attritubes.put(type,ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID,"skill."+id+"."+type));
        return attritubes.get(type);
    }
}
