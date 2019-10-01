package trainning.tdd.metric;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by chenzi on 2019/9/30.
 */

//任务拆解：
//        - 创建测试方法，英尺换算为英寸Foot.toInch()，期望输入1英尺，返回12英寸
//        - 实现Foot.toInch() ，执行测试验证换算方法
//        - 创建测试方法，码换算为英尺Yard.toFoot()，期望输入1码，返回3英尺
//        - 实现Yard.toFoot()，执行测试验证换算方法
//        - 创建测试方法，英寸换算为英尺Inch.toFoot()，期望输入1英寸，返回1/12英尺
//        - 实现Inch.toFoot()，执行测试验证换算方法
//        - 创建测试方法，英尺换算为码，期望输入1英尺，返回1/3码
//        - 创建测试方法，英寸换算为英尺，期望输入1英尺，返回1/12英尺
//        - 创建测试方法，英寸换算为码，期望输入1英尺，返回1/12/3码
//        - 创建测试方法，码换算为英寸，期望输入1码，返回1*12*3英尺

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
