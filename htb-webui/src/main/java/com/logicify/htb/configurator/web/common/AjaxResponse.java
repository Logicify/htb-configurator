package com.logicify.htb.configurator.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Sergey Tityenok
 * Date: 8/21/13
 * Time: 7:58 PM
 */
public class AjaxResponse {
    public static final Integer CODE_INTERNAL_ERROR = 500;
    public static final Integer CODE_BAD_REQUEST = 400;
    public static final Integer CODE_ACCESS_DENIED = 403;
    public static final Integer CODE_NOT_FOUND = 404;
    public static final Integer CODE_REDIRECT = 302;
    public static final Integer CODE_OK = 200;

    private static final Map<Integer, String> ERROR_CODE_TO_MESSAGE = new HashMap<>();

    private boolean success;
    private Object targetObject;
    private Integer code;
    private String errorMessage;

    static {
        ERROR_CODE_TO_MESSAGE.put(CODE_NOT_FOUND, "Not found");
        ERROR_CODE_TO_MESSAGE.put(CODE_BAD_REQUEST, "Bad request");
        ERROR_CODE_TO_MESSAGE.put(CODE_ACCESS_DENIED, "Access denied");
        ERROR_CODE_TO_MESSAGE.put(CODE_INTERNAL_ERROR, "Internal error");
    }

    public boolean isSuccess() {
        return success;
    }

    public AjaxResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public AjaxResponse setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public AjaxResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AjaxResponse setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public static AjaxResponse success() {
        return success(null);
    }

    public static AjaxResponse success(Object targetObject) {
        return new AjaxResponse()
                .setCode(CODE_OK)
                .setSuccess(true)
                .setTargetObject(targetObject);
    }

    public static AjaxResponse internalError(String errorMessage) {
        return error(CODE_INTERNAL_ERROR, errorMessage);
    }

    /**
     * Shortcut method for building error response
     *
     * @param code
     * @param errorMessage
     * @return
     */
    public static AjaxResponse error(int code, String errorMessage) {
        if (errorMessage == null) {
            errorMessage = getErrorMessage(code);
        }
        return new AjaxResponse()
                .setCode(code)
                .setErrorMessage(errorMessage)
                .setSuccess(false);
    }

    public static AjaxResponse error(int code) {
        return error(code, null);
    }

    /**
     * Returns default error message string for given code.
     * If there is no known default error message for given code - returns empty string
     *
     * @param errorCode
     * @return
     */
    public static String getErrorMessage(int errorCode) {
        if (ERROR_CODE_TO_MESSAGE.containsKey(errorCode)) {
            return ERROR_CODE_TO_MESSAGE.get(errorCode);
        }
        return "";
    }
}
