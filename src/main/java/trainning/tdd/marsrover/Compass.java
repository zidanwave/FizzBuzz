package trainning.tdd.marsrover;

import trainning.tdd.args.Command;

/**
 * @ClassName Compass
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/24
 * @Version 1.0
 */
public enum Compass {
    N("NORTH", 1),W("WEST", -1),S("SOUTH", -1),E("EAST", 1);

    private String name;
    private int value;
    private Compass compass;

    Compass(String name, int value) {
        this.name = name;
        this.value = value;
        this.compass = this;
    }

    @Override
    public String toString(){
        return this.value + "_" + this.name;
    }

    public Compass left() {
        this.compass =  values()[(ordinal()+1)%4];
        this.name = this.compass.name;
        this.value = this.compass.value;
        return this.compass;
    }

    public Compass right() {
        this.compass = values()[(ordinal()+3)%4];
        this.name = this.compass.name;
        this.value = this.compass.value;
        return this.compass;
    }

    public int getValue() {
        return this.value;
    }
}
