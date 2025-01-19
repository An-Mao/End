package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class PoisonedEggMobSkill extends MobSkill {
    public PoisonedEggMobSkill(String id) {
        super(id);
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 200) {
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                //LivingEntity livingEntity = tickEvent.getEntity();
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 10);
                if (player != null) {
                    ItemStack potionStack = PotionContents.createItemStack(Items.SPLASH_POTION, Potions.POISON);
                    //PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.POISON);
                    //PotionUtils.setCustomEffects(potionStack, PotionUtils.getCustomEffects(potionStack));
                    //potionStack.getOrCreateTag().putInt("CustomPotionColor", 0x4E9331);


                    Vec3 witchPos = livingEntity.position();
                    Vec3 targetPos = player.position();
                    double distance = targetPos.distanceTo(witchPos);

                    double x = targetPos.x - witchPos.x;
                    double y = targetPos.y - witchPos.y;
                    double z = targetPos.z - witchPos.z;

                    ThrownPotion potionEntity = new ThrownPotion(player.level(), livingEntity);
                    potionEntity.setItem(potionStack);
                    potionEntity.shoot(x, y + distance * 0.2, z, 0.75F, 8.0F);
                    player.level().addFreshEntity(potionEntity);
                }
            }
        }else {
            dat.putInt("tick",t + 1);
        }
    }

}
