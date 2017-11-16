package com.zhenjinzi.util;

import java.io.UnsupportedEncodingException;

public class MD5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("‘MD5’16位MD5：" + getMD516("MD5"));
		// System.out.println("‘MD5’32位MD5：" + getMD532("MD5"));

		String newKey = "102$ZzhcCWAgCOK1+v2382oAUQQ2OFt6ftyCVTxY5lUWhGj9rPEa8nMCsqq+kjohUM1+$abcdefgh";
		try {
			newKey = new String(newKey.getBytes("UTF-8"), "UTF-8");
			String ey="7";
			String str="f4de502e58723e6252e8856d4dc8fc3b";
			System.out.println(str.length());
//			String key = MD5.getMD516(ey);
			String key=MD5.getMD532(ey);
			System.out.println(key.toLowerCase());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getMD516(String input) {

		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
				'E', 'F' };
		try {
			byte[] source = input.getBytes("UTF8");
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
										// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
											// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 8; i++) { // 从第一个字节开始，对 MD5 的每一个字节
											// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
															// >>>
															// 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			String tempstr = new String(str); // 换后的结果转换为字符串
			s = tempstr.substring(0, 16); // 仅获取16位的加密结果
		} catch (Exception e) {
			e.getMessage();
		}
		return s;
	}

	public static String getMD532(String input) {

		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
				'E', 'F' };
		try {
			byte[] source = input.getBytes("UTF8");
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
										// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
											// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
											// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
															// >>>
															// 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串
		} catch (Exception e) {
			e.getMessage();
		}
		return s;
	}

}
