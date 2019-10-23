package trainning.tdd.args;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName ArgsTest
 * @Description 任务分解： 
1. Schemas：读取命令参数格式定义，并提供与参数格式相关功能，如数据类型转换、是否存在某种参数等
1.1 能单独识别一个参数定义的情况，分别是bool，str，int
1.2 能识别多个参数组合的情况，如：l:bool p:int d:str
1.3 能识别数组类型，如：g:list.str hi:list.int
1.4 能正确处理不合法的格式定义，如：l d:str p:
2. Command：读取命令参数，利用Schemas完成命令参数的识别，参数和数据的配对，提供命令参数数据提取的接口
2.1 能读取只有一个参数的命令，分别是-l true,-l false, -l, -p 8080, -d /usr/local
2.2 能读取多个参数组合的命令
2.3 能读取包含数组的命令，如: -l -p 8080 -g this,is,a,list -hi 1,2,3,-4,6
2.4 能处理不合法的命令，如：- -l true， 或：- - true
3. Args：组合Schemas，Command实现命令行的解析
3.1 能处理只有一个参数的命令
3.2 能处理多个参数组合的命令
3.3 能处理非常见命令
 * @Author chenzi
 * @Date 2019/10/21
 * @Version 1.0
 */
public class ArgsTest {
    @Test
    public void test_args_one(){
        Assert.assertEquals(new Args("l:bool", "-l true").getValue("l"), Boolean.TRUE);
        Assert.assertEquals(new Args("l:bool", "-l false").getValue("l"), Boolean.FALSE);
        Assert.assertEquals(new Args("l:bool", "-l").getValue("l"), Boolean.FALSE);
        Assert.assertEquals(new Args("p:int", "-p 8080").getValue("p"), 8080);
        Assert.assertEquals(new Args("p:int", "-p -8080").getValue("p"), -8080);
        Assert.assertEquals(new Args("d:str", "-d /usr/local").getValue("d"), "/usr/local");
        Assert.assertEquals(new Args("d:str", "-d").getValue("d"), null);
        Assert.assertEquals(new Args("g:list.str", "-g this,is,a,list").getValue("g"), new String[]{"this", "is", "a", "list"});
        Assert.assertEquals(new Args("h:list.int", "-h 1,2,-3,4,5,-6").getValue("h"), new Integer[]{1,2,-3,4,5,-6});
    }

    @Test
    public void test_args_combo(){
        Assert.assertEquals(new Args("l:bool p:int d:str", "-l true -p 8080 -d /usr/local").getValue("l"), Boolean.TRUE);
        Assert.assertEquals(new Args("l:bool p:int d:str", "-l true -p 8080 -d /usr/local").getValue("p"), 8080);
        Assert.assertEquals(new Args("l:bool p:int d:str", "-l true -p 8080 -d /usr/local").getValue("d"), "/usr/local");
        Assert.assertEquals(new Args("l:bool p:int d:str g:list.str", "-l true -p 8080 -d /usr/local -g this,is,a,list").getValue("g"), new String[]{"this", "is", "a", "list"});
        Assert.assertEquals(new Args("l:bool p:int d:str h:list.int", "-l true -p 8080 -d /usr/local -h 1,2,-3,4,5,-6").getValue("h"), new Integer[]{1,2,-3,4,5,-6});
    }


}
