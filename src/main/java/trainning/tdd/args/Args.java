package trainning.tdd.args;

/**
 * Created by chenzi on 2019/10/15.
 */
public class Args {
    private final Command command;

    public Args(String config, String cmdLine) {
        this.command = new Command(new Schemas(config), cmdLine);
    }

    public Object get(String key) {
        return command.getValue(key);
    }
}
