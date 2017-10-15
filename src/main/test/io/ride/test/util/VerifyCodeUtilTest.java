package io.ride.test.util;

import io.ride.util.VerifyCodeUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午5:26
 */
public class VerifyCodeUtilTest {
    @Test
    public void getVCImgTest() {
        BufferedImage bufferedImage = (new VerifyCodeUtil(new VerifyCodeUtil.VerifyCodeStyle())).getVCImg();
        System.out.println(bufferedImage);
        File file = new File("/home/ride/桌面/VCCode.jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
