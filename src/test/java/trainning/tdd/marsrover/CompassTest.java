package trainning.tdd.marsrover;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName CompassTest
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/24
 * @Version 1.0
 */
public class CompassTest {
    @Test
    public void test_compass_N_turn_left(){
        Assert.assertEquals(Compass.N.left(), Compass.W);
    }

    @Test
    public void test_compass_N_turn_right(){
        Assert.assertEquals(Compass.N.right(), Compass.E);
    }


    @Test
    public void test_compass_N_turn_right_turn_right(){
        Assert.assertEquals(Compass.N.left().right(), Compass.N);
    }

    @Test
    public void test_compass_N_turn_left_4_times(){
        Assert.assertEquals(Compass.N.left().left().left().left(), Compass.N);
    }

    @Test
    public void test_compass_N_turn_right_4_times(){
        Assert.assertEquals(Compass.N.right().right().right().right(), Compass.N);
    }



}
