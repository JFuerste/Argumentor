package argumentor;

import argumentor.argwrappers.ArgWrapper;
import argumentor.argwrappers.BooleanArgWrapper;
import argumentor.argwrappers.StringArgWrapper;

import java.util.*;

public class Argumentor {

    Map<String, ArgWrapper> argMap = new HashMap<>();

    void addBooleanArgument(String id, String... alternativeNames) {
        argMap.put(id, new BooleanArgWrapper());
    }

    void addStringArgument(String id, String... alternativeNames) {
        argMap.put(id, new StringArgWrapper());
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
            } else {
                arg = removeDashes(arg);
                var argWrapper = argMap.get(arg);
                if (argWrapper instanceof BooleanArgWrapper) {
                    argWrapper.setArgument("");
                } else if (iterator.hasNext()) {
                    argWrapper.setArgument(iterator.next());
                } else {
                    throw new ArgumentorException("Used Non-Boolean Argument without value");
                }
            }
        }
    }

    private boolean isShortFlag(String arg) {
        return arg != null && arg.charAt(0) == '-' && arg.charAt(1) != '-';
    }

    private String removeDashes(String arg) {
        if (isShortFlag(arg)) {
            //Remove leading dash
            arg = arg.substring(1);
        } else if (isLongFlag(arg)) {
            arg = arg.substring(2);
        } else {
            throw new ArgumentorException("Could not parse argument " + arg);
        }
        return arg;
    }



    private void setBooleanArgIfExists(String arg) {
        if (argMap.containsKey(arg)) {
            ArgWrapper boolArgWrapper = argMap.get(arg);
            boolArgWrapper.setArgument(""); //Sets argument to true
        } else { // Arg is Shortflag, but not boolean
            throw new ArgumentorException("Unknown argument " + arg + " of type Boolean!",
                    ArgumentorException.ErrorCode.UNKNOWN_BOOLEAN_ARG,
                    arg);
        }
    }

    private boolean isLongFlag(String arg) {
        return arg != null && arg.charAt(0) == '-' && arg.charAt(1) == '-';
    }


    public boolean getBooleanArgument(String arg) throws ArgumentorException {
        try {
            BooleanArgWrapper argWrapper = (BooleanArgWrapper) argMap.get(arg);
            return argWrapper.getBooleanArg();
        } catch (Exception e) {
            throw new ArgumentorException("Unknown argument " + arg + " of type Boolean!",
                    ArgumentorException.ErrorCode.UNKNOWN_BOOLEAN_ARG,
                    arg);
        }
    }

    public Optional<String> getStringArgument(String arg) {
        try {
            StringArgWrapper argWrapper = (StringArgWrapper) argMap.get(arg);
            return argWrapper.getStringArg();
        } catch (Exception e) {
            throw new ArgumentorException("Unknown argument " + arg + " of type String!",
                    ArgumentorException.ErrorCode.UNKNOWN_BOOLEAN_ARG,
                    arg);
        }
    }
}
