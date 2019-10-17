package trainning.tdd.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by chenzi on 2019/10/15.
 */
public class Command {
    private final String cmdLine;
    private Map<String, String> cmdMap;
    private Schemas schemas;

    public Command(Schemas schemas, String cmdLine) {
        this.cmdLine = cmdLine;
        this.cmdMap = new HashMap<>();
        this.schemas = schemas;

        ListIterator<String> it = Arrays.asList(this.cmdLine.split(" ")).listIterator();
        while(it.hasNext()) {
            String keyOrValue = it.next().toString();
            if (keyOrValue.startsWith("-")) {
                String key = keyOrValue.substring(1);
                String value = it.hasNext()?it.next().toString():null;
                //在Command不知道命令行数据格式定义的情况下，处理参数为负数的逻辑是多余的。格式的定义在Schemas，Command的行为是依赖于Schemas的
                if (this.schemas.containsKey(key)){
                    if (null == value){
                        this.cmdMap.put(key, value);
                    }
                    else if ((value.startsWith("-")) && (this.schemas.containsKey(value.substring(1)))) {
                        this.cmdMap.put(key, null);
                        it.previous();
                    }else{
                        this.cmdMap.put(key, value);
                    }
                }
            }
        }
    }

    private boolean isInteger(String value) {
        return value.matches("^[0-9]\\d*$");
    }

    public String getRawValue(String key) {
        return this.cmdMap.get(key);
    }

    public Object getValue(String key) {
        return this.schemas.getValue(key, getRawValue(key));
    }
}
