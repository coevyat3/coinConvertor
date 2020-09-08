package coinns;

import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.Serializable;

public class ILS extends Coin implements Serializable {
    private final double  value=0.28d;



    @Override
    public double getValue() {
        return  value;
    }


    @Test

    public double calculate(double input) {
        Assert.assertNotNull(input);
    return input * getValue();
}
}
