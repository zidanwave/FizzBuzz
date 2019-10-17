package trainning.tdd.args;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by chenzi on 2019/10/14.
 */
/**
 * 任务分解：
 * 1. 测试只有一个参数时的情况
 * 1.1 "l:true"，创建Schemas，直接返回 TRUE
 * 1.2 "p:int"，测试不通过时重构Schemas，使其更通用
 * 1.3 其它一个参数时的情况
 * 2. 测试常见参数时的情况，"l:bool p:int d:str g:list"
 * 3. 测试数据解析
 * 3.1 get("l", "true")==true
 * 3.2 get("l", "false")==false
 * 3.3 get("l", null)==false
 * 3.4 get("p", "8080")==8080
 * 3.5 get("d", "/usr/local")=="/usr/local"
 * 3.6 get("g", "this,is,a,list")==["this","is","a","list"]
 */
public class SchemasTest {
    @Test
    public void test_schemas_bool(){
        Schemas sc = new Schemas("l:bool");
        Assert.assertEquals(sc.getType("l"), "bool");
    }
    @Test
    public void test_schemas_int(){
        Schemas sc = new Schemas("p:int");
        Assert.assertEquals(sc.getType("p"), "int");
    }
    @Test
    public void test_schemas_str(){
        Schemas sc = new Schemas("d:str");
        Assert.assertEquals(sc.getType("d"), "str");
    }

    @Test
    public void test_schemas_normal_config(){
        Schemas sc = new Schemas("l:bool p:int d:str g:list");
        Assert.assertEquals(sc.getType("l"), "bool");
        Assert.assertEquals(sc.getType("p"), "int");
        Assert.assertEquals(sc.getType("d"), "str");
        Assert.assertEquals(sc.getType("g"), "list");
    }


    @Test
    public void test_schemas_get_value(){
        Schemas sc = new Schemas("l:bool p:int d:str g:list");
        Assert.assertEquals(sc.getValue("l", "true"), Boolean.TRUE);
        Assert.assertEquals(sc.getValue("l", "false"), Boolean.FALSE);
        Assert.assertEquals(sc.getValue("l", null), Boolean.FALSE);
        Assert.assertEquals(sc.getValue("p", "8080"), 8080);
        Assert.assertEquals(sc.getValue("d", "/usr/local"), "/usr/local");
        Assert.assertEquals(Arrays.toString((String[])sc.getValue("g", "this,is,a,list")), "[this, is, a, list]");
    }

}
