package nws.mc.ned.mob$skill;

import net.minecraft.core.Holder;

public class MobSkillInput {
    private final Holder<MobSkill> skill;

    public MobSkillInput(Holder<MobSkill> skill) {
        this.skill = skill;
    }
    public Holder<MobSkill> getSkill() {
        return skill;
    }
}
