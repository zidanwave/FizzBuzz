package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * @ClassName RoverTest
 * @Description
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
 *
 * 具体的TDD参考如下步骤（从测试Rover开始）：
 * 1 测试初始化1，检查火星车坐标、方向、探索区域，方向直接用字符串“N”表示
 * 2 测试初始化2，检查火星车坐标、方向、探索区域，方向为“W” * 3 测试火星车（方向默认为“N”）左转，直接返回“W” * 4 测试火星车右转，直接返回“E”
 * 5 测试火星车左转，再左转~~创建Compass类测试，测试左转方法~~重构Rover，涉及方向的变量类型改为Compass
 * 6 测试Compass右转
 * 7 测试Compass左转4次
 * 8 测试Compass右转4次
  * 9 测试火星车右转，再右转
 * 10 测试火星车左转4次

 * @Author chenzi
 * @Date 2019/10/30
 * @Version 1.0
 */
public class RoverTest {
    @Test
    public void test_rover_init() {
        Rover rover = new Rover(3,3,10,10,Compass.N);
        Assert.assertEquals(rover.getPosition(), new Point(3,3));
        Assert.assertEquals(rover.getArea(), new Point(10,10));
        Assert.assertEquals(rover.getDirection(), Compass.N);
    }

    @Test
    public void test_rover_init_2() {
        Rover rover = new Rover(3,4,12,10,Compass.W);
        Assert.assertEquals(rover.getPosition(), new Point(3,4));
        Assert.assertEquals(rover.getArea(), new Point(12,10));
        Assert.assertEquals(rover.getDirection(), Compass.W);
    }

    @Test
    public void test_rover_left() {
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.left();
        Assert.assertEquals(rover.getDirection(), Compass.W);
        rover.left();
        Assert.assertEquals(rover.getDirection(), Compass.S);
    }

    @Test
    public void test_rover_right() {
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.right();
        Assert.assertEquals(rover.getDirection(), Compass.E);
    }

    @Test
    public void test_rover_left_4_times() {
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.left();
        rover.left();
        rover.left();
        rover.left();
        Assert.assertEquals(rover.getDirection(), Compass.N);
    }

    //2.5 火星车前进1步，左转，前进，左转，前进，左转，前进
    @Test
    public void test_rover_step_and_turn_left() {
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(3, 4));
        rover.left();//W
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(2, 4));
        rover.left();//S
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(2, 3));
        rover.left();//E
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(3, 3));
    }

    //2.6 火星车4个方向各后退1步
    @Test
    public void test_rover_step_and_turn_right() {
        Rover rover = new Rover(3,3,10,10,Compass.N);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(3, 2));
        rover.right();//E
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(2, 2));
        rover.right();//S
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(2,3));
        rover.right();//W
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(3,3));
    }

    //2.7 火星车N方向前进并超出探索区域
    @Test
    public void test_rover_forward_outbound_area() {
        Rover rover = new Rover(1,9,10,10,Compass.N);
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(1, 10));
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(1,1));
    }

    //2.8 火星车N后退并超出探索区域
    @Test
    public void test_rover_back_outbound_area() {
        Rover rover = new Rover(9,1,10,10,Compass.N);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(9, 0));
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(9, 9));
    }

    //2.9 火星车W方向前进并超出探索区域
    @Test
    public void test_rover_W_forward_outbound_area() {
        Rover rover = new Rover(1,1,10,10,Compass.W);
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(0, 1));
        rover.forward();
        Assert.assertEquals(rover.getPosition(), new Point(9,1));
    }

    //2.10 火星车W后退并超出探索区域
    @Test
    public void test_rover_W_back_outbound_area() {
        Rover rover = new Rover(9,9,10,10, Compass.W);
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(10, 9));
        rover.back();
        Assert.assertEquals(rover.getPosition(), new Point(1, 9));
    }

    //
    @Test
    public void test_rover_block_list_check() {
        Rover rover = new Rover(1,1,10,10,Compass.N);
        rover.addBlock(2,2);
        rover.addBlock(3,5);
        rover.addBlock(6,4);
        Assert.assertTrue(rover.containsBlock(new Point(2,2)));
        Assert.assertTrue(rover.containsBlock(new Point(3,5)));
        Assert.assertTrue(rover.containsBlock(new Point(6,4)));
    }

    //2.11 火星车前进时遇到障碍物时停止
    @Test
    public void test_rover_forward_meet_block() {
        Rover rover = new Rover(1,1,10,10,Compass.N);
        rover.addBlock(1, 2);
        Assert.assertTrue(rover.forward());
        Assert.assertEquals(rover.getPosition(), new Point(1, 1));
        rover.addBlock(0, 1);
        rover.left();
        Assert.assertTrue(rover.forward());
        Assert.assertEquals(rover.getPosition(), new Point(1,1));
    }

    //2.12 火星车后退时遇到障碍物时停止
    @Test
    public void test_rover_back_meet_block() {
        Rover rover = new Rover(1,3,10,10,Compass.N);
        rover.addBlock(1, 2);
        Assert.assertTrue(rover.back());
        Assert.assertEquals(rover.getPosition(), new Point(1, 3));
        rover.addBlock(9, 3);
        rover.right();
        Assert.assertFalse(rover.back());
        Assert.assertEquals(rover.getPosition(), new Point(0, 3));
        Assert.assertTrue(rover.back());
        Assert.assertEquals(rover.getPosition(), new Point(0, 3));
    }

}
