package com.bn.util;//声明包语句

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TypeExchangeUtil {	
	public static String listToString(List<String[]> list){//把列表数据转换成字符串类型	
		StringBuffer sb=new StringBuffer();           //创建一个字符串变量StringBuufer
		if(list!=null){                                //如果列表不为空
			for(int i=0;i<list.size();i++){              //遍历列表
				String str[]=list.get(i);                  //将列表的值赋给字符串
				for(int j=0;j<str.length;j++){         //遍历字符串数组
					sb.append(str[j]+"η");          //将字符串放入StringBuffer
				}
				sb.substring(0,sb.length()-1);      //返回整个字符串
				sb.append("#");                     //在StringBuffer后加#符号
			}
		}
		return sb.toString();            //将StringBuffer以字符串形式返回
	}
	public static List<String[]> strToList(String msg){      //把字符串转换成List<String[]>类型	
		List<String[]> list =new ArrayList<String[]>();      //创建一个新列表
		String []str=msg.split("#");                //将字符串数组以#为界分割开
		for(int i=0;i<str.length;i++){                      //遍历字符串数组		
			if(str[i].length()>0)                   //若字符串长度大于零
				list.add(str[i].split("η"));//将字符串以η分割并添加入列表中
		} 
		return list;//返回列表
	}	
	public static String listString(List<String> list){//将列表以字符串输出	
		StringBuffer sb=new StringBuffer();//创建一个字符串变量StringBuufer
		if(list.size()!=0){//若列表的长度不为零		
			for(int i=0;i<list.size();i++){//遍历列表			
               sb.append(list.get(i)+"#");//将列表元素后加#符号后添加到字符串变量后
			}
			sb.substring(0, sb.length()-1);//返回整个字符串
		}		
		return sb.toString();//将字符串变量以字符串形式返回
	}
	//获取当前时间
	public static String getCurTime(){
		   String curtime=null;//声明当前时间常量字符串
		   Calendar   c   =   Calendar.getInstance(); // 使用默认时区和语言环境获得一个日历
	       c.setTime(new   java.util.Date()); //使用给定的 Date 设置此 Calendar 的时间
	       int   year   =   c.get(Calendar.YEAR); //返回给定日历年字段的值
	       String   month   =   c.get(Calendar.MONTH)+1+""; //返回给定日历月字段的值
	       String   day   =   c.get(Calendar.DAY_OF_MONTH)+"";  //返回给定日历日字段的值
	       int   hour   =   c.get(Calendar.HOUR_OF_DAY); //返回给定日历时字段的值
	       int   minute   =   c.get(Calendar.MINUTE); //返回给定日历分字段的值
	       int   second   =   c.get(Calendar.SECOND);//返回给定日历秒字段的值
	       month=Integer.parseInt(month)<10?("0"+month):month;//不足位补零
	       day=Integer.parseInt(day)<10?("0"+day):day;//不足位补零
		   curtime=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;//将得到的时间信息赋值给时间常量字符串curtime
		   return curtime;//返回时间常量字符串
	}
		//用来将一组图片的List<byte[]>转换为String
		public static String ByteToString(byte[] list){
			StringBuffer sb = new StringBuffer();//创建一个新的字符串变量
					byte str[]=list;//用比特数组接受列表信息
					try {
						String imagestr=new String(str,"ISO-8859-1");//使用ISO-8859-1解码比特数组，构造一个新的String
						sb.append(imagestr+"#");//将图片信息后加#符号并添加到字符串变量后
						sb.substring(0, sb.length()-1);//返回整个字符串
					} catch (UnsupportedEncodingException e) {//捕获编码异常错误
						e.printStackTrace();
					}
			return sb.toString();//将字符串变量以字符串的形式返回
		}
		//将字符串数组转换成string
		public static String stringArrayToString(String str[]){//将字符串数组转换成string
			StringBuffer sb = new StringBuffer();//创建一个新的字符串变量
			if(str!=null){//若字符串数组为空
				for(String strr : str){//遍历字符串数组
					sb.append(strr+"#");//在字符串后加#并添加到字符串变量后
				}
			}
			return sb.toString();//将字符串变量以字符串形式返回
		}	
}