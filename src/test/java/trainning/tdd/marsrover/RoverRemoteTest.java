package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName RoverRemoteTest
 * @Description TODO
 * 3. RoverRemote:发送批量指令，如 "LLLL" 或："FRFFLBBBRFFF"
 * 3.1 左转4次，返回坐标和方向
 * 3.2 右转10次，返回坐标和方向
 * 3.3 前进1步，右转，前进2步，左转，后退3步，右转，前进3步，返回坐标和方向
 * @Author chenzi
 * @Date 2019/10/25
 * @Version 1.0
 */
public class RoverRemoteTest {
    @Test
    public void test_rover_remote_turn_left_4_times(){
        RoverRemote roverRemote = new RoverRemote(3,3,10,10,Compass.N);
        Assert.assertEquals(roverRemote.sendCommand("LLLL"), "3,3,N");
    }

    @Test
    public void test_rover_remote_turn_right_10_times(){
        RoverRemote roverRemote = new RoverRemote(3,3,10,10,Compass.N);
        Assert.assertEquals(roverRemote.sendCommand("RRRRRRRRRR"), "3,3,S");
    }

    @Test
    public void test_rover_remote_do_more_instruction(){
        RoverRemote roverRemote = new RoverRemote(3,3,10,10,Compass.N);
        Assert.assertEquals(roverRemote.sendCommand("FRFFLBBBRFFF"), "8,1,E");
    }
}