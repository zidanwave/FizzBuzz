package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/10/1.
 */
public class Foot {
    private static final double METRIC_FOOT_TO_INCH = 12.0;
    private static final double METRIC_YARD_TO_FOOT = 3.0;

    private final double footNumber;
    public Foot(double footNumber) {
        this.footNumber = footNumber;
    }

    public double toInches() {
        return footNumber * METRIC_FOOT_TO_INCH;
    }

    public double toYards() {
        return footNumber / METRIC_YARD_TO_FOOT;
    }
}
