package trainning.tdd.args;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName SchemasTest
 * @Description TODO
 * 1. Schemas：读取命令参数格式定义，并提供与参数格式相关功能，如数据类型转换、是否存在某种参数等
 * 1.1 能单独识别一个参数定义的情况，分别是bool，str，int，list
 * 1.2 能识别多个参数组合的情况，如：l:bool p:int d:str g:list
 *
 * 耗时：14:11
 * @Author chenzi
 * @Date 2019/10/18
 * @Version 1.0
 */
public class SchemasTest {
    @Test
    public void test_schemas_l(){
        Assert.assertEquals(new Schemas("l:bool").getDefine("l"), "bool");
    }

    @Test
    public void test_schemas_d(){
        Assert.assertEquals(new Schemas("d:str").getDefine("d"), "str");
    }

    @Test
    public void test_schemas_p(){
        Assert.assertEquals(new Schemas("p:int").getDefine("p"), "int");
    }

    @Test
    public void test_schemas_combo_define(){
        Schemas schemas = new Schemas("l:bool d:str p:int");
        Assert.assertEquals(schemas.getDefine("l"), "bool");
        Assert.assertEquals(schemas.getDefine("d"), "str");
        Assert.assertEquals(schemas.getDefine("p"), "int");
    }

    @Test
    public void test_schemas_g(){
        Assert.assertEquals(new Schemas("g:list").getDefine("g"), "list");
    }

}
