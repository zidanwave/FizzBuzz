package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * @ClassName RoverTest
 * @Description TODO
 * 初始需求
 * 火星车收到的指令分为四类：
 * 1. 探索区域信息：告知火星车，整片区域的长度（X）和宽度（Y）有多大；
 * 2. 初始化信息：火星车的降落地点（x, y）和朝向（N, S, E, W）信息；
 * 3. 移动指令：火星车可以前进（f）或后退（b）；
 * 4. 转向指令：火星车可以左转90度（l）或右转90度（r）。
 * 由于地球和火星之间的距离很远，指令必须批量发送，火星车执行完整批指令之后，再回报自己所在的位置坐标和朝向。
 * 扩展需求
 * 下面是火星探索团队提出的其他一些需求：
 * 应该阻止小车跑到超出整片区域坐标系的地方去
 * 火星不是平的，而是球体，所以如果小车从坐标系的一边跑了出去，应该从另一边跑进来
 * 火星地面上有障碍物，如果火星车遇到了障碍物导致后续指令受阻，应该停留在原地，放弃执行后续指令，并立即向地球回报
 * 你需要自己判断，是否采纳这些需求。

 * 任务分解：
 * 1. Compass(Enum)
 * 1.1 当前方向为N，左转为W，左转为S
 * 1.2 右转为E，右转为S
 * 1.3 左转4次为N
 * 1.4 右转4次为N
 * 1.5 检查方向N及转向之后的value
 * 2 Rover
 * 2.1 初始化，检查火星车坐标、方向、探索区域（初始化两次，以测试其通用型）
 * 2.2 火星车左转，左转
 * 2.3 火星车右转，右转
 * 2.4 火星车连续左转4次
 * 2.5 火星车前进1步，左转，前进，左转，前进，左转，前进
 * 2.6 火星车4个方向各后退1步
 * 2.7 火星车N方向前进并超出探索区域
 * 2.8 火星车N后退并超出探索区域
 * 2.9 火星车W方向前进并超出探索区域
 * 2.10 火星车W后退并超出探索区域
 * 2.11 火星车前进时遇到障碍物时停止
 * 2.12 火星车后退时遇到障碍物时停止
 * 3. RoverRemote:发送批量指令，如 "LLLL" 或："FRFFLBBBRFFF"
 * 3.1 左转4次，返回坐标、方向和执行状态（成功执行到第几步）
 * 3.2 右转10次，返回坐标、方向和执行状态
 * 3.3 前进1步，右转，前进2步，左转，后退3步，右转，前进3步，返回坐标、方向和执行状态
 * 3.4 前进5步，右转，前进4步，左转，前进1步，碰到障碍物，返回坐标、方向和执行状态
 *
 * @Author chenzi
 * @Date 2019/10/26
 * @Version 1.0
 */
public class RoverTest {
    @Test
    public void test_rover_init(){
        Assert.assertEquals(new Rover(3,3,10,10,Compass.N).getPosition(), new Point(3,3));
        Assert.assertEquals(new Rover(3,3,10,10,Compass.N).getDirection(), Compass.N);
        Assert.assertEquals(new Rover(3,3,10,10,Compass.N).getArea(), new Point(10,10));
    }

    @Test
    public void test_rover_init_2(){
        Assert.assertEquals(new Rover(3,4,10,15,Compass.W).getPosition(), new Point(3,4));
        Assert.assertEquals(new Rover(3,4,10,15,Compass.W).getDirection(), Compass.W);
        Assert.assertEquals(new Rover(3,4,10,15,Compass.W).getArea(), new Point(10,15));
    }

    @Test
    public void test_rover_turn_left(){
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.left();
        Assert.assertEquals(rover.getDirection(), Compass.W);
        rover.left();
        Assert.assertEquals(rover.getDirection(), Compass.S);
    }

    @Test
    public void test_rover_turn_right(){
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.right();
        Assert.assertEquals(rover.getDirection(), Compass.E);
        rover.right();
        Assert.assertEquals(rover.getDirection(), Compass.S);
    }

    //2.4 火星车连续左转4次
    @Test
    public void test_rover_turn_left_4_times(){
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.left();
        rover.left();
        rover.left();
        rover.left();
        Assert.assertEquals(rover.getDirection(), Compass.N);
    }

    //2.5 火星车前进1步，左转，前进，左转，前进，左转，前进
    @Test
    public void test_rover_forward_4_directions(){
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(3, 4));
        rover.left();
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(2, 4));
        rover.left();
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(2, 3));
        rover.left();
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(3, 3));
    }

    //2.6 火星车4个方向各后退1步
    @Test
    public void test_rover_back_4_directions(){
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(3, 2));
        rover = new Rover(3,3,10,10,Compass.E);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(2,3));
        rover = new Rover(3,3,10,10,Compass.S);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(3,4));
        rover = new Rover(3,3,10,10,Compass.W);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(4,3));
    }

    //2.7 火星车N方向前进并超出探索区域
    @Test
    public void test_rover_forward_outbound_area(){
        Rover rover = new Rover(3,9,10,10,Compass.N);
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(3, 10));
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(3, 1));
    }

    //2.8 火星车N后退并超出探索区域
    @Test
    public void test_rover_back_outbound_area(){
        Rover rover = new Rover(3,1,10,10,Compass.N);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(3, 0));
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(3, 9));
        rover = new Rover(1,1,10,10,Compass.E);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(0, 1));
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(9, 1));
    }

    //2.9 火星车W方向前进并超出探索区域
    @Test
    public void test_rover_forward_W_outbound_area() {
        Rover rover = new Rover(1, 1, 11, 10, Compass.W);
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(0, 1));
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(10, 1));
    }

    //2.11 火星车前进时遇到障碍物时停止
    @Test
    public void test_rover_forward_block() {
        Rover rover = new Rover(3,1,10,10,Compass.N);
        rover.addBlock(new Point(3,3));
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(3, 2));
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(3,2));
    }

    //2.12 火星车后退时遇到障碍物时停止
    @Test
    public void test_rover_back_block() {
        Rover rover = new Rover(3,1,10,10,Compass.N);
        rover.addBlock(new Point(3,0));
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(3,1));
    }
}
