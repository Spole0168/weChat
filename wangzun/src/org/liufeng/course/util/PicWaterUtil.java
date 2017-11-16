package org.liufeng.course.util;

import java.awt.*;   
import java.awt.event.*;   
import java.io.*;   
import java.awt.image.*;   
import org.w3c.dom.*;   
import com.sun.image.codec.jpeg.*;   
import javax.imageio.*;  


public class PicWaterUtil {
	/**
	 * 
	 * @param s 水印文字
	 * @param ImgName 源文件路径
	 * @param filePath  生成文件的路径
	 */
	public static  void ImgYin(String s,String ImgName,String filePath){   
		try{   
		   File _file = new File(ImgName);   
		   System.out.println(_file.getAbsolutePath());
		   System.out.println(_file.exists());
		   Image src = ImageIO.read(_file);   
		   int wideth=src.getWidth(null);   
		   int height=src.getHeight(null);   
		   BufferedImage image=new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB);   
		   Graphics g=image.createGraphics();   
		   g.drawImage(src,0,0,wideth,height,null);   
		g.setColor(Color.GREEN);   
		g.setFont(new Font("宋体",Font.PLAIN,30));   
		Font aa=new Font("宋体",Font.PLAIN,30);   
		System.out.println(" 你好啊：  "+wideth+"     "+height);
		g.drawString(s,wideth-320,height-150);   
		g.dispose();   
		FileOutputStream out=new FileOutputStream(filePath);   
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
		encoder.encode(image);   
		out.close();   
		}   
		catch(Exception e){   
		System.out.println(e);   
		e.printStackTrace();
		}   
		} 
	
	public static void main(String[] args) {
//		String 
		PicWaterUtil.ImgYin("刘文刚和P3","youhui.png","D:\\yyy.png");
	}

}
