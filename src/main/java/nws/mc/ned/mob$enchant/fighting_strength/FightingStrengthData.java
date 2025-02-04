package nws.mc.ned.mob$enchant.fighting_strength;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.register.data.DataRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public record FightingStrengthData(
        boolean enable, double min, double max, double health, double attackDamage, double attackSpeed, double moveSpeed, double timeMin, double timeMax, double distanceMin, double distanceMax, boolean onlyWhitelist, HashMap<String,Double> dimensionRatio,List<String> whitelist, List<String> blacklist) {
    public static final Random random = new Random();
    public static final ResourceLocation FS_AttackDamage = ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID,"fighting_strength.ned.attack_damage.add");
    public static final ResourceLocation FS_AttackSpeed = ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID,"fighting_strength.ned.attack_speed.add");
    public static final ResourceLocation FS_MoveSpeed = ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID,"fighting_strength.ned.move_speed.add");
    public static final ResourceLocation FS_MaxHealth = ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID,"fighting_strength.ned.max_health.add");

    public FightingStrengthData(boolean enable,double min, double max,  double health,double attackDamage, double attackSpeed, double moveSpeed, double timeMin, double timeMax, double distanceMin, double distanceMax,boolean onlyWhitelist,HashMap<String,Double> dimensionRatio){
        this(enable, min,  max,health,  attackDamage,  attackSpeed,  moveSpeed,  timeMin,  timeMax,  distanceMin,  distanceMax,onlyWhitelist,dimensionRatio,new ArrayList<>(),new ArrayList<>());
    }
    public void addFightingStrength(LivingEntity entity){
        if (enable) {
            String regKey = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();
            if (blacklist.contains(regKey)) return;
            if (onlyWhitelist && !whitelist.contains(regKey)) return;
            if (entity.getData(DataRegister.EASY_ENTITY_DATA).getSynData().getDouble("ned.mob.fighting_strength")!= 0)return;
            double fs = getRandomFightingStrength(entity);
            fs *= dimensionRatio.getOrDefault(entity.level().dimensionTypeRegistration().getRegisteredName(),1d);
            entity.getData(DataRegister.EASY_ENTITY_DATA).getSynData().putDouble("ned.mob.fighting_strength",fs);
            AttributeHelper.setAttribute(entity,Attributes.MAX_HEALTH,new AttributeModifier(FS_MaxHealth,fs*health, AttributeModifier.Operation.ADD_VALUE));
            AttributeHelper.setAttribute(entity,Attributes.ATTACK_DAMAGE,new AttributeModifier(FS_AttackDamage,fs*attackDamage, AttributeModifier.Operation.ADD_VALUE));
            AttributeHelper.setAttribute(entity,Attributes.ATTACK_SPEED,new AttributeModifier(FS_AttackSpeed,fs*attackSpeed, AttributeModifier.Operation.ADD_VALUE));
            AttributeHelper.setAttribute(entity,Attributes.MOVEMENT_SPEED,new AttributeModifier(FS_MoveSpeed,fs*moveSpeed, AttributeModifier.Operation.ADD_VALUE));
            FightingStrengthHelper.sendData(entity);
        }
    }

    public double getRandomFightingStrength(LivingEntity livingEntity){
        double fs = getRandomDouble(min,max);
        fs += getRandomDouble(timeMin,timeMax) * (livingEntity.level().getGameTime() / 24000d);
        fs += getRandomDouble(distanceMin,distanceMax) * calculateDistance2D(livingEntity.getX(),livingEntity.getZ());
        return fs;
    }
    public double getRandomDouble(double min,double max){
        return min + (max - min) * random.nextDouble();
    }
    public static double calculateDistance2D(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }
}
