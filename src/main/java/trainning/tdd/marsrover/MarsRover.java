package trainning.tdd.marsrover;

import java.awt.*;
import java.util.Arrays;

/**
 * @ClassName MarsRover
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/27
 * @Version 1.0
 */
public class MarsRover {
    private final Rover rover;

    public MarsRover(int x, int y, int areaX, int areaY, Compass direction, String blocks) {
        this.rover = new Rover(x,y,areaX,areaY,direction);
        if (blocks == null) {
            return;
        }else if(!blocks.trim().contains(",")){
            return;
        }
        Arrays.asList(blocks.split(" ")).stream().forEach(e -> {
            String[] xy = e.split(",");
            int xx = Integer.valueOf(xy[0]);
            int yy = Integer.valueOf(xy[1]);
            rover.addBlock(new Point(xx,yy));
        });
    }

    public String receiveCommands(String commands) {
        int steps = 0;
        boolean blocked = false;
        for(char cmd : commands.toCharArray()){
            switch (cmd){
                case 'L': blocked = rover.left();break;
                case 'R': blocked = rover.right();break;
                case 'F': blocked = rover.forward();break;
                case 'B': blocked = rover.back();break;
            }
            if (blocked){
                break;
            }else{
                steps++;
            }
        }
        return String.format("x:%d,y:%d,direction:%s,steps:%d", rover.getPosition().x, rover.getPosition().y, rover.getDirection(), steps);
    }
}
