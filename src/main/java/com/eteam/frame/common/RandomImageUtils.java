package com.eteam.frame.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 图片工具类， 图片水印，文字水印，缩放，补白等
 * 
 * @author Carl He
 */
public final class RandomImageUtils {

	private RandomImageUtils() {
	}

	private static final Random random = new Random();

	// private static final String randString =
	// "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串

	private static final String rand0String = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串

	private static final String rand1String = "0123456789";// 随机产生的字符串

	private static final String rand2String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串
	
	private static final String rand3String = "+-×"; 

	/**
	 * 生成随机图片
	 */
	/**
	 * 
	 * @param width 图片宽
	 * @param height 图片高
	 * @param lineSize 干扰线数量
	 * @param stringNum 随机产生字符数量
	 * @param randomString 生成的随机数
	 *            （字符），【注意】
	 * @return
	 */
	public final static BufferedImage getRandcode(int width, int height,
			int lineSize, int stringNum, int StringType,
			StringBuffer randomString) {
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g, width, height);
		}
		
		String randString = null;
		if (StringType == 0) {
			randString = rand0String;
			// 绘制随机字符
			for (int i = 1; i <= stringNum; i++) {
				drowString(g, randomString, i, randString);
			}
		} else if (StringType == 1) {
			randString = rand1String;
			// 绘制随机字符
			for (int i = 1; i <= stringNum; i++) {
				drowString(g, randomString, i, randString);
			}
		} else  if (StringType == 2) {
			randString = rand2String;
			// 绘制随机字符
			for (int i = 1; i <= stringNum; i++) {
				drowString(g, randomString, i, randString);
			}
		} else  if (StringType == 3) {
			randString = rand3String;
			// 绘制随机字符
			drowStringForOperator(g,randomString,randString);
		}
		
		g.dispose();
		
		return image;
	}

	/*
	 * 绘制字符串
	 */
	public final static void drowString(Graphics g, StringBuffer randomString,
			int i, String randString) {
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
				.nextInt(121)));
		String rand = String.valueOf(getRandomString(
				random.nextInt(randString.length()), randString));
		randomString.append(rand);
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
	}
	
	/*
	 * 绘制字符串
	 */
	public final static void drowStringForOperator(Graphics g, StringBuffer randomString, 
			String randString) {
		
		int operateCount = random.nextInt(randString.length()); 
		String rand = String.valueOf(getRandomString(
				operateCount, randString));

		 // 生成一个0-4之间的随机整数operate   
		StringBuffer randomStringForShow =new StringBuffer();
		
        int num1 = random.nextInt(10);   
        int num2 = random.nextInt(10);   
        // 运算结果   
        int result = 0;   

        switch (operateCount) {   
        case 0:   
            result = num1 + num2;   
            break;   
        case 1:   
        	
        	if(num1<num2){
        		int tempNum=num2;
        		num2=num1;
        		num1=tempNum;
        	}
            result = num1 - num2;   
            break;   
        case 2:   
        	while(num1==0){
        		num1 = random.nextInt(10);
        	}
        	while(num2==0){
        		num2 = random.nextInt(10);
        	}
            result = num1 * num2;   
            break;   
        }   
        randomString.append(result);
        randomStringForShow.append(num1);
        randomStringForShow.append(rand);
        randomStringForShow.append(num2);
        randomStringForShow.append("=");
		int len=randomStringForShow.length();
		for(int i=0;i<len;i++){
			g.setFont(getFont());
			g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
					.nextInt(121)));
			g.translate(random.nextInt(3), random.nextInt(3));
			g.drawString( String.valueOf(randomStringForShow.charAt(i)), 13 * i, 16);
		}
	}

	/*
	 * 获得字体
	 */
	public final static Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
	}

	/*
	 * 获得颜色
	 */
	public final static Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/*
	 * 绘制干扰线
	 */
	public final static void drowLine(Graphics g, int width, int height) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	/*
	 * 获取随机的字符
	 */
	public final static String getRandomString(int num, String randString) {
		return String.valueOf(randString.charAt(num));
	}

	public static void main(String[] args) throws IOException {
		int width = 80;//
		int height = 26;//
		int lineSize = 40;//
		int stringNum = 4;//
		StringBuffer randomString = new StringBuffer();
		getRandcode(width, height, lineSize, stringNum, 0, randomString);
		// ImageIO.write(image, "JPEG",
		// response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
	}
}