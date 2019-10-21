package trainning.tdd.args;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @ClassName ArgsTest
 * @Description 任务分解：
 * 1. Schemas：读取命令参数格式定义，并提供与参数格式相关功能，如数据类型转换、是否存在某种参数等
 * 1.1 能单独识别一个参数定义的情况，分别是bool，str，int，list
 * 1.2 能识别多个参数组合的情况，如：l:bool p:int d:str g:list
 * 2. Command：读取命令参数，利用Schemas完成命令参数的识别，参数和数据的配对，提供命令参数数据提取的接口
 * 2.1 能读取只有一个参数的命令，分别是-l true,-l false, -l, -p 8080, -d /usr/local
 * 2.2 能读取多个参数组合的命令
 * 3. Args：组合Schemas，Command实现命令行的解析
 * 3.1 能处理只有一个参数的命令
 * 3.2 能处理多个参数组合的命令
 * 3.3 能处理非常见命令
 * @Author chenzi
 * @Date 2019/10/18
 * @Version 1.0
 */
public class ArgsTest {
    @Test
    public void test_args_l(){
        Assert.assertEquals(new Args("l:bool", "-l true").getValue("l"), Boolean.TRUE);
        Assert.assertEquals(new Args("l:bool", "-l false").getValue("l"), Boolean.FALSE);
        Assert.assertEquals(new Args("l:bool", "-l").getValue("l"), Boolean.FALSE);
    }

    @Test
    public void test_args_p(){
        Assert.assertEquals(new Args("p:int", "-p 8080").getValue("p"), 8080);
        Assert.assertEquals(new Args("p:int", "-p -8080").getValue("p"), -8080);
    }

    @Test
    public void test_args_d(){
        Assert.assertEquals(new Args("d:str", "-d /usr/local").getValue("d"), "/usr/local");
        Assert.assertEquals(new Args("d:str", "-d").getValue("d"), null);
    }

    @Test
    public void test_args_combo(){
        Assert.assertEquals(new Args("l:bool p:int d:str", "-l -p -8080 -d /usr/local").getValue("l"), Boolean.FALSE);
        Assert.assertEquals(new Args("l:bool p:int d:str", "-l -p -8080 -d /usr/local").getValue("p"), -8080);
        Assert.assertEquals(new Args("l:bool p:int d:str", "-l -p -8080 -d /usr/local").getValue("d"), "/usr/local");
    }

    @Test
    public void test_args_combo_list_str(){
        Assert.assertEquals(Arrays.asList((String[])new Args("g:list.str", "-g this,is,a,list").getValue("g")).toString(), "[this, is, a, list]");
        Assert.assertEquals(Arrays.asList((String[])new Args("l:bool p:int d:str g:list.str", "-l -p -8080 -d /usr/local -g this,is,a,list").getValue("g")).toString(), "[this, is, a, list]");
    }

    @Test
    public void test_args_combo_list_int(){
        Assert.assertEquals((int[])new Args("hi:list.int", "-hi 1,2,3,-4,6").getValue("hi"), new int[]{1, 2, 3, -4, 6});
        Assert.assertEquals((int[])new Args("l:bool p:int d:str hi:list.int", "-l -p -8080 -d /usr/local -hi 1,2,3,-4,6").getValue("hi"), new int[]{1, 2, 3, -4, 6});
    }
}
