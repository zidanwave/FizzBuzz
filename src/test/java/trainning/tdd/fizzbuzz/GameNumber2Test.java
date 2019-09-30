package trainning.tdd.fizzbuzz;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by chenzi on 2019/9/29.
 */
public class GameNumber2Test {

    @Test
    public void return_1_when_1() {
        Assert.assertEquals(new GameNumber2(1).toString(), "1");
    }


    @Test
    public void return_fizz_when_3() {
        Assert.assertEquals(new GameNumber2(3).toString(), "Fizz");
    }

    @Test
    public void return_buzz_when_5() {
        Assert.assertEquals(new GameNumber2(5).toString(), "Buzz");
    }


    @Test
    public void return_fizzbuzz_when_15() {
        Assert.assertEquals(new GameNumber2(15).toString(), "FizzBuzz");
    }

    @Test
    public void return_fizzbuzz_when_53() {
        Assert.assertEquals(new GameNumber2(53).toString(), "FizzBuzz");
    }




}

