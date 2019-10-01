package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/10/1.
 */
public class Yard {
    private static final double METRIC_YARD_TO_FOOT = 3.0;

    private final double yardNumber;

    public Yard(double yardNumber) {
        this.yardNumber = yardNumber;
    }

    public double toFoots() {
        return yardNumber * METRIC_YARD_TO_FOOT;
    }

    public double toInches() {
        return new Foot(toFoots()).toInches();
    }
}
