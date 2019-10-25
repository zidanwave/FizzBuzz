package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

import static java.lang.Math.abs;

/**
 * @ClassName RoverTest
 * @Description TODO
 *
 * 初始需求
火星车收到的指令分为四类：
1. 探索区域信息：告知火星车，整片区域的长度（X）和宽度（Y）有多大；
2. 初始化信息：火星车的降落地点（x, y）和朝向（N, S, E, W）信息；
3. 移动指令：火星车可以前进（f）或后退（b）；
4. 转向指令：火星车可以左转90度（l）或右转90度（r）。
由于地球和火星之间的距离很远，指令必须批量发送，火星车执行完整批指令之后，再回报自己所在的位置坐标和朝向。

扩展需求
下面是火星探索团队提出的其他一些需求：
 * 应该阻止小车跑到超出整片区域坐标系的地方去
 * 火星不是平的，而是球体，所以如果小车从坐标系的一边跑了出去，应该从另一边跑进来
 * 火星地面上有障碍物，如果火星车遇到了障碍物导致后续指令受阻，应该停留在原地，放弃执行后续指令，并立即向地球回报

你需要自己判断，是否采纳这些需求。

 * 任务分解：
 * 1. Compass(Enum)
 * 1.1 当前方向为N，左转为W
 * 1.2 右转为E
 * 1.3 左转4次为N
 * 1.4 右转4次为N
 * 2 Rover
 * 2.1 初始化，检查火星车坐标、方向、探索区域
 * 2.2 火星车左转
 * 2.3 火星车右转
 * 2.4 火星车连续左转4次
 * 2.5 火星车前进1步
 * 2.6 火星车后退1步
 * 2.7 火星车N方向前进并超出探索区域
 * 2.8 火星车N后退并超出探索区域
 * 2.9 火星车W方向前进并超出探索区域
 * 2.10 火星车W后退并超出探索区域
 * 2.11 火星车遇到障碍物时停止，返回坐标和方向（一个障碍物一个坐标）
 * 3. RoverRemote:发送批量指令，如 "-c F-1,L,F-1,R,F-3" 或："-c FLFRFFF" 或："-c FLFRF3"
 * 3.1 左转4次，返回坐标和方向
 * 3.2 右转10次，返回坐标和方向
 * 3.3 前进1步，右转，前进2步，左转，后退3步，右转，前进3步，返回坐标和方向


 * @Author chenzi
 * @Date 2019/10/23
 * @Version 1.0
 */
public class RoverTest {
    @Test
    public void test_rover_init(){
        Assert.assertEquals(new Rover(5, 5, 10, 10, Compass.N).getPos(), new Point(5,5));
        Assert.assertEquals(new Rover(5, 5, 10, 10, Compass.N).getDirection(), Compass.N);
        Assert.assertEquals(new Rover(5, 5, 10, 10, Compass.N).getArea(), new Point(10,10));
    }

    @Test
    public void test_rover_turn_left_as_WEST(){
        Rover rover = new Rover(5, 5, 10, 10, Compass.N);
        Assert.assertEquals(rover.turn("l"), Compass.W);
    }

    @Test
    public void test_rover_turn_left_4_times_as_NORTH(){
        Rover rover = new Rover(5, 5, 10, 10, Compass.N);
        rover.turn("l");
        rover.turn("l");
        rover.turn("l");
        rover.turn("l");
        Assert.assertEquals(rover.getDirection(), Compass.N);
    }


    @Test
    public void test_rover_forward(){
        Rover rover = new Rover(5, 5, 10, 10, Compass.N);
        Assert.assertEquals(rover.forward(1), new Point(5, 5 + 1));
    }

    @Test
    public void test_rover_back(){
        Rover rover = new Rover(5, 5, 10, 10, Compass.N);
        rover.back(1);
        Assert.assertEquals(rover.getPos(), new Point(5, 5 - 1));
    }

    @Test
    public void test_rover_outbound_y_forward_6(){
        Rover rover = new Rover(5,5,10,10, Compass.N);
        Assert.assertEquals(rover.forward(6), new Point(5, abs((5+6)%10)));
    }

    @Test
    public void test_rover_outbound_y_back_8(){
        Rover rover = new Rover(5,5,10,10, Compass.N);
        Assert.assertEquals(rover.back(8), new Point(5, abs((5-8)%10+10)));
    }

    @Test
    public void test_rover_outbound_x_forward_6(){
        Rover rover = new Rover(5,5,10,10, Compass.W);
        Assert.assertEquals(rover.forward(6), new Point(abs((5-6)%10+10), 5));
    }

    @Test
    public void test_rover_outbound_x_back_6(){
        Rover rover = new Rover(5,5,10,10, Compass.W);
        Assert.assertEquals(rover.back(6), new Point(abs((5+6)%10), 5));
    }



}
