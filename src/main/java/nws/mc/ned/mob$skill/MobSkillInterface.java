package nws.mc.ned.mob$skill;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

interface MobSkillInterface {
     boolean canAdd(String[] skills);

     default void mobSpawn(FinalizeSpawnEvent event, CompoundTag dat){}
     default void entityJoinLevel(EntityJoinLevelEvent event, CompoundTag dat){}
     default void entityInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event, CompoundTag dat){}
     /**
     * LivingAttackEvent
     * @param event
     */
     default void livingIncomingDamage(LivingIncomingDamageEvent event, CompoundTag dat){}
     /**
     * LivingDamageEvent
     * @param event
     */
     default void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat){}
     /**
     * LivingHurtEvent
     * @param event
     */
     default void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat){}
     default void livingDeath(LivingDeathEvent event, CompoundTag dat){}
     default void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat){}
}
