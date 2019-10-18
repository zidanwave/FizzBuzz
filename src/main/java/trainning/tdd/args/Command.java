package trainning.tdd.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * @ClassName Command
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/18
 * @Version 1.0
 */
public class Command {
    private Schemas schemas;
    private Map<String, String> cmdMap = null;

    public Command(Schemas schemas, String command) {
        this.schemas = schemas;
        this.cmdMap = new HashMap<>();

        ListIterator lit = Arrays.asList(command.split(" ")).listIterator();
        while(lit.hasNext()){
            String commandDataStr = lit.next().toString();
            if (commandDataStr.startsWith("-")) {
                String name = commandDataStr.substring(1);
                if (schemas.containsKey(name)) {
                    if (lit.hasNext()) {
                        String value = lit.next().toString();
                        if (value.startsWith("-") && schemas.containsKey(value.substring(1))){
                            lit.previous();
                        }else {
                            cmdMap.put(name, value);
                        }
                    }else{
                        cmdMap.put(name, null);
                    }
                }
            }
        }

    }

    public String getRawValue(String key){
        return cmdMap.get(key);
    }

    public Object getValue(String key) {
        switch (schemas.getDefine(key)){
            case "bool": return "true".equals(getRawValue(key));
            case "int": return Integer.valueOf(getRawValue(key));
            case "str": return getRawValue(key);
            case "list": return getRawValue(key).split(",");
            default: return getRawValue(key);
        }
    }
}
