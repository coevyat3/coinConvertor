package coinns;

import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.Serializable;
public class USD extends Coin implements Serializable {
    private final double value = 3.52d;

@Override
 double getValue() {
        return value;
    }

@Test
public  double calculate(double input) {
    Assert.assertNotNull(input);
    return input*getValue();

}}
