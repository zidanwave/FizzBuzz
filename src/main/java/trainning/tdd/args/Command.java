package trainning.tdd.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * @ClassName Command
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/21
 * @Version 1.0
 */
public class Command {
    private Schemas schemas;
    private Map<String, String> cmdMap = null;

    public Command(Schemas schemas, String command) {
        this.schemas = schemas;
        cmdMap = new HashMap<>();

        ListIterator lit = Arrays.asList(command.split(" ")).listIterator();
        while(lit.hasNext()) {
            String key = lit.next().toString();
            if (isValidKey(schemas, key)) {
                if (lit.hasNext()) {
                    String value = lit.next().toString();
                    if (isValidKey(schemas, value)){
                        lit.previous();
                    }else{
                        cmdMap.put(key.substring(1), value);
                    }

                } else {
                    cmdMap.put(key.substring(1), null);
                }
            }
        }
    }

    private boolean isValidKey(Schemas schemas, String key) {
        return key.startsWith("-") && schemas.containsKey(key.substring(1));
    }

    public String getRawValue(String key) {
        return cmdMap.get(key);
    }

    public Object getValue(String key) {
        switch (schemas.getDefine(key)){
            case "bool": return "true".equalsIgnoreCase(getRawValue(key));
            case "int": return Integer.valueOf(getRawValue(key));
            case "str": return getRawValue(key);
            case "list.str": return getRawValue(key).split(",");
            case "list.int": return Arrays.asList(getRawValue(key).split(",")).stream().mapToInt(e->Integer.valueOf(e)).toArray();
            default: return null;
        }

    }
}
