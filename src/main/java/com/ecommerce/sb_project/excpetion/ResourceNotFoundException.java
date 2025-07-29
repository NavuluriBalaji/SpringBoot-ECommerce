package com.ecommerce.sb_project.excpetion;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;


    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String resourceName, String fieldName, String field) {
        super(String.format("%s Not found",resourceName,fieldName,field));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.field = field;
    }

    public ResourceNotFoundException(String message, String resourceName, Long fieldId) {
        super(String.format("%d Not found", resourceName,fieldId,fieldId));
        this.resourceName = resourceName;
        this.fieldId = fieldId;
        this.field = field;
    }

    public ResourceNotFoundException() {
    }
}
