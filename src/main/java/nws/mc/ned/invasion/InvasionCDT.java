package nws.mc.ned.invasion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class InvasionCDT {
    public static final String SAVE_KEY = "ned.invasion";
    public static final int TYPE_STOP = -1;
    public static final int TYPE_PRE = 0;
    public static final int TYPE_START = 1;

    public static final String MSG_START = "message.ned.invasion.start";
    public static final String MSG_PRE = "message.ned.invasion.pre";
    public static final String MSG_WAVES = "message.ned.invasion.wave";
    public static final String MSG_STOP = "message.ned.invasion.stop";
    public static final String MSG_STOP_ERROR = "message.ned.invasion.error_stop";


    public static final MobEffectInstance SLOW_FALLING = new MobEffectInstance(MobEffects.SLOW_FALLING,Integer.MAX_VALUE,1);
    public static final MobEffectInstance WATER_BREATHING = new MobEffectInstance(MobEffects.WATER_BREATHING,Integer.MAX_VALUE,1);
    public static final MobEffectInstance FIRE_RESISTANCE = new MobEffectInstance(MobEffects.FIRE_RESISTANCE,Integer.MAX_VALUE,1);
}
