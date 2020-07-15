package argumentor;


public class ArgumentorException extends RuntimeException {

    private ErrorCode code;
    private String parameter;

    public ArgumentorException(String message) {
        super(message);
    }

    public ArgumentorException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public ArgumentorException(String message, ErrorCode code, String parameter) {
        super(message);
        this.code = code;
        this.parameter = parameter;
    }

    public String errorMessage() {
        switch (code) {
            case UNKNOWN_STRING_ARG:
                return "Could not find String argument " + parameter + "!";
            case UNKNOWN_INT_ARG:
                return "Could not find int argument " + parameter + "!";
            case UNKNOWN_DOUBLE_ARG:
                return "Could not find double argument " + parameter + "!";
            case UNKNOWN_BOOLEAN_ARG:
                return "Could not find boolean argument " + parameter + "!";
            default:
                return "";
        }
    }


    public enum ErrorCode {
        UNKNOWN_STRING_ARG, UNKNOWN_INT_ARG, UNKNOWN_DOUBLE_ARG, UNKNOWN_BOOLEAN_ARG
    }
}
