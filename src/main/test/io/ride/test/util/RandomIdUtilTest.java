package io.ride.test.util;

import io.ride.util.RandomIdUtil;
import org.junit.Test;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午3:21
 */
public class RandomIdUtilTest {
    @Test
    public void getRandomIdTest() {
        System.out.println(RandomIdUtil.getRandomId(String.valueOf(System.currentTimeMillis() + "user1@gmail.com")));
    }
}
