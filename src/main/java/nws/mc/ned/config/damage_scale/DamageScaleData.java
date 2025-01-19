package nws.mc.ned.config.damage_scale;

public class DamageScaleData {
    private int ApplicableTarget;
    private float ScaleWithHealth;
    private float MinDamage;

    public void setApplicableTarget(int applicableTarget) {
        ApplicableTarget = applicableTarget;
    }

    public int getApplicableTarget() {
        return ApplicableTarget;
    }

    public float getScaleWithHealth() {
        return ScaleWithHealth;
    }

    public void setScaleWithHealth(float scaleWithHealth) {
        ScaleWithHealth = scaleWithHealth;
    }

    public float getMinDamage() {
        return MinDamage;
    }

    public void setMinDamage(float minDamage) {
        MinDamage = minDamage;
    }
}
