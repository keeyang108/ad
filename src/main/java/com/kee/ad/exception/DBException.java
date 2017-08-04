package com.kee.ad.exception;

/**
 * Created by Kee on 2016/10/30.
 */
public class DBException extends Throwable {

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
