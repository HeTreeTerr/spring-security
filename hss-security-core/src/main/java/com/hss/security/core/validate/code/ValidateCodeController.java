/**
 * 
 */
package com.hss.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.hss.security.core.properties.SecurityProperties;

/**
 * @author lenovo
 *
 */
@RestController
public class ValidateCodeController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	
	@GetMapping("/code/image")
	public void creatCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ImageCode imageCode = creatImageCode(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request, response),SESSION_KEY, imageCode);
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}

	private ImageCode creatImageCode(ServletWebRequest request) {
		//获取配置的长宽信息:1、先从浏览器获取长宽信息（加载图片），获取不到读取配置信息，在没有就读取默认信息
	    int WIDTH = ServletRequestUtils.getIntParameter(request.getRequest(),"width",securityProperties.getCode().getImage().getWidth());
	    int HEIGHT = ServletRequestUtils.getIntParameter(request.getRequest(),"height",securityProperties.getCode().getImage().getHeight());
	    
	    logger.info("验证码的宽："+WIDTH+",高："+HEIGHT);
	    logger.info("调用了验证码！！！");
		
		BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		
		String yanzheng= drawRandomUnm((Graphics2D)g,WIDTH,HEIGHT);	
	 	logger.info("验证码="+yanzheng);
	 	
	    return new ImageCode(image, yanzheng, securityProperties.getCode().getImage().getExpireIn());
	}
	
	private String drawRandomUnm(Graphics2D g,int WIDTH,int HEIGHT) {
		// TODO Auto-generated method stub
		//设置背景色
		g.setColor(Color.WHITE);
		g.fillRect(0,0,WIDTH,HEIGHT);
		//设置边框
		g.setColor(Color.BLUE);
		g.drawRect(1,1,WIDTH-2,HEIGHT-2);
		//画干扰线
		g.setColor(Color.GREEN);
		for (int i = 0; i < 5; i++) {
			int x1=new Random().nextInt(WIDTH-1);
			int y1=new Random().nextInt(HEIGHT-1);
			
			int x2=new Random().nextInt(WIDTH-1);
			int y2=new Random().nextInt(HEIGHT-1);
			g.drawLine(x1,y1,x2,y2);
			
			
		}
		//写随机数
		g.setColor(Color.RED);
		g.setFont(new Font("黑体",Font.BOLD, 20));
		String base="abcdefjhigklmnopqrstuvwxyz0123456789";
		int x=10;
		String yanzheng="";
		for (int i = 0; i <securityProperties.getCode().getImage().getLength() ; i++) {
			int degree =new Random().nextInt()%30;
			String ch=base.charAt(new Random().nextInt(base.length()))+"";
			logger.info("ch="+ch);
			
			g.rotate(degree*Math.PI/180, x, 20);
			g.drawString(ch,x,20);
			g.rotate(-degree*Math.PI/180, x, 20);
			x=x+30;
			yanzheng=yanzheng+ch;
		}
		//System.out.println("yanzheng="+yanzheng);
		return yanzheng;
	}

}
