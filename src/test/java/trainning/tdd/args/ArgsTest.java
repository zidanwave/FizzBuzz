package trainning.tdd.args;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by chenzi on 2019/10/15.
 */

/**
 * 任务分解：
 * 1. 测试只有一个参数时的情况
 * 1.1 "-l true"，测试不通过时创建Args，直接返回 TRUE
 * 1.2 "-l false"，测试不通过时重构Args，使其更通用
 * 1.3 其它一个参数时的情况
 * 2. 测试常见参数时的情况，"-l true -p 8080 -d /usr/local -g this,is,a,list"
 * 3. 测试非常见参数时的情况，"-l -p -8090 -d /usr/local -g this,is,a,list"
 */
public class ArgsTest {
    @Test
    public void test_args_one_params(){
        Assert.assertEquals(new Args("l:bool", "-l true").get("l"), Boolean.TRUE);
        Assert.assertEquals(new Args("l:bool", "-l false").get("l"), Boolean.FALSE);
        Assert.assertEquals(new Args("l:bool", "-l").get("l"), Boolean.FALSE);
        Assert.assertEquals(new Args("p:int", "-p 8080").get("p"), 8080);
        Assert.assertEquals(new Args("d:str", "-d /usr/local").get("d"), "/usr/local");
        Assert.assertEquals(Arrays.toString((String[])new Args("g:list", "-g this,is,a,list").get("g")), "[this, is, a, list]");
    }

    @Test
    public void test_args_normal_params(){
        Args args;
        args = new Args("l:bool p:int d:str g:list","-l true -p 8080 -d /usr/local -g this,is,a,list");
        Assert.assertEquals(args.get("l"), Boolean.TRUE);
        Assert.assertEquals(args.get("p"), 8080);
        Assert.assertEquals(args.get("d"), "/usr/local");
        Assert.assertEquals(Arrays.toString((String[])args.get("g")), "[this, is, a, list]");

    }
    @Test
    public void test_args_special_params(){
        Args args;
        args = new Args("l:bool p:int d:str g:list","-l -p -8090 -d /usr/local -g this,is,a,list");
        Assert.assertEquals(args.get("l"), Boolean.FALSE);
        Assert.assertEquals(args.get("p"), -8090);
        Assert.assertEquals(args.get("d"), "/usr/local");
        Assert.assertEquals(Arrays.toString((String[])args.get("g")), "[this, is, a, list]");
    }

}
