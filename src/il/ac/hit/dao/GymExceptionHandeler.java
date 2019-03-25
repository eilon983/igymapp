package il.ac.hit.dao;

import org.apache.log4j.spi.ErrorCode;

public class GymExceptionHandeler extends Exception {
    private static final long serialVersionUID = 7718828512143293558L;

    public GymExceptionHandeler(String message, Throwable cause) {
        super(message, cause);
    }
    public GymExceptionHandeler(String message) {
        super(message);
    }

}
