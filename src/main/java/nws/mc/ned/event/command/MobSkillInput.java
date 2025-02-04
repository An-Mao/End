package nws.mc.ned.event.command;

import net.minecraft.core.Holder;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class MobSkillInput {
    private final Holder<MobSkill> skill;

    public MobSkillInput(Holder<MobSkill> skill) {
        this.skill = skill;
    }
    public Holder<MobSkill> getSkill() {
        return skill;
    }
}
