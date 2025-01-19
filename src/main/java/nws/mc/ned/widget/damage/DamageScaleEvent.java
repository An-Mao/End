package nws.mc.ned.widget.damage;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.NED;
import nws.mc.ned.config.damage_scale.DamageScaleConfig;
import nws.mc.ned.lib.EntityHelper;

@EventBusSubscriber(modid = NED.MOD_ID)
public class DamageScaleEvent {
    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event){
        LivingEntity entity = event.getEntity();
        if (EntityHelper.isServerLevel(entity) && !(entity instanceof ServerPlayer) && DamageScaleConfig.INSTANCE.getDatas().getApplicableTarget() > 0 ){
            /*
            if (DamageScaleConfig.INSTANCE.getDatas().getApplicableTarget() == 1){
                if (!InvasionEvent.isInvasionMob(entity)){
                    return;
                }
            }

             */
            float oldDamage = event.getNewDamage();
            float maxDamage = Math.max(DamageScaleConfig.INSTANCE.getDatas().getMinDamage(),entity.getMaxHealth() * DamageScaleConfig.INSTANCE.getDatas().getScaleWithHealth());
            event.setNewDamage(Math.min(oldDamage, maxDamage));
        }
    }
}
