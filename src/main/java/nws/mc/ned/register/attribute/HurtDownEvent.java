package nws.mc.ned.register.attribute;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.NED;

@EventBusSubscriber(modid = NED.MOD_ID)
public class HurtDownEvent {
    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event){
        if (!event.getEntity().level().isClientSide){
            AttributeInstance a = event.getEntity().getAttribute(AttributeReg.HURT_DOWN);
            if (a != null){
                float v = (float) a.getValue();
                event.setNewDamage(event.getNewDamage() * (1F- v / 100F));
            }
        }
    }
}
