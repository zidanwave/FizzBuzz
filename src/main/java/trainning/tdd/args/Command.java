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
            String key = lit.next().toString();
            if (isValidKey(schemas, key)) {
                key = key.substring(1);
                if (lit.hasNext()) {
                    String value = lit.next().toString();
                    if (isValidKey(schemas, value)){
                        lit.previous();
                    }else{
                        cmdMap.put(key, value);
                    }
                }else{
                    cmdMap.put(key, null);
                }
            }
        }
    }

    private boolean isValidKey(Schemas schemas, String value) {
        return value.startsWith("-") && schemas.containsKey(value.substring(1));
    }

    public String getRawValue(String key){
        return cmdMap.get(key);
    }

    public Object getValue(String key) {
        switch (schemas.getDefine(key)){
            case "bool": return "true".equals(getRawValue(key));
            case "int": return Integer.valueOf(getRawValue(key));
            case "str": return getRawValue(key);
            case "list.str": return getRawValue(key).split(",");
            case "list.int": return Arrays.asList(getRawValue(key).split(",")).stream().mapToInt(e -> Integer.valueOf(e)).toArray();
            default: return getRawValue(key);
        }
    }

}
