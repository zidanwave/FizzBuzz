package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName CompassTest
 * @Description
 * 1.1 当前方向为N，左转为W，左转为S
 * 1.2 右转为E，右转为S
 * 1.3 左转4次为N
 * 1.4 右转4次为N
 * 1.5 检查方向N及转向之后的value
 * @Author chenzi
 * @Date 2019/10/30
 * @Version 1.0
 */
public class CompassTest {
    @Test
    public void test_compass_left(){
        Assert.assertEquals(Compass.N.left(), Compass.W);
        Assert.assertEquals(Compass.W.left(), Compass.S);
    }

    @Test
    public void test_compass_right(){
        Assert.assertEquals(Compass.N.right(), Compass.E);
        Assert.assertEquals(Compass.N.right().right(), Compass.S);
    }

    @Test
    public void test_compass_left_4_times() {
        Assert.assertEquals(Compass.N.left().left().left().left(), Compass.N);
    }

    @Test
    public void test_compass_right_4_times() {
        Assert.assertEquals(Compass.N.right().right().right().right(), Compass.N);
    }

    @Test
    public void test_compass_left_value(){
        Assert.assertEquals(Compass.N.getFactor(), 1);
        Assert.assertEquals(Compass.N.left().getFactor(), -1);
    }

}
