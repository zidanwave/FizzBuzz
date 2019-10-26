package trainning.tdd.marsrover;

import java.awt.*;
import java.util.Arrays;

/**
 * @ClassName RoverRemote
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/25
 * @Version 1.0
 */
public class RoverRemote {
    private final Rover rover;

    public RoverRemote(int x, int y, int areaX, int areaY, Compass direction) {
        this.rover = new Rover(x, y, areaX, areaY, direction);
    }

    public String sendCommand(String command) {
        char[] cmdSequence = command.toCharArray();
        for (char cmd : cmdSequence){
            switch (cmd){
                case 'L': rover.left();break;
                case 'R': rover.right();break;
                case 'F': rover.forward();break;
                case 'B': rover.back();break;
            }
        }
        return String.format("%d,%d,%s", rover.getPosition().x, rover.getPosition().y, rover.getDirection().name());
    }

    public void addBlock(Point point) {
        rover.addBlock(point);
    }
}
