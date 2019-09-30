package trainning.tdd.fizzbuzz;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by chenzi on 2019/9/30.
 */
public class GameNumber3Test {

    @Test
    public void return_1_when_1() {
        Assert.assertEquals(new GameNumber3(1).toString().toString(), "1");
    }


    @Test
    public void return_fizz_when_3() {
        Assert.assertEquals(new GameNumber3(3).toString(), "Fizz");
    }

    @Test
    public void return_buzz_when_5() {
        Assert.assertEquals(new GameNumber3(5).toString(), "Buzz");
    }

    @Test
    public void return_fizzbuzz_when_15() {
        Assert.assertEquals(new GameNumber3(15).toString(), "FizzBuzz");
    }

    @Test
    public void return_fizzbuzz_when_35() {
        Assert.assertEquals(new GameNumber3(35).toString(), "FizzBuzz");
    }



}
