package nws.mc.ned.register.attribute;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class Hurt extends RangedAttribute {
    public static final Attribute UP = new Hurt("attribute.ned.hurt.up").setSyncable(true);
    public static final Attribute DOWN = new Hurt("attribute.ned.hurt.down").setSyncable(true);
    public Hurt(String descriptionId) {
        super(descriptionId, 0, 0, 100);
    }


}
