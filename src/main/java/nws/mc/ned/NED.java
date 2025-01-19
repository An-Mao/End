package nws.mc.ned;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import nws.dev.core.system._File;
import nws.mc.ned.register.attribute.AttributeReg;
import nws.mc.ned.register.effect.EffectRegister;
import nws.mc.ned.mob$skill.MobSkillRegister;
import nws.mc.ned.register.item.ItemReg;
import nws.mc.ned.register.net.NetRegister;
import nws.mc.ned.register.DataRegister;

@Mod(NED.MOD_ID)
public class NED
{
    public static final String MOD_ID = "ned";
    public static final String CONFIG_DIR = _File.getFileFullPathWithRun("config/"+ MOD_ID +"/");
    public static final String MOB_SKILL = CONFIG_DIR +"/MobSkill/";

    static {
        _File.checkAndCreateDir(CONFIG_DIR);
        _File.checkAndCreateDir(MOB_SKILL);
    }

    public NED(IEventBus modEventBus, ModContainer modContainer)
    {
        DataRegister.register(modEventBus);
        AttributeReg.register(modEventBus);
        EffectRegister.register(modEventBus);
        //DamageTypeReg.register(modEventBus);
        ItemReg.register(modEventBus);
        NetRegister.reg(modEventBus);
        MobSkillRegister.register(modEventBus);
    }



}
