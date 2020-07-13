package argumentor;

import java.util.HashMap;
import java.util.Map;

public class Argumentor {

    Map<String, Boolean> booleanFlags = new HashMap<>();

    void addBooleanArgument(String id, String... alternativeNames) {

        booleanFlags.put(id, false);
    }

    public void parseArguments(String[] args) {
        for (String arg : args) {
            if (isMultipleBooleanFlagArg(arg)) {
                setMultipleFlags(arg);
            } else if (arg.charAt(0) == '-' && arg.length() == 2){
                setSingleFlag(arg);
            }
        }
    }

    private void setSingleFlag(String arg) {
        
    }

    private boolean isMultipleBooleanFlagArg(String arg) {
        return (arg.charAt(0) == '-') && (arg.length() > 2);
    }

    private void setMultipleFlags(String arg) {
        String[] splitFlags = arg.substring(1).split("(?!^)");
        for (String flag : splitFlags) {
            booleanFlags.put(flag, true);
        }
    }


    public boolean getBooleanArgument(String c) {
        return booleanFlags.get(c);
    }
}
