package trainning.tdd.marsrover;

import java.awt.*;
import java.util.Arrays;

/**
 * @ClassName MarsRover
 * @Description TODO
 * @Author chenzi
 * @Date 2019/11/4
 * @Version 1.0
 */
public class MarsRover {

    private final Rover rover;

    public MarsRover(Rover rover, String blocks) {
        this.rover = rover;
        if (blocks == null) {
            return;
        }else if (!blocks.trim().contains(",")){
            return;
        }
        Arrays.asList(blocks.split(" ")).stream().forEach(block->{
            String[] xy = block.split(",");
            this.rover.addBlock(Integer.valueOf(xy[0]), Integer.valueOf(xy[1]));
        });
    }

    public String receiveCommands(String commands) {
        int steps = 0;
        boolean blocked = false;
        char[] cmdArray = commands.toUpperCase().toCharArray();
        for (char cmd : cmdArray){
            switch (cmd){
                case 'L':
                    blocked = rover.left(); break;
                case 'R':
                    blocked = rover.right(); break;
                case 'F':
                    blocked = rover.forward(); break;
                case 'B':
                    blocked = rover.back(); break;
            }
            if (blocked){
                break;
            }else{
                steps++;
            }
        }
        return String.format("%d,%d,%s,%d", rover.getPosition().x, rover.getPosition().y, rover.getDirection(), steps);
    }

    public boolean containsBlock(String ps) {
        if (ps == null) {
            return false;
        }else if (!ps.trim().contains(",")){
            return false;
        }
        String[] xy = ps.split(",");
        Point block = new Point(Integer.valueOf(xy[0]), Integer.valueOf(xy[1]));
        return rover.containsBlock(block);
    }
}
