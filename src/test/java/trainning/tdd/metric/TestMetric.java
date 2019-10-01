package trainning.tdd.metric;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by chenzi on 2019/10/1.
 */

//任务拆解：
//        - 创建测试方法，英尺换算为英寸，期望输入1英尺，返回12英寸
//        - 创建测试方法，码换算为英尺，期望输入1码，返回3英尺
//        - 创建测试方法，码换算为英寸，期望输入1码，返回1*12*3英尺
//        - 创建测试方法，英寸换算为英尺，期望输入1英寸，返回1/12英尺
//        - 创建测试方法，英尺换算为码，期望输入1英尺，返回1/3码
//        - 创建测试方法，英寸换算为码，期望输入1英尺，返回1/12/3码

public class TestMetric {
    @Test
    public void check_1_foot_12_inch() {
        Assert.assertEquals(new Foot(1).toInches(), 1 * 12.0);
    }

    @Test
    public void check_1_yard_3_foot() {
        Assert.assertEquals(new Yard(1).toFoots(), 1 * 3.0);
    }

    @Test
    public void check_1_yard_36_inch() {
        Assert.assertEquals(new Yard(1).toInches(), 1 * 3 * 12.0);
    }

    @Test
    public void check_1_inch_foot() {
        Assert.assertEquals(new Inch(1).toFoots(), 1.0/12);
    }

    @Test
    public void check_1_foot_yard() {
        Assert.assertEquals(new Foot(1).toYards(), 1.0/3);
    }

    @Test
    public void check_1_inch_yard() {
        Assert.assertEquals(new Inch(1).toYards(), 1/12.0/3);
    }




}
