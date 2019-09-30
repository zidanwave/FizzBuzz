package trainning.tdd.fizzbuzz;

/**
 * Created by chenzi on 2019/9/29.
 */
public class GameNumber {

    private final  int rawNumber;

    public GameNumber(int rawNumber) {
        this.rawNumber = rawNumber;
    }

    @Override
    public String toString() {
        String fz = String.valueOf(rawNumber);
        boolean fizz = isRelated(3);
        boolean buzz = isRelated(5);

        if (fizz && buzz){
            fz = "FizzBuzz";
        }else if (fizz){
            fz = "Fizz";
        }else if (buzz){
            fz = "Buzz";
        }

        return fz;
    }

    private boolean isRelated(int num) {
        return (rawNumber % num == 0) || (String.valueOf(rawNumber).contains(String.valueOf(num)));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 101; i++) {
            System.out.printf("\t%3d\t%s\n", i, new GameNumber(i).toString());
        }
    }

}
