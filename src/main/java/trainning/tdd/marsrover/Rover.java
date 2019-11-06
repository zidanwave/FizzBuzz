package trainning.tdd.marsrover;

import java.awt.*;
import java.util.LinkedList;

/**
 * @ClassName Rover
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/30
 * @Version 1.0
 */
public class Rover {
    private Point position;
    private final Point area;
    private Compass direction;
    private LinkedList<Point> blocks;

    public Rover(int x, int y, int areaX, int areaY, Compass direction) {
        this.position = new Point(x,y);
        this.area = new Point(areaX, areaY);
        this.direction = direction;
        this.blocks = new LinkedList<>();
    }

    public Point getPosition() {
        return this.position;
    }

    public Point getArea() {
        return this.area;
    }

    public Compass getDirection() {
        return this.direction;
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
        Point nextPos = new Point(0,0);
        if (Compass.N.equals(this.direction) || Compass.S.equals(this.direction)) {
            nextPos.setLocation(this.position.x, this.position.y + this.direction.getFactor());
        }else{
            nextPos.setLocation(this.position.x + this.direction.getFactor(), this.position.y);
        }
        if (isOutboundArea(nextPos)) {
            nextPos = resetIntoArea(nextPos);
        }

        boolean blocked = isMeetBlock(nextPos);
        if (blocked){
            return blocked;
        }else{
            this.position = nextPos;
            return blocked;
        }
    }


    public boolean back() {
        Point nextPos = new Point(0,0);
        if (Compass.N.equals(this.direction) || Compass.S.equals(this.direction)) {
            nextPos.setLocation(this.position.x, this.position.y - this.direction.getFactor());
        }else{
            nextPos.setLocation(this.position.x - this.direction.getFactor(), this.position.y);
        }
        if (isOutboundArea(nextPos)) {
            nextPos = resetIntoArea(nextPos);
        }

        boolean blocked = isMeetBlock(nextPos);
        if (blocked) {
            return blocked;
        }else{
            this.position = nextPos;
            return blocked;
        }
    }

    private boolean isMeetBlock(Point point) {
        for (Point block : this.blocks) {
            if (block.equals(point)){
                return true;
            }
        }
        return false;
    }

    private Point resetIntoArea(Point p) {
        return new Point((p.x%this.area.x +this.area.x)%this.area.x, (p.y%this.area.y+this.area.y)%this.area.y);
    }

    private boolean isOutboundArea(Point point) {
        return ((point.x < 0) || (point.y < 0) || (point.x > this.area.x) || (point.y > this.area.y));
    }

    public void addBlock(int x, int y) {
        this.blocks.add(new Point(x,y));
    }

    public boolean containsBlock(Point point) {
        for (Point p : this.blocks) {
            if (p.equals(point)){
                return true;
            }
        }
        return  false;
    }
}
