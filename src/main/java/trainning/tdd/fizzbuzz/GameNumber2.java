package trainning.tdd.fizzbuzz;

/**
 * Created by chenzi on 2019/9/29.
 */
public class GameNumber2 {

    private final int rawNumber;


    public GameNumber2(int rawNumber) {
        this.rawNumber = rawNumber;
    }

    @Override
    public String toString() {
        String fz;

        int num = 3;
        boolean fizz = isRelated(num);
        num = 5;
        boolean buzz = (isRelated(num));

        if (fizz && buzz){
            fz = "FizzBuzz";
        }else if (fizz) {
            fz = "Fizz";
        }else if(buzz){
            fz = "Buzz";
        }
        else {
            fz = String.valueOf(rawNumber);
        }

        return fz;
    }

    private boolean isRelated(int num) {
        return (rawNumber % num == 0) || (String.valueOf(rawNumber).contains(String.valueOf(num)));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 101; i++) {
            System.out.printf("\t%3d\t%s\n", i, new GameNumber2(i).toString());
        }
    }


}
