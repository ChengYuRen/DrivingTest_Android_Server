package com.bn.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//import java.util.Base64;

public class Pcode 
{
	public static boolean GenerateImage(String imgStr,String cid) 
	{   // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
		{
			return false;
		}
		Base64.Decoder decoder= Base64.getDecoder();
		//BASE64Decoder decoder = new BASE64Decoder();
		try 
		{
		// Base64解码
			//byte[] b = decoder.decodeBuffer(imgStr);
		byte[] b = decoder.decode(imgStr);
		for (int i = 0; i < b.length; ++i) {
		if (b[i] < 0) {// 调整异常数据
		b[i] += 256;
		}
		}
		// 生成jpeg图片
		String imgFilePath = "STTP/"+cid+".png";// 新生成的图片
		OutputStream out = new FileOutputStream(imgFilePath);
		out.write(b);
		out.flush();
		out.close();
		return true;
		} 
		catch (Exception e) {
		return false;
		}
	}
	
	
	public static String GetImageStr(String imgpath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = imgpath;// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
		in = new FileInputStream(imgFile);
		data = new byte[in.available()];
		in.read(data);
		in.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		// 对字节数组Base64编码
		//BASE64Encoder encoder = new BASE64Encoder();
		Base64.Encoder encoder=Base64.getEncoder();
		return String.valueOf(encoder.encode(data));// 返回Base64编码过的字节数组字符串
		//return String.valueOf(encoder.encode(data));
		}
	public static byte[] GetImageStrToPad(String imgpath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = imgpath;// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
		in = new FileInputStream(imgFile);
		data = new byte[in.available()];
		in.read(data);
		in.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		// 对字节数组Base64编码
	//	BASE64Encoder encoder = new BASE64Encoder();
		return data;// 返回Base64编码过的字节数组字符串
		}
	
	public static boolean GenerateImageToUser(String imgStr,String cid) 
	{   // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
		{
			return false;
		}
		//BASE64Decoder decoder = new BASE64Decoder();
		Base64.Decoder decoder= Base64.getDecoder();
		try 
		{
		// Base64解码
		//byte[] b = decoder.decodeBuffer(imgStr);
			byte[] b = decoder.decode(imgStr);

		for (int i = 0; i < b.length; ++i) {
		if (b[i] < 0) {// 调整异常数据
		b[i] += 256;
		}
		}
		// 生成jpeg图片
		String imgFilePath = "YHTP/"+cid+".png";// 新生成的图片
		OutputStream out = new FileOutputStream(imgFilePath);
		out.write(b);
		out.flush();
		out.close();
		return true;
		} 
		catch (Exception e) {
		return false;
		}
	}

}
