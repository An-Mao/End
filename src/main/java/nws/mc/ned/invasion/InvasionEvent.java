package nws.mc.ned.invasion;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import nws.mc.ned.NED;
import nws.mc.ned.config.invasion.InvasionConfig;
import nws.mc.ned.config.invasion.InvasionMobList;
import nws.mc.ned.register.DataRegister;

@EventBusSubscriber(modid = NED.MOD_ID)
public class InvasionEvent extends InvasionCDT{
    private static final ResourceLocation invasionRes = ResourceLocation.tryBuild(NED.MOD_ID,"invasion");

/*
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Level> event) {
        if (event.getObject() instanceof Level) {
            if (!event.getObject().getCapability(InvasionProvider.levelInvasion).isPresent()) {
                event.addCapability(invasionRes,new InvasionProvider());
            }
        }
    }


 */
    @SubscribeEvent
    public static void onTick(LevelTickEvent.Pre event) {
        Level level = event.getLevel();
        if (!level.isClientSide) {
            // && level.dimension().location().toString().equals("minecraft:overworld")
            level.getData(DataRegister.INVASIONS).tick(level);
        }

    }

    @SubscribeEvent
    public static void onSpawn(FinalizeSpawnEvent event){
        Mob mob = event.getEntity();
        if (!mob.level().isClientSide) {
            if (isInvasionMob(mob)) {
                mob.addEffect(InvasionCDT.SLOW_FALLING);
                mob.addEffect(InvasionCDT.WATER_BREATHING);
                mob.addEffect(InvasionCDT.FIRE_RESISTANCE);
            }
        }
    }

    @SubscribeEvent
    public static void onDamage(EntityInvulnerabilityCheckEvent event){
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            //LivingEntity livingEntity = event.getEntity();
            if (!livingEntity.level().isClientSide && isInvasionMob(livingEntity) && InvasionConfig.INSTANCE.getDatas().isImmunityNonPlayerDamage()) {
                if (!(event.getSource().getEntity() instanceof Player)) {
                    event.setInvulnerable(true);
                }
            }
        }
    }

    public static boolean isInvasionMob(LivingEntity livingEntity){
        return livingEntity.getPersistentData().getBoolean(InvasionMobList.SAVE_INVASION_KEY);
    }
}
