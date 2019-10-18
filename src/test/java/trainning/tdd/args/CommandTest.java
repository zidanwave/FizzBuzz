package trainning.tdd.args;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @ClassName CommandTest
 * @Description TODO
 * 2. Command：读取命令参数，利用Schemas完成命令参数的识别，参数和数据的配对，提供命令参数数据提取的接口
 * 2.1 能读取只有一个参数的命令，分别是-l true,-l false, -l, -p 8080, -d /usr/local
 * 2.2 能读取多个参数组合的命令
 * 耗时：20:26
 * @Author chenzi
 * @Date 2019/10/18
 * @Version 1.0
 */
public class CommandTest {
    @Test
    public void test_command_l_true_raw(){
        Assert.assertEquals(new Command(new Schemas("l:bool"),"-l true").getRawValue("l"), "true");
    }

    @Test
    public void test_command_l_false_raw(){
        Assert.assertEquals(new Command(new Schemas("l:bool"),"-l false").getRawValue("l"), "false");
    }

    @Test
    public void test_command_l_null_raw(){
        Assert.assertEquals(new Command(new Schemas("l:bool"),"-l").getRawValue("l"), null);
    }

    @Test
    public void test_command_p_8080_raw(){
        Assert.assertEquals(new Command(new Schemas("p:int"),"-p 8080").getRawValue("p"), "8080");
    }

    @Test
    public void test_command_p_neg_8080_raw(){
        Assert.assertEquals(new Command(new Schemas("p:int"),"-p -8080").getRawValue("p"), "-8080");
    }

    @Test
    public void test_command_d_str(){
        Assert.assertEquals(new Command(new Schemas("d:str"),"-d /usr/local").getRawValue("d"), "/usr/local");
    }

    @Test
    public void test_command_combo_args_raw() {
        Command cmd = new Command(new Schemas("l:boo p:int d:str"), "-l true -p 8080 -d /usr/local");
        Assert.assertEquals(cmd.getRawValue("l"), "true");
        Assert.assertEquals(cmd.getRawValue("p"), "8080");
        Assert.assertEquals(cmd.getRawValue("d"), "/usr/local");
    }

    @Test
    public void test_command_combo_special_args_raw() {
        Command cmd = new Command(new Schemas("l:boo p:int d:str"), "-l -p -8080 -d /usr/local");
        Assert.assertEquals(cmd.getRawValue("l"), null);
        Assert.assertEquals(cmd.getRawValue("p"), "-8080");
        Assert.assertEquals(cmd.getRawValue("d"), "/usr/local");
    }

    @Test
    public void test_command_combo_args() {
        Command cmd = new Command(new Schemas("l:bool p:int d:str"), "-l true -p 8080 -d /usr/local");
        Assert.assertEquals(cmd.getValue("l"), Boolean.TRUE);
        Assert.assertEquals(cmd.getValue("p"), 8080);
        Assert.assertEquals(cmd.getValue("d"), "/usr/local");
    }

    @Test
    public void test_command_combo_special_args() {
        Command cmd = new Command(new Schemas("l:bool p:int d:str"), "-l -p -8080 -d /usr/local");
        Assert.assertEquals(cmd.getValue("l"), Boolean.FALSE);
        Assert.assertEquals(cmd.getValue("p"), -8080);
        Assert.assertEquals(cmd.getValue("d"), "/usr/local");
    }

    @Test
    public void test_command_g_list_raw(){
        Assert.assertEquals(new Command(new Schemas("g:list"),"-g this,is,a,list").getRawValue("g"), "this,is,a,list");
    }

    @Test
    public void test_command_g_list(){
        Command command = new Command(new Schemas("g:list"), "-g this,is,a,list");
        Assert.assertEquals(Arrays.asList((String[])command.getValue("g")).toString(), "[this, is, a, list]");
    }

}
