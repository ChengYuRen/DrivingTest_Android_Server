package com.bn.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IOUtil{ //��������	
	public static byte[]readBytes(DataInputStream din){//�����ж�ȡ�ֽ�	
		byte[]  data=null;//�����ֽ�����
		ByteArrayOutputStream out= new ByteArrayOutputStream(1024); //����һ���µĻ����������ָ����������СΪ1024����
			try {//ѭ����������
				int len =0,temRev =0,size;//�������ȳ��������ճ��ȳ�������С����
				len =din.readInt();//�õ������������ĳ���
				System.out.println("len======"+len);//��ӡ����
				//byte[] buf=new byte[1024];
				byte[] buf=new byte[len-temRev];//������һ���ֽ�����
				while ((size = din.read(buf)) != -1){//���ж�ȡ����				 	
					temRev+=size;
					out.write(buf, 0, size);//д�������
					if(temRev>=len){//�����ճ��ȴ���������					
						break;//ֹͣ����
					}
					buf = new byte[len-temRev];//��buf���¸�ֵ���Դ˽���ѭ��
				}
				data = out.toByteArray();//����������ֽ�������ʽ��ֵ��data
			} catch (IOException e) {//����IO�쳣
				e.printStackTrace();
			}
           finally{
			try {out.close();} catch (IOException e) {e.printStackTrace();}//�ر����������쳣
		}
		return data;//�����ֽ�����
	}
	
	//v2�汾���·���������din.writeUTF();
	public static void writeStr(DataOutputStream dout,String str){//������д���ַ���	
		try{		
			dout.writeUTF("STR");//ʹ�� UTF-8 �޸İ���뽫STRд�������
			dout.writeInt(str.length());//д�볤��Ϊ�ַ�������
			dout.write(str.getBytes());//д������Ϊ�ַ����ı�����ʽ
			dout.flush();//��մ������
		}
		catch(Exception e){//�����쳣		
		  e.printStackTrace();
		}
		finally{		
			 try{dout.flush();}catch(Exception e){e.printStackTrace();}//���������
		 }
	}
	//v2�汾���·�������ȡ�ƶ��豸���ص�bytes
	
	public static void writeBytes(DataOutputStream dout,byte[] data){ //������д���ֽ� 
	try{
		dout.writeUTF("BYTE");//ʹ�� UTF-8 �޸İ���뽫BYTEд�������
		dout.writeInt(data.length);//д�볤��Ϊ�ֽڳ���
		dout.write(data);//д������Ϊ�ֽ�����
		dout.flush();//��մ������
    }catch(Exception e){//�����쳣
	  e.printStackTrace();
     }finally{
    	 try{dout.flush();}catch(Exception e){e.printStackTrace();}//���������
     }	
  }
	public static String readStr(DataInputStream din){//��ȡ����������	
		String str=null;//�����ַ�������
		byte[]  data=null;//�����������ݵ��ֽ�����
		ByteArrayOutputStream out= new ByteArrayOutputStream(1024);  //����һ���µĻ����������ָ����������СΪ1024����
			try {//ѭ����������
				int len =0,temRev =0,size;//�������ȳ��������ճ��ȳ�������С����
				len =din.readInt();//�õ������������ĳ���
				byte[] buf = new byte[len-temRev];//������һ���ֽ�����
				while ((size = din.read(buf)) != -1){//���ж�ȡ����				 	
					temRev+=size;
					out.write(buf, 0, size);//д�������
					if(temRev>=len){//�����ճ��ȴ���������					
						break;//ֹͣ����
					}
					buf = new byte[len-temRev];//��buf���¸�ֵ���Դ˽���ѭ��
				}
				data = out.toByteArray();//����������ֽ�������ʽ��ֵ��data
				str= new String(data,0,len,"utf-8");//��UTF-8���뽫�����ֽ��������ַ���
				str=MyConverter.unescape(str);//���ַ�������
			} catch (IOException e) {//����IO�쳣
				e.printStackTrace();
			}finally{
			try {out.close();} catch (IOException e) {e.printStackTrace();}//�ر���������IO�쳣
		}
		return str;//�����ַ���
	}
}
