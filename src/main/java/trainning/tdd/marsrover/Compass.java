package trainning.tdd.marsrover;

/**
 * @ClassName Compass
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/30
 * @Version 1.0
 */
public enum Compass {
    N(1),W(-1),S(-1),E(1);


    private final int factor;

    Compass(int factor) {
        this.factor = factor;
    }

    public Compass left() {
        return values()[(ordinal()+1)%4];
    }

    public Compass right() {
        return values()[(ordinal()+3)%4];
    }

    public int getFactor(){
        return this.factor;
    }
}
