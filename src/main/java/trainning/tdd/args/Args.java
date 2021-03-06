package trainning.tdd.args;

/**
 * @ClassName Args
 * @Description TODO
 * @Author chenzi
 * @Date 2019/10/22
 * @Version 1.0
 */
public class Args {
    private final Command command;

    public Args(String config, String command) {
        this.command = new Command(new Schemas(config), command);


    }

    public Object getValue(String key) {
        return command.getValue(key);
    }
}
