package nws.mc.ned.config.client;

public record ClientData(
        int MobSkillRenderType,
        double MobSkillRenderRotationAngle,
        boolean MobSkillOnlyRenderWithView,
        int MobSkillRenderRadius,
        int MobSkillRenderOffsetX,
        int MobSkillRenderOffsetY,
        int MobSkillRenderOffsetZ
) {}