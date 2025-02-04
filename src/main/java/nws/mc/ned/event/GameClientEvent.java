package nws.mc.ned.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import nws.dev.core.color._ColorSupport;
import nws.dev.core.format._FormatToString;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.config.client.ClientConfig;
import nws.mc.ned.register.data.DataRegister;
import org.joml.Quaternionf;

@EventBusSubscriber(modid = NekoEnd.MOD_ID,bus = EventBusSubscriber.Bus.GAME ,value = Dist.CLIENT)
public class GameClientEvent {
    private static final int FightingStrengthColor = _ColorSupport.HexToColor(ClientConfig.INSTANCE.getDatas().FightingStrengthColor);
    @SubscribeEvent
    public static void onNameTagRender(RenderNameTagEvent event){

        if (ClientConfig.INSTANCE.getDatas().RenderFightingStrength){
            if (event.getEntity() instanceof LivingEntity livingEntity){
                double fs = livingEntity.getData(DataRegister.EASY_ENTITY_DATA).getSynData().getDouble("ned.mob.fighting_strength");
                if (fs > 0) {
                    Minecraft minecraft = Minecraft.getInstance();
                    if (minecraft.player != null && (ClientConfig.INSTANCE.getDatas().FightingStrengthOnlyRenderWithView
                            && MobSkillRender.checkView(minecraft, livingEntity))) {
                        Quaternionf camera = minecraft.getEntityRenderDispatcher().cameraOrientation();
                        float y = livingEntity.getBbHeight() + ClientConfig.INSTANCE.getDatas().FightingStrengthY;
                        PoseStack poseStack = event.getPoseStack();
                        poseStack.pushPose();
                        poseStack.translate(0, y, 0);
                        poseStack.mulPose(camera);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                        poseStack.scale(-0.025F, -0.025F, 0.025F);
                        String t = _FormatToString.formatValue(fs,0);
                        minecraft.font.drawInBatch(t, -(float) minecraft.font.width(t) / 2, 16 - (float) minecraft.font.lineHeight / 2, FightingStrengthColor, false, poseStack.last().pose(), minecraft.renderBuffers().bufferSource(), Font.DisplayMode.NORMAL, 0, event.getPackedLight());
                        poseStack.popPose();
                    }
                }
            }
        }
    }
}
