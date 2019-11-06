package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName MarsRoverTest
 * @Description TODO
 * 3. MarsRoverTest: 发送批量指令，如 "LLLL" 或："FRFFLBBBRFFF"
 * 3.1 左转4次，返回坐标、方向和执行状态（成功执行到第几步）
 * 3.2 右转10次，返回坐标、方向和执行状态
 * 3.3 前进1步，右转，前进2步，左转，后退3步，右转，前进3步，返回坐标、方向和执行状态
 * 3.4 前进5步，右转，前进4步，左转，前进1步，碰到障碍物，返回坐标、方向和执行状态

 * @Author chenzi
 * @Date 2019/11/4
 * @Version 1.0
 */
public class MarsRoverTest {
    @Test
    public void test_marsrover_left_4_times(){
        MarsRover marsRover = new MarsRover(new Rover(3,3,10,10,Compass.N), null);
        Assert.assertEquals(marsRover.receiveCommands("LLLL"), "3,3,N,4");

    }

    @Test
    public void test_marsrover_right_10_times(){
        MarsRover marsRover = new MarsRover(new Rover(3,3,10,10,Compass.N), null);
        Assert.assertEquals(marsRover.receiveCommands("RRRRRRRRRR"), "3,3,S,10");
    }

    @Test
    public void test_marsrover_receive_12_commands(){
        MarsRover marsRover = new MarsRover(new Rover(3,3,10,10,Compass.N), null);
        Assert.assertEquals(marsRover.receiveCommands("FRFFLBBBRFFF"), "8,1,E,12");
    }

    @Test
    public void test_marsrover_receive_12_commands_and_blocked(){
        MarsRover marsRover = new MarsRover(new Rover(3,3,10,10,Compass.N), null);
        Assert.assertEquals(marsRover.receiveCommands("FFFFFRFFFFLF"), "7,9,N,12");

        marsRover = new MarsRover(new Rover(3,3,10,10,Compass.N), "1,1 0,0 10,10 7,1");
        Assert.assertEquals(marsRover.receiveCommands("FRFFLBBBRFFF"), "6,1,E,10");
    }

}
