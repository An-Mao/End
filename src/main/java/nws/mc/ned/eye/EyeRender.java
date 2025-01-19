package nws.mc.ned.eye;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import nws.mc.ned.NED;
import nws.mc.ned.config.general.GeneralConfig;
import org.joml.Matrix4f;

@EventBusSubscriber(modid = NED.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class EyeRender {
    private static final ResourceLocation EYE_LOCATION = ResourceLocation.tryBuild(NED.MOD_ID, "textures/eye/eye.png");



    @SubscribeEvent
    public static void onHurt(RenderLevelStageEvent event){
        if (GeneralConfig.INSTANCE.getDatas().isRenderEndEye()) {
            if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_SKY)) {
                //System.out.println("eye render");
                PoseStack posestack = new PoseStack();
                posestack.pushPose();
                posestack.mulPose(event.getModelViewMatrix());
                //posestack.mulPose(event.getCamera().rotation());
                //posestack.mulPose(Axis.YP.rotationDegrees(90));
                drawEyeInSky(posestack, event.getProjectionMatrix(), event.getPartialTick());
                posestack.popPose();
            }
        }
    }
    private static void drawEllipse(Matrix4f matrix4f, int x, int y, int width, int height, int color) {
        PoseStack posestack = new PoseStack();
        posestack.mulPose(matrix4f);
        Tesselator tesselator = Tesselator.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        int segments = 100;
        for (int i = 0; i <= segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            double dx = Math.cos(angle) * width / 2;
            double dy = Math.sin(angle) * height / 2;
            buffer.addVertex(matrix4f, (float) (x + dx), (float) (y + dy), 0.0F)
                    .setColor(color);
        }
        BufferUploader.drawWithShader(buffer.buildOrThrow());
    }
    private static void drawEyeInSky(PoseStack posestack, Matrix4f projectionMatrix, DeltaTracker partialTicks) {
        Tesselator tesselator = Tesselator.getInstance();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        // 设置眼睛的颜色
        float eyeWhiteR = 1.0F, eyeWhiteG = 1.0F, eyeWhiteB = 1.0F;
        float eyeIrisR = 0.2F, eyeIrisG = 0.6F, eyeIrisB = 1.0F;
        float eyePupilR = 0.0F, eyePupilG = 0.0F, eyePupilB = 0.0F;

        // 眼睛的椭圆中心
        float eyeX = 0.0F, eyeY = 123.0F, eyeZ = 0.0F;

        posestack.pushPose();
        posestack.translate(eyeX, eyeY, eyeZ);
        //posestack.mulPose(Axis.YP.rotationDegrees(45.0F));
        Matrix4f matrix4f = posestack.last().pose();

        float f12 = 320.0F;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, EYE_LOCATION);
        BufferBuilder bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder1.addVertex(matrix4f, -f12, 100.0F, -f12).setUv(0.0F, 0.0F);
        bufferbuilder1.addVertex(matrix4f, f12, 100.0F, -f12).setUv(1.0F, 0.0F);
        bufferbuilder1.addVertex(matrix4f, f12, 100.0F, f12).setUv(1.0F, 1.0F);
        bufferbuilder1.addVertex(matrix4f, -f12, 100.0F, f12).setUv(0.0F, 1.0F);

        BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());

        //BufferBuilder bufferBuilder;

        // 绘制眼睛白色部分（椭圆）
        /*
        bufferBuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.addVertex(matrix4f, 0.0F, 50.0F, 0.0F).setColor(eyeWhiteR, eyeWhiteG, eyeWhiteB, 1.0F);
        for (int i = 0; i <= 360; i += 10) {
            double angle = Math.toRadians(i);
            bufferBuilder.addVertex(matrix4f, (float) (50.0F * Math.cos(angle)), (float) (30.0F * Math.sin(angle)), 0.0F)
                    .setColor(eyeWhiteR, eyeWhiteG, eyeWhiteB, 1.0F);
        }
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());

         */
        // 绘制眼睛虹膜部分（较小的椭圆）
        /*
        bufferBuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.addVertex(matrix4f, 0.0F, 50.0F, 1.0F).setColor(eyeIrisR, eyeIrisG, eyeIrisB, 1.0F);
        for (int i = 0; i <= 360; i += 10) {
            double angle = Math.toRadians(i);
            bufferBuilder.addVertex(matrix4f, (float) (20.0F * Math.cos(angle)), (float) (12.0F * Math.sin(angle)), 0.0F)
                    .setColor(eyeIrisR, eyeIrisG, eyeIrisB, 1.0F);
        }
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());

         */
        // 绘制瞳孔部分（圆形）
        /*
        bufferBuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.addVertex(matrix4f, 0.0F, 50.0F, 2.0F).setColor(eyePupilR, eyePupilG, eyePupilB, 1.0F);
        for (int i = 0; i <= 360; i += 10) {
            double angle = Math.toRadians(i);
            bufferBuilder.addVertex(matrix4f, (float) (10.0F * Math.cos(angle)), (float) (10.0F * Math.sin(angle)), 0.0F)
                    .setColor(eyePupilR, eyePupilG, eyePupilB, 1.0F);
        }
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());

         */



        posestack.popPose();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(true);
    }
}