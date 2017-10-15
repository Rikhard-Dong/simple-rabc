package io.ride.servlet;

import io.ride.util.VerifyCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午6:51
 */
@WebServlet("/verify_code")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置样式
        VerifyCodeUtil.VerifyCodeStyle verifyCodeStyle = new VerifyCodeUtil.VerifyCodeStyle();
        verifyCodeStyle.setWidth(125);
        verifyCodeStyle.setHeight(80);
        // 得到图片
        VerifyCodeUtil util = new VerifyCodeUtil(verifyCodeStyle);
        BufferedImage img = util.getVCImg();
        // 将验证码的值存储到session中
        String verifyCode = util.getCodeValue().toString();
        System.out.println("[Verify Code] --> " + verifyCode);
        req.getSession().setAttribute("verifyCode", verifyCode);

        // 输出验证图片
        util.outputImg(resp, img);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
