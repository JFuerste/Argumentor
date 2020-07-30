package argumentor.argwrappers;

import java.util.Optional;

public class IntArgWrapper implements ArgWrapper {
    private Optional<Integer> data;

    @Override
    public void setArgument(String argParameter) {
        data = Optional.of(Integer.parseInt(argParameter));
    }

    public Optional<Integer> getIntArgument() {
        return data;
    }
}
