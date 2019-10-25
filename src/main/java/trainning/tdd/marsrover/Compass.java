package trainning.tdd.marsrover;

/**
 * @ClassName Compass
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/25
 * @Version 1.0
 */
public enum Compass {
    N("NORTH", 1), W("WEST", -1), S("SOUTH", -1), E("EAST", 1);

    private String name;
    private int value;
    private Compass compass;

    Compass(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Compass left() {
        this.compass = values()[(ordinal()+1)%4];
        return this.compass;
    }

    public Compass right() {
        this.compass = values()[(ordinal()+3)%4];
        return this.compass;
    }

    @Override
    public String toString(){
        return "name:" + this.name + ",value:" + this.value;
    }

    public int getValue() {
        return this.value;
    }
}
