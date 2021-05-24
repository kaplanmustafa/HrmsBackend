package com.mustafakaplan.hrms.core.utilities.results;

public class Result {

    private boolean success;

    private String message;

    public Result(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public Result(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
