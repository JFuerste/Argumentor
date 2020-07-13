package argumentor;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgumentorTest {

    @Test
    public void emptyBooleanTest(){
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
    public void singleFlagTest(){
        Argumentor argumentor = getOneFlagArgumentor();
        argumentor.parseArguments(getSimpleBooleanArg());
        boolean cArg = argumentor.getBooleanArgument("c");
        assertTrue(cArg);
    }

    private String[] getSimpleBooleanArg() {
        String[] args = new String[1];
        args[0] = "-cb";
        return args;
    }


}