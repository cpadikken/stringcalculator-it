package nl.oose.stringcalculator.core;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

public class StringAddedFactoryTest {

    @Test
    public void createIsNotNull() throws Exception {
        assertNotNull(StringAddedFactory.create());
    }

    @Test
    public void everyCreateDeliversAnUniqueInstance()
    {
        assertNotSame(StringAddedFactory.create(), StringAddedFactory.create());
    }

}