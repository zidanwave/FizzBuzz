package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/9/30.
 */
public class Foot {
    private final double footNumber;

    public Foot(double footNumber) {
        this.footNumber = footNumber;
    }

    public double toInches() {
        return footNumber * 12.0;
    }

    public double toYards() {
        return footNumber / 3.0;
    }

}
