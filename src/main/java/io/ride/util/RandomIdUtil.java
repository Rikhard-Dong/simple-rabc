package io.ride.util;

import java.util.UUID;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午3:19
 */
public class RandomIdUtil {

    public static String getRandomId(String key) {
        return UUID.nameUUIDFromBytes(key.getBytes()).toString().replace("-", "");
    }
}
