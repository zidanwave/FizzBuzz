package trainning.tdd.marsrover;

/**
 * @ClassName Compass
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/26
 * @Version 1.0
 */
public enum Compass {
    N("NORTH", 1), W("WEST", -1), S("SOUTH", -1), E("EAST", 1);

    private final String name;
    private final int value;

    Compass(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Compass left() {
        return values()[(ordinal()+1)%4];
    }

    public Compass right() {
        return values()[(ordinal()+3)%4];
    }

    public int getValue() {
        return this.value;
    }
}
