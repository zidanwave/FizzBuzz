package trainning.tdd.metric;

/**
 * Created by chenzi on 2019/9/30.
 */
public class Yard {

    private final int yardNumber;
    public Yard(int yardNumber) {
        this.yardNumber = yardNumber;
    }

    public int toFoots(){
        return yardNumber * 3;
    }
}
