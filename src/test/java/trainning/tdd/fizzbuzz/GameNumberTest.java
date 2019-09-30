package trainning.tdd.fizzbuzz;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by chenzi on 2019/9/29.
 */
public class GameNumberTest {


    @Test
    public void return_1_when_number_is_1() {
        Assert.assertEquals(new GameNumber(1).toString(), "1");
    }

    @Test
    public void return_fizz_when_number_is_3() {
        Assert.assertEquals(new GameNumber(3).toString(), "Fizz");
    }

    @Test
    public void return_buzz_when_number_is_3() {
        Assert.assertEquals(new GameNumber(5).toString(), "Buzz");
    }

    @Test
    public void return_fizzbuzz_when_number_is_15() {
        Assert.assertEquals(new GameNumber(15).toString(), "FizzBuzz");
    }

    @Test
    public void return_fizzbuzz_when_number_is_53() {
        Assert.assertEquals(new GameNumber(53).toString(), "FizzBuzz");
    }

    @Test
    public void return_number_when_number_is_negative_number() {
        Assert.assertEquals(new GameNumber(-1).toString(), "-1");
    }


}
