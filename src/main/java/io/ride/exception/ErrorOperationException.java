package io.ride.exception;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午8:50
 *
 * 错误操作异常
 */
public class ErrorOperationException extends Exception {
    public ErrorOperationException(String message) {
        super(message);
    }
}
