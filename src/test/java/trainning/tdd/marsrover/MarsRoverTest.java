package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * @ClassName MarsRoverTest
 * @Description TODO
 * 3. RoverRemote:发送批量指令，如 "LLLL" 或："FRFFLBBBRFFF"
 * 3.1 左转4次，返回坐标、方向和执行状态（成功执行到第几步）
 * 3.2 右转10次，返回坐标、方向和执行状态
 * 3.3 前进1步，右转，前进2步，左转，后退3步，右转，前进3步，返回坐标、方向和执行状态
 * 3.4 前进5步，右转，前进4步，左转，前进1步，碰到障碍物，返回坐标、方向和执行状态
 * @Author chenzi
 * @Date 2019/10/27
 * @Version 1.0
 */
public class MarsRoverTest {
    @Test
    public void test_marsrover_turn_left_4_times(){
        MarsRover marsRover = new MarsRover(3,3,10,10,Compass.N,null);
        Assert.assertEquals(marsRover.receiveCommands("LLLL"), "x:3,y:3,direction:N,steps:4");
    }

    @Test
    public void test_marsrover_turn_right_10_times(){
        MarsRover marsRover = new MarsRover(3,3,10,10,Compass.N,null);
        Assert.assertEquals(marsRover.receiveCommands("RRRRRRRRRR"), "x:3,y:3,direction:S,steps:10");
    }

    @Test
    public void test_marsrover_do_more_steps(){
        MarsRover marsRover = new MarsRover(3,3,10,10,Compass.N,null);
        Assert.assertEquals(marsRover.receiveCommands("FRFFLBBBRFFF"), "x:8,y:1,direction:E,steps:12");
    }

    @Test
    public void test_marsrover_do_more_steps_with_blocks(){
        MarsRover marsRover = new MarsRover(3,3,10,10,Compass.N,"1,1 0,0 10,10 7,1");
        Assert.assertEquals(marsRover.receiveCommands("FRFFLBBBRFFF"), "x:6,y:1,direction:E,steps:10");
    }

}
