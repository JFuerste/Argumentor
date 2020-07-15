package argumentor;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArgumentorTest {

    @Test
    public void emptyBooleanTest() {
        Argumentor argumentor = getOneFlagArgumentor();
        argumentor.parseArguments(new String[0]);
        boolean cArg = argumentor.getBooleanArgument("c");
        assertFalse(cArg);
    }


    private Argumentor getOneFlagArgumentor() {
        Argumentor argumentor = new Argumentor();
        argumentor.addBooleanArgument("c");
        return argumentor;
    }

    @Test
    public void singleFlagTest() {
        Argumentor argumentor = getOneFlagArgumentor();
        argumentor.parseArguments(getSimpleBooleanArg());
        boolean cArg = argumentor.getBooleanArgument("c");
        assertTrue(cArg);
    }

    @Test(expected = ArgumentorException.class)
    public void nonexistentFlagTest() {
        Argumentor argumentor = getOneFlagArgumentor();
        argumentor.parseArguments(getSimpleBooleanArg());
        boolean cArg = argumentor.getBooleanArgument("f");
    }

    private String[] getSimpleBooleanArg() {
        String[] args = new String[1];
        args[0] = "-cb";
        return args;
    }




}