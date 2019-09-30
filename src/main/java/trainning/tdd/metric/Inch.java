package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/9/30.
 */
public class Inch {

    private final int inchNumber;

    public Inch(int inchNumber) {
        this.inchNumber = inchNumber;

    }

    public int toFoots() {
        return inchNumber / 12;
    }

}
