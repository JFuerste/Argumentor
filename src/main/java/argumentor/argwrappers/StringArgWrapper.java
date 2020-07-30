package argumentor.argwrappers;

import java.util.Optional;

public class StringArgWrapper implements ArgWrapper {
    private Optional<String> data = Optional.empty();

    @Override
    public void setArgument(String argParameter) {
        data = Optional.of(argParameter);
    }

    public Optional<String> getStringArg() {
        return data;
    }
}
