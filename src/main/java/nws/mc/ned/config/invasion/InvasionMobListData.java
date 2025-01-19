package nws.mc.ned.config.invasion;

public class InvasionMobListData {

    private String mid;
    private int min;
    private int max;
    private double probability;
    public InvasionMobListData(){}
    public InvasionMobListData(String mid,int min,int max,double probability){
        setMid(mid);
        setMin(min);
        setMax(max);
        setProbability(probability);
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMid() {
        return mid;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "MobListJson{" +
                "mid='" + getMid() + '\'' +
                ", min=" + getMin() +
                ", max=" + getMax() +
                ", weight=" + getProbability() +
                '}';
    }
}
