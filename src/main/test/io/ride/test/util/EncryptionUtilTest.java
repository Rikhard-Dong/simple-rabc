package io.ride.test.util;

import io.ride.util.EncryptionUtil;
import org.junit.Test;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午12:05
 */
public class EncryptionUtilTest {
    @Test
    public void md5Test() {
        System.out.println(EncryptionUtil.md5("123456"));
        System.out.println(EncryptionUtil.md5("ABCabc123#"));

    }
 }
