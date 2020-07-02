package com.bn.util;//���������

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TypeExchangeUtil {	
	public static String listToString(List<String[]> list){//���б�����ת�����ַ�������	
		StringBuffer sb=new StringBuffer();           //����һ���ַ�������StringBuufer
		if(list!=null){                                //����б�Ϊ��
			for(int i=0;i<list.size();i++){              //�����б�
				String str[]=list.get(i);                  //���б��ֵ�����ַ���
				for(int j=0;j<str.length;j++){         //�����ַ�������
					sb.append(str[j]+"��");          //���ַ�������StringBuffer
				}
				sb.substring(0,sb.length()-1);      //���������ַ���
				sb.append("#");                     //��StringBuffer���#����
			}
		}
		return sb.toString();            //��StringBuffer���ַ�����ʽ����
	}
	public static List<String[]> strToList(String msg){      //���ַ���ת����List<String[]>����	
		List<String[]> list =new ArrayList<String[]>();      //����һ�����б�
		String []str=msg.split("#");                //���ַ���������#Ϊ��ָ
		for(int i=0;i<str.length;i++){                      //�����ַ�������		
			if(str[i].length()>0)                   //���ַ������ȴ�����
				list.add(str[i].split("��"));//���ַ����ԦǷָ������б���
		} 
		return list;//�����б�
	}	
	public static String listString(List<String> list){//���б����ַ������	
		StringBuffer sb=new StringBuffer();//����һ���ַ�������StringBuufer
		if(list.size()!=0){//���б�ĳ��Ȳ�Ϊ��		
			for(int i=0;i<list.size();i++){//�����б�			
               sb.append(list.get(i)+"#");//���б�Ԫ�غ��#���ź���ӵ��ַ���������
			}
			sb.substring(0, sb.length()-1);//���������ַ���
		}		
		return sb.toString();//���ַ����������ַ�����ʽ����
	}
	//��ȡ��ǰʱ��
	public static String getCurTime(){
		   String curtime=null;//������ǰʱ�䳣���ַ���
		   Calendar   c   =   Calendar.getInstance(); // ʹ��Ĭ��ʱ�������Ի������һ������
	       c.setTime(new   java.util.Date()); //ʹ�ø����� Date ���ô� Calendar ��ʱ��
	       int   year   =   c.get(Calendar.YEAR); //���ظ����������ֶε�ֵ
	       String   month   =   c.get(Calendar.MONTH)+1+""; //���ظ����������ֶε�ֵ
	       String   day   =   c.get(Calendar.DAY_OF_MONTH)+"";  //���ظ����������ֶε�ֵ
	       int   hour   =   c.get(Calendar.HOUR_OF_DAY); //���ظ�������ʱ�ֶε�ֵ
	       int   minute   =   c.get(Calendar.MINUTE); //���ظ����������ֶε�ֵ
	       int   second   =   c.get(Calendar.SECOND);//���ظ����������ֶε�ֵ
	       month=Integer.parseInt(month)<10?("0"+month):month;//����λ����
	       day=Integer.parseInt(day)<10?("0"+day):day;//����λ����
		   curtime=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;//���õ���ʱ����Ϣ��ֵ��ʱ�䳣���ַ���curtime
		   return curtime;//����ʱ�䳣���ַ���
	}
		//������һ��ͼƬ��List<byte[]>ת��ΪString
		public static String ByteToString(byte[] list){
			StringBuffer sb = new StringBuffer();//����һ���µ��ַ�������
					byte str[]=list;//�ñ�����������б���Ϣ
					try {
						String imagestr=new String(str,"ISO-8859-1");//ʹ��ISO-8859-1����������飬����һ���µ�String
						sb.append(imagestr+"#");//��ͼƬ��Ϣ���#���Ų���ӵ��ַ���������
						sb.substring(0, sb.length()-1);//���������ַ���
					} catch (UnsupportedEncodingException e) {//��������쳣����
						e.printStackTrace();
					}
			return sb.toString();//���ַ����������ַ�������ʽ����
		}
		//���ַ�������ת����string
		public static String stringArrayToString(String str[]){//���ַ�������ת����string
			StringBuffer sb = new StringBuffer();//����һ���µ��ַ�������
			if(str!=null){//���ַ�������Ϊ��
				for(String strr : str){//�����ַ�������
					sb.append(strr+"#");//���ַ������#����ӵ��ַ���������
				}
			}
			return sb.toString();//���ַ����������ַ�����ʽ����
		}	
}