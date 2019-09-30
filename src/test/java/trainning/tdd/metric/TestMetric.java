package trainning.tdd.metric;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by chenzi on 2019/9/30.
 */
public class TestMetric {

    @Test
    public void test_1_foot_to_12_inch() {
        Assert.assertEquals(new Foot(1).toInches(), 12);
    }

    @Test
    public void test_1_yard_to_3_foot() {
        Assert.assertEquals(new Yard(1).toFoots(), 3);
    }

    @Test
    public void test_12_inch_to_1_foot() {
        Assert.assertEquals(new Inch(12).toFoots(), 1);
    }

    @Test
    public void test_3_foot_to_1_yard() {
        Assert.assertEquals(new Foot(3).toYards(), 1);
    }




}
