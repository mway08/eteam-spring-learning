package com.eteam.frame.service;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -4781036670556650701L;

    public ServiceException() {
        super();
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}