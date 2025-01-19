package nws.mc.ned.lib;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;

import java.util.List;
import java.util.Set;

public class EntityHelper {
    public static Level getEntityLevel(Entity entity){
        return entity.level();
    }
    public static boolean isServerLevel(Entity entity){
        return !getEntityLevel(entity).isClientSide;
    }
    public static Set<String> getLivingEntityTag(LivingEntity entity){
        return entity.getTags();
    }

    public static EntityType<?> getEntityType(String id){
        //return ForgeRegistries.ENTITY_TYPES.getValue(new net.minecraft.resources.ResourceLocation(registryId));
        return BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse(id));
    }
    public static List<? extends LivingEntity> getLivingEntities(LivingEntity livingEntity){
        return getLivingEntities(livingEntity,10);
    }
    public static List<? extends LivingEntity> getLivingEntities(LivingEntity livingEntity, int radius){
        return getEntityLevel(livingEntity).getEntities(EntityTypeTest.forClass(livingEntity.getClass()), livingEntity.getBoundingBox().inflate(radius), Entity::isAlive);
    }
}
