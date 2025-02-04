package nws.mc.ned.mob$enchant.skill.a;

import nws.mc.ned.mob$enchant.skill.MobSkill;

public class WearDownMobSkill extends MobSkill {
    //每次切换武器都会损失耐久，若无耐久则陷入软弱
    public WearDownMobSkill(String id) {
        super(id);
    }
}
