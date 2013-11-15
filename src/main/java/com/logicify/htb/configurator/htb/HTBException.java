package com.logicify.htb.configurator.htb;

/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 11/15/13
 * Time: 12:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class HTBException extends Exception {
    public static final int WRONG_ARGUMENT_ERROR=4;
    public static final int INPUT_FROM_FILE_ERROR=5;
    public static final int OUTPUT_TO_FILE_ERROR=6;
    int codeOfError;

    public HTBException(String message,int codeOfError){
        super(message);
        this.codeOfError=codeOfError;
    }

    public HTBException(String message,Exception cause,int codeOfError){
        super(message,cause);
        this.codeOfError=codeOfError;
    }
}
