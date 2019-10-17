package trainning.tdd.args;

import org.testng.Assert;
import org.testng.annotations.Test;

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
 * 2. 测试常见参数时的情况，"-l true -p 8080 -d /usr/local"
 * 3. 测试非常见参数时的情况，"-l -p 8080 -d /usr/local -g this,is,a,list"
 * 4. 测试非常见参数时的情况，"-l -p -8080 -d /usr/local -g this,is,a,lis"
 */
public class CommandTest {
    @Test
    public void test_command_l(){
        Schemas schemas = new Schemas("l:bool");
        Command cmd = new Command(schemas, "-l true");
        Assert.assertEquals(cmd.getValue("l"), Boolean.TRUE);
        cmd = new Command(schemas, "-l false");
        Assert.assertEquals(cmd.getValue("l"), Boolean.FALSE);
        cmd = new Command(schemas, "-l");
        Assert.assertEquals(cmd.getValue("l"), Boolean.FALSE);
    }
    @Test
    public void test_command_p(){
        Schemas schemas = new Schemas("p:int");
        Command cmd = new Command(schemas, "-p 8080");
        Assert.assertEquals(cmd.getValue("p"), 8080);

        cmd = new Command(schemas, "-p -8080");
        Assert.assertEquals(cmd.getValue("p"), -8080);

    }
    @Test
    public void test_command_d(){
        Schemas schemas = new Schemas("d:str");
        Command cmd = new Command(schemas, "-d /usr/local");
        Assert.assertEquals(cmd.getValue("d"), "/usr/local");
    }
    @Test
    public void test_command_normal_line(){
        Schemas schemas = new Schemas("l:bool p:int d:str g:list");
        Command cmd = new Command(schemas, "-g this,is,a,list -l true -p 8080 -d /usr/local");
        Assert.assertEquals(cmd.getValue("l"), Boolean.TRUE);
        Assert.assertEquals(cmd.getValue("p"), 8080);
        Assert.assertEquals(cmd.getValue("d"), "/usr/local");
        Assert.assertEquals(Arrays.toString((String[]) cmd.getValue("g")), "[this, is, a, list]");
    }

    @Test
    public void test_command_no_true_line(){
        Schemas schemas = new Schemas("l:bool p:int d:str g:list");
        Command cmd = new Command(schemas, "-l -p 8080 -d /usr/local -g this,is,a,list");
        Assert.assertEquals(cmd.getValue("l"), Boolean.FALSE);
        Assert.assertEquals(cmd.getValue("p"), 8080);
        Assert.assertEquals(cmd.getValue("d"), "/usr/local");
        Assert.assertEquals(Arrays.toString((String[]) cmd.getValue("g")), "[this, is, a, list]");
    }

    @Test
    public void test_command_neg_int_line(){
        Schemas schemas = new Schemas("l:bool p:int d:str g:list");
        Command cmd = new Command(schemas, "-l -p -8080 -d /usr/local -g this,is,a,list");
        Assert.assertEquals(cmd.getValue("l"), Boolean.FALSE);
        Assert.assertEquals(cmd.getValue("p"), -8080);
        Assert.assertEquals(cmd.getValue("d"), "/usr/local");
        Assert.assertEquals(Arrays.toString((String[]) cmd.getValue("g")), "[this, is, a, list]");
    }



}
