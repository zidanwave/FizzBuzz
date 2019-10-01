package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/10/1.
 */
public class Inch {
    private final double inchNumber;
    public Inch(double inchNumber) {
        this.inchNumber = inchNumber;
    }

    public double toFoots() {
        return inchNumber / 12.0;
    }

    public double toYards() {
        return new Foot(toFoots()).toYards();
    }
}
