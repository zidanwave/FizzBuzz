package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/9/30.
 */
public class Yard {

    private final double yardNumber;
    public Yard(double yardNumber) {
        this.yardNumber = yardNumber;
    }

    public double toFoots(){
        return yardNumber * 3.0;
    }

    public double toInches() {
        return new Foot(this.toFoots()).toInches();
    }
}
