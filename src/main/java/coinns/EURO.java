package coinns;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.Serializable;

public  class EURO extends Coin implements Serializable {

    private  final double value = 4.23D;



    @Override
    double getValue() {
        return value;
    }

    @Test
    public double calculate(double input) {
        Assert.assertNotNull(input);

        return input*getValue();
    }


}