package nws.mc.ned.mob$skill.other;

import nws.mc.ned.mob$skill.MobSkill;

public class CorrosionMobSkill extends MobSkill {
    //每次切换武器都会损失耐久，若无耐久则陷入软弱
    public CorrosionMobSkill(String id) {
        super(id);
    }
}
