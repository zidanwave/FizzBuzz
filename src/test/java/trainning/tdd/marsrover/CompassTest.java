package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName CompassTest
 * @Description TODO
 * 1. Compass(Enum)
 * 1.1 当前方向为N，左转为W，左转为S
 * 1.2 右转为E，右转为S
 * 1.3 左转4次为N
 * 1.4 右转4次为N
 * 1.5 检查方向N及转向之后的value
 *
 * @Author chenzi
 * @Date 2019/10/26
 * @Version 1.0
 */
public class CompassTest {
    @Test
    public void test_compass_turn_left(){
        Assert.assertEquals(Compass.N.left(), Compass.W);
        Assert.assertEquals(Compass.N.left().left(), Compass.S);
        Assert.assertEquals(Compass.N.left().left().left(), Compass.E);
    }

    @Test
    public void test_compass_turn_right(){
        Assert.assertEquals(Compass.N.right(), Compass.E);
        Assert.assertEquals(Compass.N.right().right(), Compass.S);
        Assert.assertEquals(Compass.N.right().right().right(), Compass.W);
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
    public void test_compass_getvalue(){
        Assert.assertEquals(Compass.N.getValue(), 1);
        Assert.assertEquals(Compass.N.left().getValue(), -1);
        Assert.assertEquals(Compass.N.left().left().getValue(), -1);
    }
}
