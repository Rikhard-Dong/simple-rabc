package io.ride.exception;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午8:52
 * 非法请求异常
 */
public class IllegaRequestException extends Exception {
    public IllegaRequestException(String message) {
        super(message);
    }
}
