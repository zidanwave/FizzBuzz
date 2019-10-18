package trainning.tdd.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Schemas
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/18
 * @Version 1.0
 */
public class Schemas {
    private final String config;
    private Map<String, String> configMap = null;

    public Schemas(String config) {
        this.config = config;
        configMap = new HashMap<>();
        for (String keyDefStr:Arrays.asList(config.split(" "))) {
            String[] keyDefPair = keyDefStr.split(":");
            configMap.put(keyDefPair[0], keyDefPair[1]);
        }
    }

    public String getDefine(String key) {
        return configMap.get(key);
    }

    public boolean containsKey(String key) {
        return configMap.containsKey(key);
    }
}
