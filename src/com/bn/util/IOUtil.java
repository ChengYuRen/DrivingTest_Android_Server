package com.bn.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IOUtil{ //创建主类	
	public static byte[]readBytes(DataInputStream din){//从流中读取字节	
		byte[]  data=null;//声明字节数组
		ByteArrayOutputStream out= new ByteArrayOutputStream(1024); //创建一个新的缓冲输出流，指定缓冲区大小为1024比特
			try {//循环接受数据
				int len =0,temRev =0,size;//声明长度常量，接收长度常量，大小常量
				len =din.readInt();//得到数据输入流的长度
				System.out.println("len======"+len);//打印长度
				//byte[] buf=new byte[1024];
				byte[] buf=new byte[len-temRev];//创建另一个字节数组
				while ((size = din.read(buf)) != -1){//若有读取内容				 	
					temRev+=size;
					out.write(buf, 0, size);//写入输出流
					if(temRev>=len){//若接收长度大于流长度					
						break;//停止接收
					}
					buf = new byte[len-temRev];//给buf重新赋值，以此进行循环
				}
				data = out.toByteArray();//将输出流以字节数组形式赋值给data
			} catch (IOException e) {//捕获IO异常
				e.printStackTrace();
			}
           finally{
			try {out.close();} catch (IOException e) {e.printStackTrace();}//关闭流并捕获异常
		}
		return data;//返回字节数组
	}
	
	//v2版本的新方法，代替din.writeUTF();
	public static void writeStr(DataOutputStream dout,String str){//向流中写入字符串	
		try{		
			dout.writeUTF("STR");//使用 UTF-8 修改版编码将STR写入输出流
			dout.writeInt(str.length());//写入长度为字符串长度
			dout.write(str.getBytes());//写入内容为字符串的比特形式
			dout.flush();//清空此输出流
		}
		catch(Exception e){//捕获异常		
		  e.printStackTrace();
		}
		finally{		
			 try{dout.flush();}catch(Exception e){e.printStackTrace();}//必须清空流
		 }
	}
	//v2版本的新方法，读取移动设备传回的bytes
	
	public static void writeBytes(DataOutputStream dout,byte[] data){ //向流中写入字节 
	try{
		dout.writeUTF("BYTE");//使用 UTF-8 修改版编码将BYTE写入输出流
		dout.writeInt(data.length);//写入长度为字节长度
		dout.write(data);//写入内容为字节数组
		dout.flush();//清空此输出流
    }catch(Exception e){//捕获异常
	  e.printStackTrace();
     }finally{
    	 try{dout.flush();}catch(Exception e){e.printStackTrace();}//必须清空流
     }	
  }
	public static String readStr(DataInputStream din){//读取数据输入流	
		String str=null;//声明字符串常量
		byte[]  data=null;//声明保存数据的字节数组
		ByteArrayOutputStream out= new ByteArrayOutputStream(1024);  //创建一个新的缓冲输出流，指定缓冲区大小为1024比特
			try {//循环接受数据
				int len =0,temRev =0,size;//声明长度常量，接收长度常量，大小常量
				len =din.readInt();//得到数据输入流的长度
				byte[] buf = new byte[len-temRev];//创建另一个字节数组
				while ((size = din.read(buf)) != -1){//若有读取内容				 	
					temRev+=size;
					out.write(buf, 0, size);//写入输出流
					if(temRev>=len){//若接收长度大于流长度					
						break;//停止接收
					}
					buf = new byte[len-temRev];//给buf重新赋值，以此进行循环
				}
				data = out.toByteArray();//将输出流以字节数组形式赋值给data
				str= new String(data,0,len,"utf-8");//以UTF-8编码将数据字节流存入字符串
				str=MyConverter.unescape(str);//将字符串译码
			} catch (IOException e) {//捕获IO异常
				e.printStackTrace();
			}finally{
			try {out.close();} catch (IOException e) {e.printStackTrace();}//关闭流并捕获IO异常
		}
		return str;//返回字符串
	}
}
