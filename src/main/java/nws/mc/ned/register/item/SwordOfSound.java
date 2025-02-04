package nws.mc.ned.register.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import nws.mc.cores.amlib.the$world.TheWorld;
import nws.mc.ned.register.damage$type.DamageTypes;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.ItemExtraData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SwordOfSound extends SwordItem {

    private static final Properties PROPERTIES = new Item.Properties()
            .fireResistant()
            .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 4, 0F));

    public SwordOfSound() {
        super(Tiers.NETHERITE, PROPERTIES);
    }



    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        if (player instanceof ServerPlayer serverPlayer && entity instanceof LivingEntity livingEntity) {
            ItemExtraData itemExtraData = stack.get(DataRegister.Item_Extra_Data);

            int sound = 0;
            if (itemExtraData != null) {
                sound = itemExtraData.get("sound").getInt("index");
            }else {
                itemExtraData = new ItemExtraData(new CompoundTag());
            }
            ResourceKey<DamageType> damageType = switch (sound) {
                case 0 -> DamageTypes.GONG;
                case 1 -> DamageTypes.SHANG;
                case 2 -> DamageTypes.JUE;
                case 3 -> DamageTypes.ZHI;
                case 4 -> DamageTypes.YU;
                default -> null;
            };
            if (damageType != null) {
                ItemAttributeModifiers damageAttributeModifiers = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
                if (damageAttributeModifiers != null) {
                    float damage = (float) (serverPlayer.getAttributeValue(Attributes.ATTACK_DAMAGE)+ (sound + 3));
                            //(serverPlayer.getLuck()+1) * (sound + 3);
                    //System.out.println("damage: " + damage);
                    damage *= (serverPlayer.getLuck()+1);
                    //damageAttributeModifiers.compute(serverPlayer.getLuck() * sound, EquipmentSlot.MAINHAND);
                    if (sound != 0) livingEntity.hurt(serverPlayer.damageSources().source(damageType, serverPlayer), damage);
                    switch (sound) {
                        case 0 -> {
                            damage += livingEntity.getMaxHealth() * 0.1f;
                            livingEntity.hurt(serverPlayer.damageSources().source(damageType, serverPlayer), (float) damage);
                        }
                        case 1 -> {
                            livingEntity.setRemainingFireTicks(livingEntity.getRemainingFireTicks()+200);
                            /*
                            PrimedTnt tntEntity = new PrimedTnt(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), livingEntity);
                            tntEntity.setFuse(20);
                            livingEntity.level().addFreshEntity(tntEntity);

                             */

                        }
                        case 2 -> {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 9));
                        }
                        case 3 -> {
                            serverPlayer.heal(damage);
                        }
                        case 4 -> {
                            TheWorld.SetTheWorldTime(livingEntity,500);
                            TheWorld.SetTheWorldState(livingEntity, true);
                        }
                    }
                    sound++;
                    if (sound > 4) sound = 0;
                    CompoundTag soundTag = new CompoundTag();
                    soundTag.putInt("index", sound);
                    itemExtraData.put("sound", soundTag);
                    stack.set(DataRegister.Item_Extra_Data, itemExtraData);
                    //serverPlayer.attack(livingEntity);
                }
            }
        }
        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("tooltip.ned.quality").append(":").withStyle(ChatFormatting.DARK_AQUA).append(Component.translatable("tooltip.ned.quality.apocalypse_flame").withColor(0xFFFF00)));
        /*
        qualityText.setStyle(Style.EMPTY.withColor(0xFFFFFF)); // 白色
        qualityText.setStyle(Style.EMPTY.withColor(0x00FF00)); // 绿色
        qualityText.setStyle(Style.EMPTY.withColor(0x0000FF)); // 蓝色
        qualityText.setStyle(Style.EMPTY.withColor(0x00FFFF)); // 青色
        qualityText.setStyle(Style.EMPTY.withColor(0x800080)); // 紫色
        qualityText.setStyle(Style.EMPTY.withColor(0xFFA500)); // 橙色
        qualityText.setStyle(Style.EMPTY.withColor(0xFF0000)); // 红色
        qualityText.setStyle(Style.EMPTY.withColor(0xFFD700)); // 金色
        qualityText.setStyle(Style.EMPTY.withColor(0xFF1493)); // 彩虹
        qualityText.setStyle(Style.EMPTY.withColor(0xFFFF00)); // 闪耀

         */
        ItemExtraData itemExtraData = pStack.getOrDefault(DataRegister.Item_Extra_Data,ItemExtraData.EMPTY);
        int sound = itemExtraData.get("sound").getInt("index");
        pTooltipComponents.add(Component.translatable("tooltip.ned.sword_of_sound.damage_type").append(Component.translatable(getName(sound))).withStyle(ChatFormatting.GREEN));

        pTooltipComponents.add(Component.translatable("tooltip.ned.sword_of_sound").withStyle(ChatFormatting.DARK_PURPLE));
        //System.out.println("sound::"+sound);


    }

    @Override
    public int getEnchantmentValue() {
        return 99;
    }

    public String getName(int sound) {
        return switch (sound) {
            case 0 -> "damage_type.ned.gong"; //"攻击";
            case 1 -> "damage_type.ned.shang";//"爆炸";
            case 2 -> "damage_type.ned.jue"; //"冰冻";
            case 3 -> "damage_type.ned.zhi"; //"治疗";
            case 4 -> "damage_type.ned.yu"; //"减速";
            default -> "damage_type.ned.null"; //"异常";
        };
    }
}
