package com.ecommerce.sb_project.excpetion;

public class APIException extends RuntimeException {
    public static final long productVersionId=1L;

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }


}
