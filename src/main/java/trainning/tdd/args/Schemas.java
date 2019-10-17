package trainning.tdd.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by chenzi on 2019/10/14.
 */
public class Schemas {
    private final String config;
    private Map<String, String> configMap;


    public Schemas(String config) {
        this.config = config;
        this.configMap = new HashMap<>();

        ListIterator it = Arrays.asList(config.split(" ")).listIterator();
        while(it.hasNext()) {
            String[] keyPair = it.next().toString().split(":");
            configMap.put(keyPair[0], keyPair[1]);
        }
    }

    public String getType(String key) {
        return configMap.get(key);
    }

    public Object getValue(String key, String def) {
        switch (configMap.get(key)) {
            case "bool": return "true".equals(def);
            case "int": return Integer.valueOf(def);
            case "str": return def;
            case "list": return def.split(",");
            default:return def;

        }
    }

    private boolean isInteger(String value) {
        return value.matches("^[0-9]\\d*$");
    }

    public boolean containsKey(String key) {
        return configMap.containsKey(key);
    }
}
