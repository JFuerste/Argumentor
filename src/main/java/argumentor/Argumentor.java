package argumentor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Argumentor {

    Map<String, Boolean> booleanFlags = new HashMap<>();

    void addBooleanArgument(String id, String... alternativeNames) {
        booleanFlags.put(id, false);
    }


    public void parseArguments(String[] args) {
        Iterator<String> iterator = Arrays.asList(args).iterator();

        while (iterator.hasNext()) {
            String arg = iterator.next();
            if (isShortFlag(arg) && arg.length() > 2) {
                //Split string into single characters. Regex prevents having an empty
                //String as first element of the array.
                String[] splitFlags = arg.substring(1).split("(?!^)");
                for (String flag : splitFlags) {
                    setBooleanArgIfExists(flag);
                }
            } else if (isShortFlag(arg) && arg.length() == 2) {
                arg = arg.substring(1); //Remove leading dash
                setBooleanArgIfExists(arg);

            }
        }
    }

    private void setBooleanArgIfExists(String arg) {
        if (booleanFlags.containsKey(arg)) {
            booleanFlags.put(arg, true);
        } else { // Arg is Shortflag, but not boolean
            throw new ArgumentorException("Unknown argument " + arg + " of type Boolean!",
                    ArgumentorException.ErrorCode.UNKNOWN_BOOLEAN_ARG,
                    arg);
        }
    }

    private boolean isShortFlag(String arg) {
        return arg != null && arg.charAt(0) == '-';
    }


    private void setSingleFlag(String arg) {

    }


    public boolean getBooleanArgument(String arg) throws ArgumentorException {
        try {
            return booleanFlags.get(arg);
        } catch (Exception e) {
            throw new ArgumentorException("Unknown argument " + arg + " of type Boolean!",
                    ArgumentorException.ErrorCode.UNKNOWN_BOOLEAN_ARG,
                    arg);
        }
    }
}
