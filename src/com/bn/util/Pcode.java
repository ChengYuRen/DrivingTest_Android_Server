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
	{   // ���ֽ������ַ�������Base64���벢����ͼƬ
		if (imgStr == null) // ͼ������Ϊ��
		{
			return false;
		}
		Base64.Decoder decoder= Base64.getDecoder();
		//BASE64Decoder decoder = new BASE64Decoder();
		try 
		{
		// Base64����
			//byte[] b = decoder.decodeBuffer(imgStr);
		byte[] b = decoder.decode(imgStr);
		for (int i = 0; i < b.length; ++i) {
		if (b[i] < 0) {// �����쳣����
		b[i] += 256;
		}
		}
		// ����jpegͼƬ
		String imgFilePath = "STTP/"+cid+".png";// �����ɵ�ͼƬ
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
	
	
	public static String GetImageStr(String imgpath) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
		String imgFile = imgpath;// �������ͼƬ
		InputStream in = null;
		byte[] data = null;
		// ��ȡͼƬ�ֽ�����
		try {
		in = new FileInputStream(imgFile);
		data = new byte[in.available()];
		in.read(data);
		in.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		// ���ֽ�����Base64����
		//BASE64Encoder encoder = new BASE64Encoder();
		Base64.Encoder encoder=Base64.getEncoder();
		return String.valueOf(encoder.encode(data));// ����Base64��������ֽ������ַ���
		//return String.valueOf(encoder.encode(data));
		}
	public static byte[] GetImageStrToPad(String imgpath) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
		String imgFile = imgpath;// �������ͼƬ
		InputStream in = null;
		byte[] data = null;
		// ��ȡͼƬ�ֽ�����
		try {
		in = new FileInputStream(imgFile);
		data = new byte[in.available()];
		in.read(data);
		in.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		// ���ֽ�����Base64����
	//	BASE64Encoder encoder = new BASE64Encoder();
		return data;// ����Base64��������ֽ������ַ���
		}
	
	public static boolean GenerateImageToUser(String imgStr,String cid) 
	{   // ���ֽ������ַ�������Base64���벢����ͼƬ
		if (imgStr == null) // ͼ������Ϊ��
		{
			return false;
		}
		//BASE64Decoder decoder = new BASE64Decoder();
		Base64.Decoder decoder= Base64.getDecoder();
		try 
		{
		// Base64����
		//byte[] b = decoder.decodeBuffer(imgStr);
			byte[] b = decoder.decode(imgStr);

		for (int i = 0; i < b.length; ++i) {
		if (b[i] < 0) {// �����쳣����
		b[i] += 256;
		}
		}
		// ����jpegͼƬ
		String imgFilePath = "YHTP/"+cid+".png";// �����ɵ�ͼƬ
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
