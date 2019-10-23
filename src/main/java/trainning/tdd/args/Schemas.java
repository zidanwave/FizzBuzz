package trainning.tdd.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Schemas
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/21
 * @Version 1.0
 */
public class Schemas {
    private Map<String, String> configMap = null;

    public Schemas(String config) {
        configMap = new HashMap<>();
        Arrays.asList(config.split(" ")).stream().forEach(e -> {
            String[] keyValue = e.split(":");
            if(keyValue.length>1) {
                configMap.put(keyValue[0], keyValue[1]);
            }else{
                configMap.put(keyValue[0], null);
            }
        });
    }

    public String getDefine(String key) {
        if (configMap.containsKey(key)) {
            return configMap.get(key);
        }else{
            return null;
        }
    }

    public boolean containsKey(String key) {
        return configMap.containsKey(key);
    }
}
