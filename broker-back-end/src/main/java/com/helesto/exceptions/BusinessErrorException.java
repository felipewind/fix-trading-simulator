package com.helesto.exceptions;

public class BusinessErrorException extends Exception {

    private static final long serialVersionUID = 1L;

    private BusinessError businessError;

    public BusinessErrorException(BusinessError businessError) {
        super(businessError.toString());
        this.businessError = businessError;
    }

    public BusinessError getBusinessError() {
        return businessError;
    }

    public void setBusinessError(BusinessError businessError) {
        this.businessError = businessError;
    }

}
