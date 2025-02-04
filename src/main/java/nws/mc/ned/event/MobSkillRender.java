package nws.mc.ned.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import nws.dev.core.color._ColorSupport;
import nws.dev.core.math._Math;
import nws.dev.core.math._MathCDT;
import nws.mc.cores.render.DrawImage;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.config.client.ClientConfig;
import nws.mc.ned.mob$enchant.skill.MobSkill;
import nws.mc.ned.register.data.DataRegister;
import org.joml.Quaternionf;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = NekoEnd.MOD_ID, value = Dist.CLIENT)
public class MobSkillRender {
    private static double rotationAngle = 0;
    //private static final double ra = Math.PI / ClientConfig.getSkillRenderRotationAngle() ;
    private static final int textColor = _ColorSupport.HexToColor(ClientConfig.INSTANCE.getDatas().MobSkillTextRenderColor);
    public static double getAngle(){
        if (ClientConfig.INSTANCE.getDatas().MobSkillRenderType() == 0){
            return 0;
        }
        rotationAngle += Math.PI / ClientConfig.INSTANCE.getDatas().MobSkillRenderRotationAngle() ;
        if (rotationAngle >= _MathCDT.TWICE_PI) {
            rotationAngle = 0.0;
        }
        return rotationAngle;
    }

    public static boolean checkView(Minecraft minecraft, LivingEntity entity){
        if (minecraft.player != null) {
            return ClientHooks.isNameplateInRenderDistance(entity,minecraft.getEntityRenderDispatcher().distanceToSqr(entity)) && minecraft.player.hasLineOfSight(entity) && entity == minecraft.getEntityRenderDispatcher().crosshairPickEntity;
        }
        return false;
    }
    @SubscribeEvent
    public static void onRender(RenderNameTagEvent event){
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            if (ClientConfig.INSTANCE.getDatas().MobSkillRenderType() == 0) return;
            Minecraft MC = Minecraft.getInstance();
            if (ClientConfig.INSTANCE.getDatas().MobSkillOnlyRenderWithView() && !checkView(MC, livingEntity)) return;
            if (!livingEntity.hasData(DataRegister.MOB_SKILLS)) return;
            List<MobSkill> skills = livingEntity.getData(DataRegister.MOB_SKILLS).getAllSkill();
            if (skills.isEmpty()) return;
            PoseStack poseStack = event.getPoseStack();
            float lY = livingEntity.getBbHeight() / 2 + 0.16f;
            poseStack.pushPose();
            poseStack.translate(0.0, lY, 0.0);
            Quaternionf camera = MC.getEntityRenderDispatcher().cameraOrientation();
            camera.x = 0;
            camera.z = 0;
            poseStack.mulPose(camera);
            poseStack.mulPose(Axis.YP.rotationDegrees(180));
            poseStack.scale(-0.025F, -0.025F, 0.025F);
            draw(MC, livingEntity, poseStack, skills, MC.font, event.getPackedLight());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
        }

    }
    private static void draw(Minecraft MC, LivingEntity entity, PoseStack poseStack,List<MobSkill> skills,Font font,int packedLight){
        if (skills.isEmpty()) return;
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        int i = 0;
        ArrayList<Point2D.Double> pointPoss = _Math.distributePoints(entity.getBbWidth() * ClientConfig.INSTANCE.getDatas().MobSkillRenderRadius(), skills.size(),getAngle());
        for (MobSkill id : skills){
            Point2D.Double point = pointPoss.get(i);
            poseStack.pushPose();
            poseStack.translate(point.x,0,point.y);
            poseStack.translate(ClientConfig.INSTANCE.getDatas().MobSkillRenderOffsetX(),ClientConfig.INSTANCE.getDatas().MobSkillRenderOffsetY(),ClientConfig.INSTANCE.getDatas().MobSkillRenderOffsetZ());
            if (ClientConfig.INSTANCE.getDatas().MobSkillRenderType() == 1) {
                ResourceLocation icon = id.getTexture();
                RenderSystem.setShaderTexture(0, icon);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                DrawImage.blit(poseStack, icon, 0, 0, 0, 0, 0, 16, 16, 16, 16);
            }else if (ClientConfig.INSTANCE.getDatas().MobSkillRenderType() == 2){
                MultiBufferSource.BufferSource bufferSource = MC.renderBuffers().bufferSource();
                font.drawInBatch(id.getName(), 0, 0, textColor, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, packedLight);
            }
            poseStack.popPose();
            i ++;
        }
        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
    }
}
