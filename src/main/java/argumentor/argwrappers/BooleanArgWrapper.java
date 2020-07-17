package argumentor.argwrappers;

public class BooleanArgWrapper implements ArgWrapper {
    boolean data = false;

    @Override
    public void setArgument(String argParameter) {
        data = true;
    }

    public boolean getBooleanArg() {
        return data;
    }
}
