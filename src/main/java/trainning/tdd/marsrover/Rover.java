package trainning.tdd.marsrover;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @ClassName Rover
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/25
 * @Version 1.0
 */
public class Rover {
    private Point pos;
    private final Point area;
    private Compass direction;
    private List<Point> blocks;

    public Rover(int x, int y, int areaX, int areaY, Compass direction) {
        this.pos = new Point(x, y);
        this.area = new Point(areaX, areaY);
        this.direction = direction;
        this.blocks = new LinkedList<>();
    }

    public Point getPosition() {
        return this.pos;
    }

    public Compass getDirection() {
        return this.direction;
    }

    public Point getArea() {
        return this.area;
    }

    public Compass left() {
        this.direction = this.direction.left();
        return this.direction;
    }

    public Compass right() {
        this.direction = this.direction.right();
        return this.direction;
    }

    public Point forward() {
        Point nextPos = moveOneStep(true);

        if (!isMeetBlock(nextPos)){
            this.pos = nextPos;
            if (isOutboundArea()){
                resetIntoArea();
            }
        }

        return this.pos;
    }


    public Point back() {
        Point nextPos = moveOneStep(false);

        if (!isMeetBlock(nextPos)){
            this.pos = nextPos;
            if (isOutboundArea()){
                resetIntoArea();
            }
        }

        return this.pos;
    }


    private boolean isMeetBlock(Point nextPos) {
        for (Point p : this.blocks) {
            if(p.equals(nextPos)) {
                return true;
            }
        }
        return false;
    }

    private Point moveOneStep(boolean forward) {
        Point result = new Point(0,0);
        int step = forward ? 1 : -1;
        if (Compass.N.equals(this.direction) || (Compass.S.equals(this.direction))) {
            result.setLocation(this.pos.x, this.pos.y + step * this.direction.getValue());
        }else{
            result.setLocation(this.pos.x + step * this.direction.getValue(), this.pos.y);
        }
        return result;
    }

    private void resetIntoArea() {
        this.pos.setLocation((this.pos.x%this.area.x+this.area.y)%this.area.x, (this.pos.y%this.area.y+this.area.y)%this.area.y);
    }

    private boolean isOutboundArea(){
        return (this.pos.x < 0 || this.pos.x > this.area.x || this.pos.y < 0 || this.pos.y > this.area.y);
    }

    public Point back(int step) {
        for(int ix=0; ix<step; ix++) {
            back();
        }
        return this.pos;
    }

    public Point forward(int step){
        for(int ix=0; ix<step; ix++) {
            forward();
        }
        return this.pos;
    }



    public void addBlock(Point point) {
        this.blocks.add(point);
    }
}
