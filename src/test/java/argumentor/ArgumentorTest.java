package argumentor;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

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

    @Test(expected = ArgumentorException.class)
    public void parseNonExistentFlag() {
        Argumentor argumentor = new Argumentor();
        argumentor.parseArguments(getSimpleBooleanArg());
    }

    @Test
    public void verboseBooleanArg() {
        Argumentor argumentor = new Argumentor();
        argumentor.addBooleanArgument("verbose");
        argumentor.parseArguments(getLongBooleanArg());
        boolean verboseArg = argumentor.getBooleanArgument("verbose");
        assertTrue(verboseArg);
    }

    @Test
    public void stringArgument() {
        Argumentor argumentor = new Argumentor();
        argumentor.addStringArgument("argument");
        argumentor.parseArguments(getStringArg());
        Optional<String> strArg = argumentor.getStringArgument("argument");
        assertTrue(strArg.isPresent());
        assertEquals("param", strArg.get());
    }

    private String[] getStringArg() {
        String[] args = new String[2];
        args[0] = "--argument";
        args[1] = "param";
        return args;
    }

    private String[] getSimpleBooleanArg() {
        String[] args = new String[1];
        args[0] = "-c";
        return args;
    }

    private String[] getLongBooleanArg() {
        String[] args = new String[1];
        args[0] = "--verbose";
        return args;
    }

    @Test
    public void multipleArguments() {
        Argumentor argumentor = new Argumentor();
        argumentor.addStringArgument("argument");
        argumentor.addBooleanArgument("verbose");

        String[] args = new String[3];
        args[0] = "--verbose";
        args[1] = "--argument";
        args[2] = "param";

        argumentor.parseArguments(args);

        Optional<String> strArg = argumentor.getStringArgument("argument");
        assertTrue(strArg.isPresent());
        assertEquals(strArg.get(), "param");
        boolean verboseArg = argumentor.getBooleanArgument("verbose");
        assertTrue(verboseArg);

    }

    @Test
    public void intArgument() {
        Argumentor argumentor = new Argumentor();
        argumentor.addIntArgument("size");
        String[] args = new String[2];
        args[0] = "--size";
        args[1] = "10";

        argumentor.parseArguments(args);

        Optional<Integer> size = argumentor.getIntegerArgument("size");
        assertTrue(size.isPresent());
        assertEquals(Optional.of(10), size);


    }


}