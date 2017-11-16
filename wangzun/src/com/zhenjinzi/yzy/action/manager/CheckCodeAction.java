package com.zhenjinzi.yzy.action.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zhenjinzi.yzy.action.BaseAction;

@Controller
@Action(
value="check-code",results={@Result(name="index", location = "/alipay/index.jsp"),
	@Result(name="success",type="stream",
			params={"inputName","input","contentType","image/jpeg"}
			)})
public class CheckCodeAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InputStream input;

	private static int WIDTH = 120;

	private static int HEIGHT = 40;

	private static int NUM = 4;

	private static char[] seq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
			'J', 'K',  'M', 'N',  'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', '0',  '2', '3', '4', '5', '6', '7', '8',
			'9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z'
			};

	public String execute() {
		byte[] image = randomImage();
		input = new ByteArrayInputStream(image);
		response.reset();
		ServletOutputStream out=null;
		try {
			 out=response.getOutputStream();
			byte[] buffer=new byte[1024];
			int len;
			while((len=input.read(buffer))!=-1){
				out.write(buffer, 0, len);
			}
//			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(out!=null){
				out.flush();
				out.close();
				out=null;}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	/**
	 * 
	 * @return
	 */
	private byte[] randomImage() {
		Random r = new Random();

		// 图片的内存映像
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		// 获得画笔对象
		Graphics g = image.getGraphics();
		g.setColor(randomColor(r));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(0, 0, 0));

		// 用于存储随机生成的验证码
		StringBuffer number = new StringBuffer();

		// 绘制验证码
		for (int i = 0; i < NUM; i++) {
			g.setColor(randomColor(r));
			//去掉这个随机高度
			//int h = (int) ((HEIGHT * 60 / 100) * r.nextDouble() + (HEIGHT * 30 / 100));
			int h = (int) (HEIGHT * 90 / 100);
			g.setFont(new Font(null, Font.BOLD | Font.ITALIC, h));
			String ch = String.valueOf(seq[r.nextInt(seq.length)]);
			number.append(ch);
			g.drawString(ch, i * WIDTH / NUM * 90 / 100, h);
		}

		 httpSession.setAttribute("checkCode", number.toString());
		 System.out.println(number.toString());

		// 绘制干扰线
		for (int i = 0; i <=3; i++) {
			g.setColor(randomColor(r));
			g.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r
					.nextInt(HEIGHT));

		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);

		// 把BufferedImage对象中的图像信息编码后
		// 向创建该对象(encoder)时指定的输出流输出
		try {
			encoder.encode(image);
			return os.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private Color randomColor(Random r) {
		return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

//	public void setSession(Map<String, Object> arg0) {
//
//		this.session=arg0;
//	}
	public static String getRandomPassword(){
		Random r = new Random();
		StringBuffer number = new StringBuffer();
		for (int i = 0; i < NUM+4; i++) {
			String ch = String.valueOf(seq[r.nextInt(seq.length)]);
			number.append(ch);
		}
		return number.toString();
	}


}
