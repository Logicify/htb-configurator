package com.logicify.htb.configurator.htb;

public class HTBException extends Exception {
    public static final int WRONG_ARGUMENT_ERROR = 1;
    public static final int INPUT_FROM_FILE_ERROR = 2;
    public static final int OUTPUT_TO_FILE_ERROR = 3;
    int codeOfError;

    public HTBException(String message, int codeOfError) {
        super(message);
        this.codeOfError = codeOfError;
    }

    public HTBException(String message, Exception cause, int codeOfError) {
        super(message, cause);
        this.codeOfError = codeOfError;
    }
}
