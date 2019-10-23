package trainning.tdd.args;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName CommandTest
 * @Description
2. Command：读取命令参数，利用Schemas完成命令参数的识别，参数和数据的配对，提供命令参数数据提取的接口
2.1 能读取只有一个参数的命令，分别是-l true,-l false, -l, -p 8080, -d /usr/local
2.2 能读取多个参数组合的命令
2.3 能读取包含数组的命令，如: -l -p 8080 -g this,is,a,list -h 1,2,3,-4,6
2.4 能处理不合法的命令，如：- -l true， 或：- - true

 * @Author chenzi
 * @Date 2019/10/21
 * @Version 1.0
 */
public class CommandTest {
    @Test
    public void test_command_bool_raw(){
        Assert.assertEquals(new Command(new Schemas("l:bool"), "-l true").getRawValue("l"), "true");
        Assert.assertEquals(new Command(new Schemas("l:bool"), "-l false").getRawValue("l"), "false");
        Assert.assertEquals(new Command(new Schemas("l:bool"), "-l").getRawValue("l"), null);
    }

    @Test
    public void test_command_int_raw(){
        Assert.assertEquals(new Command(new Schemas("p:int"), "-p 8080").getRawValue("p"), "8080");
        Assert.assertEquals(new Command(new Schemas("p:int"), "-p -8080").getRawValue("p"), "-8080");
    }

    @Test
    public void test_command_str_raw(){
        Assert.assertEquals(new Command(new Schemas("d:str"), "-d /usr/local").getRawValue("d"), "/usr/local");
    }

    @Test
    public void test_command_list_str_raw(){
        Assert.assertEquals(new Command(new Schemas("g:list.str"), "-g this,is,a,list").getRawValue("g"), "this,is,a,list");
    }

    @Test
    public void test_command_list_int_raw(){
        Assert.assertEquals(new Command(new Schemas("h:list.int"), "-h 1,2,-3,4,5,-6").getRawValue("h"), "1,2,-3,4,5,-6");
    }

    @Test
    public void test_command_combo_raw(){
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l true -p 8080 -d /usr/local").getRawValue("l"), "true");
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l -p 8080 -d /usr/local").getRawValue("l"), null);
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l true -p 8080 -d /usr/local").getRawValue("p"), "8080");
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l true -p 8080 -d /usr/local").getRawValue("d"), "/usr/local");
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l true -p -8080 -d /usr/local").getRawValue("p"), "-8080");
    }

    @Test
    public void test_command_bool(){
        Assert.assertEquals(new Command(new Schemas("l:bool"), "-l true").getValue("l"), Boolean.TRUE);
        Assert.assertEquals(new Command(new Schemas("l:bool"), "-l false").getValue("l"), Boolean.FALSE);
        Assert.assertEquals(new Command(new Schemas("l:bool"), "-l").getValue("l"), Boolean.FALSE);
    }

    @Test
    public void test_command_int(){
        Assert.assertEquals(new Command(new Schemas("p:int"), "-p 8080").getValue("p"), 8080);
        Assert.assertEquals(new Command(new Schemas("p:int"), "-p -8080").getValue("p"), -8080);
    }

    @Test
    public void test_command_str(){
        Assert.assertEquals(new Command(new Schemas("d:str"), "-d /usr/local").getValue("d"), "/usr/local");
    }

    @Test
    public void test_command_list_str(){
        Assert.assertEquals(new Command(new Schemas("g:list.str"), "-g this,is,a,list").getValue("g"), new String[]{"this","is","a","list"});
    }

    @Test
    public void test_command_list_int(){
        Assert.assertEquals(new Command(new Schemas("h:list.int"), "-h 1,2,-3,4,5,-6").getValue("h"), new Integer[]{1,2,-3,4,5,-6});
    }

    @Test
    public void test_command_ileagal(){
        Assert.assertEquals(new Command(new Schemas("l:bool"), "- -l true").getValue("l"), Boolean.TRUE);
        Assert.assertEquals(new Command(new Schemas("l:bool"), "- - true").getValue("l"), Boolean.FALSE);
    }

    @Test
    public void test_command_combo(){
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l true -p 8080 -d /usr/local").getValue("l"), Boolean.TRUE);
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l -p 8080 -d /usr/local").getValue("l"), Boolean.FALSE);
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l true -p 8080 -d /usr/local").getValue("p"), 8080);
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l true -p 8080 -d /usr/local").getValue("d"), "/usr/local");
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str"), "-l true -p -8080 -d /usr/local").getValue("p"), -8080);
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str g:list.str"), "-l true -p -8080 -d /usr/local -g this,is,a,list").getValue("g"), new String[]{"this","is","a","list"});
        Assert.assertEquals(new Command(new Schemas("l:bool p:int d:str h:list.int"), "-l true -p -8080 -d /usr/local -h 1,2,-3,4,5,-6").getValue("h"), new Integer[]{1,2,-3,4,5,-6});
    }
}
