package trainning.tdd.metric;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by chenzi on 2019/9/30.
 */
public class TestMetric {

    @Test
    public void test_1_foot_to_12_inch() {
        Assert.assertEquals(new Foot(1).toInches(), 12.0);
    }

    @Test
    public void test_1_yard_to_3_foot() {
        Assert.assertEquals(new Yard(1).toFoots(), 3.0);
    }

    @Test
    public void test_12_inch_to_1_foot() {
        Assert.assertEquals(new Inch(12).toFoots(), 1.0);
    }

    @Test
    public void test_3_foot_to_1_yard() {
        Assert.assertEquals(new Foot(3).toYards(), 1.0);
    }

    @Test
    public void test_1_inch_to_foot() {
        Assert.assertEquals(new Inch(1).toFoots(), 1.0 / 12);
    }

    @Test
    public void test_1_foot_to_yard() {
        Assert.assertEquals(new Foot(1).toYards(), 1.0/3.0);
    }

    @Test
    public void test_1_inch_to_yard() {
        Assert.assertEquals(new Inch(1).toYards(), 1.0/12/3);
    }

    @Test
    public void test_1_yard_to_inch() {
        Assert.assertEquals(new Yard(1).toInches(), 1.0*3*12);
    }


}
