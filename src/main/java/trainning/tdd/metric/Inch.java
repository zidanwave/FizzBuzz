package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/10/1.
 */
public class Inch {
    private static final double METRIC_FOOT_TO_INCH = 12.0;

    private final double inchNumber;
    public Inch(double inchNumber) {
        this.inchNumber = inchNumber;
    }

    public double toFoots() {
        return inchNumber / METRIC_FOOT_TO_INCH;
    }

    public double toYards() {
        return new Foot(toFoots()).toYards();
    }
}
