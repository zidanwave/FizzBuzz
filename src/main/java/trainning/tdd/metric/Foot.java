package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/9/30.
 */
public class Foot {
    private final int footNumber;

    public Foot(int footNumber) {
        this.footNumber = footNumber;
    }

    public int toInches() {
        return footNumber * 12;
    }

    public int toYards() {
        return footNumber / 3;
    }

}
