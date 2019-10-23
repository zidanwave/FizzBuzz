package trainning.tdd.args;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName SchemasTest
 * @Description 任务分解
1. Schemas：读取命令参数格式定义，并提供与参数格式相关功能，如数据类型转换、是否存在某种参数等
1.1 能单独识别一个参数定义的情况，分别是bool，str，int
1.2 能识别多个参数组合的情况，如：l:bool p:int d:str
1.3 能识别数组类型，如：g:list.str h:list.int
1.4 能正确处理不合法的格式定义，如：l d:str p:
 * @Author chenzi
 * @Date 2019/10/21
 * @Version 1.0
 */
public class SchemasTest {
    @Test
    public void test_schemas_bool(){
        Assert.assertEquals(new Schemas("l:bool").getDefine("l"), "bool");
    }

    @Test
    public void test_schemas_str(){
        Assert.assertEquals(new Schemas("d:str").getDefine("d"), "str");
    }

    @Test
    public void test_schemas_int(){
        Assert.assertEquals(new Schemas("p:int").getDefine("p"), "int");
    }

    @Test
    public void test_schemas_combo(){
        Assert.assertEquals(new Schemas("l:bool d:str p:int").getDefine("l"), "bool");
        Assert.assertEquals(new Schemas("l:bool d:str p:int").getDefine("d"), "str");
        Assert.assertEquals(new Schemas("l:bool d:str p:int").getDefine("p"), "int");
    }

    @Test
    public void test_schemas_array(){
        Assert.assertEquals(new Schemas("g:list.str h:list.int").getDefine("g"), "list.str");
        Assert.assertEquals(new Schemas("g:list.str h:list.int").getDefine("h"), "list.int");
    }

    @Test
    public void test_schemas_ileagal(){
        Assert.assertEquals(new Schemas("l d:str p:").getDefine("l"), null);
        Assert.assertEquals(new Schemas("l d:str p:").getDefine("p"), null);
        Assert.assertEquals(new Schemas("l d:str p:").getDefine("g"), null);
    }



}
