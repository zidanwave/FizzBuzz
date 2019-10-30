package trainning.tdd.marsrover;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import static trainning.tdd.marsrover.Compass.*;

/**
 * @ClassName Rover
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/26
 * @Version 1.0
 */
public class Rover {
    private final Point position;
    private final Point area;
    private Compass direction;
    private LinkedList<Point> blocks;

    public Rover(int x, int y, int areaX, int areaY, Compass direction) {
        this.position = new Point(x, y);
        this.area = new Point(areaX, areaY);
        this.direction = direction;
        this.blocks = new LinkedList<>();
    }

    public Point getPosition() {
        return this.position;
    }

    public Compass getDirection() {
        return this.direction;
    }

    public Point getArea() {
        return this.area;
    }

    public boolean left() {
        this.direction = this.direction.left();
        return false;
    }

    public boolean right() {
        this.direction = this.direction.right();
        return false;
    }

    public boolean forward() {
        return forward(1);
    }

    public boolean back() {
        return forward(-1);
    }

    public void addBlock(Point point) {
        this.blocks.add(point);
    }

    private boolean forward(int factor) {
        boolean blocked = false;
        Point nextPos = nextStep(factor);
        if (isOutbound(nextPos)){
            nextPos = resetIntoArea(nextPos);
        }

        blocked = isBlocked(nextPos);
        if (blocked){
            nextPos = this.position;
        }else {
            this.position.setLocation(nextPos.x, nextPos.y);
        }
        return blocked;
    }

    private Point resetIntoArea(Point point) {
        Point result = new Point(0,0);
        result.setLocation((point.x+this.area.x)%this.area.x, (point.y+this.area.y)%this.area.y);
        return result;
    }

    private boolean isBlocked(Point curPos) {
        boolean blocked = false;
        Iterator it = this.blocks.iterator();
        while(it.hasNext()){
            Point point = (Point)it.next();
            if (curPos.equals(point)){
                blocked = true;
                break;
            }
        }
        return blocked;
    }

    private Point nextStep(int factor) {
        Point result = new Point(0,0);
        if (this.direction.equals(Compass.N) || this.direction.equals(Compass.S)){
            result.setLocation(this.position.x, this.position.y+factor*this.direction.getValue());
        }else{
            result.setLocation(this.position.x+factor*this.direction.getValue(), this.position.y);
        }

        return result;
    }

    private boolean isOutbound(Point point){
        return ((point.x < 0) || (point.y < 0) || (point.x > this.area.x) || (point.y > this.area.y));
    }

}
