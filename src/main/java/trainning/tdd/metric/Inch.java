package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/9/30.
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
        return new Foot(this.toFoots()).toYards();
    }
}
