package trainning.tdd.marsrover;

import trainning.tdd.args.Command;

import java.awt.*;

import static java.lang.Math.abs;

/**
 * @ClassName Rover
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/23
 * @Version 1.0
 */
public class Rover {
    private Point pos = new Point(0,0);
    private final Point area = new Point(0,0);

    private Compass direction;

    public Rover(int x, int y, int areaX, int areaY, Compass direction) {
        this.pos.setLocation(x, y);
        this.area.setLocation(areaX, areaY);

        this.direction = direction;
    }

    public Point getPos() {
        return this.pos;
    }

    public Compass getDirection() {
        return this.direction;
    }

    public Point getArea() {
        return this.area;
    }

    public Compass turn(String leftOrRight) {
        if ("l".equalsIgnoreCase(leftOrRight)) {
            this.direction = this.direction.left();
        }else{
            this.direction = this.direction.right();
        }
        return this.direction;
    }


    public Point forward(int step) {
        switch (direction) {
            case N: {
                pos.setLocation(pos.x, pos.y + step * direction.getValue());
                break;
            }
            case W: {
                pos.setLocation(pos.x + step * direction.getValue(), pos.y);
                break;
            }
            case S: {
                pos.setLocation(pos.x, pos.y + step * direction.getValue());
                break;
            }
            case E: {
                pos.setLocation(pos.x + step * direction.getValue(), pos.y);
                break;
            }
        }
        if (isInArea()){
            pos.setLocation(resetXInArea(), resetYInArea());
        }
        return pos;
    }

    public Point back(int step) {
        return forward(-1 * step);
    }

    private int resetYInArea() {
        return abs((pos.y % area.y + area.y) % area.y);
    }

    private int resetXInArea() {
        return abs(pos.x % area.x + area.x) % area.x;
    }

    private boolean isInArea() {
        return (pos.x > area.x) || (pos.x < 0) || (pos.y > area.y) || (pos.y < 0);
    }

 }
