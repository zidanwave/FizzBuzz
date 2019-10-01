package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/10/1.
 */
public class Yard {
    private final double yardNumber;

    public Yard(double yardNumber) {
        this.yardNumber = yardNumber;
    }

    public double toFoots() {
        return yardNumber * 3;
    }

    public double toInches() {
        return new Foot(toFoots()).toInches();
    }
}
