package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName CompassTest
 * @Description TODO
 * 1. Compass(Enum)
 * 1.1 当前方向为N，左转为W
 * 1.2 右转为E
 * 1.3 左转4次为N
 * 1.4 右转4次为N
 *
 * 耗时：20分钟
 * @Author chenzi
 * @Date 2019/10/25
 * @Version 1.0
 */
public class CompassTest {
    @Test
    public void test_compass_turn_left(){
        Assert.assertEquals(Compass.N.left(), Compass.W);
        Assert.assertEquals(Compass.W.left(), Compass.S);
    }

    @Test
    public void test_compass_turn_right(){
        Assert.assertEquals(Compass.N.right(), Compass.E);
        Assert.assertEquals(Compass.E.right(), Compass.S);
    }


    @Test
    public void test_compass_turn_left_4_times(){
        Assert.assertEquals(Compass.N.left().left().left().left(), Compass.N);
    }

    @Test
    public void test_compass_turn_right_4_times(){
        Assert.assertEquals(Compass.N.right().right().right().right(), Compass.N);
    }

    @Test
    public void test_compass_name_value(){
        Assert.assertEquals(Compass.N.toString(), "name:NORTH,value:1");
        Assert.assertEquals(Compass.N.left().toString(), "name:WEST,value:-1");
        Assert.assertEquals(Compass.N.right().toString(), "name:EAST,value:1");
    }


}
