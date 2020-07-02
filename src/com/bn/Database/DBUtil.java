package com.bn.Database;
import java.io.File;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bn.util.Constant;
import com.bn.util.Pcode;

public class DBUtil {

	public static Connection getConnection()
	{		  //��д�����ݿ⽨�����ӵķ���
		Connection con = null;                         //��������
		try{
			Class.forName(Constant.MySQL_Driver_Version_8);	//��������
			con=DriverManager.getConnection(Constant.MySQL_Connection_URL,
					Constant.MySQL_Root_User,Constant.MySQL_Root_Password);
		}catch(Exception e){
			e.printStackTrace();             //�����쳣
		}
		return con;                           //��������
	}
	//===================���ͱ�begin======================

	//ͨ��id�ҵ���Ӧ�ĳ��͵�������Ϣ

	public static List<String[]> getcarById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String[] str=new String[3];
		List<String[]> list = new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select c_name,c_jiazhao ,c_miaoshu from car where c_id='"+string+"';";
			//System.out.println(task);
			rs=st.executeQuery(task);
			while(rs.next())
			{
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);

			}
			list.add(str);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}

	//����������� ��С��
	@SuppressWarnings("unused")
	private static Object ADDCARTYPE=new Object();

	public static String addCarType(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into car values('" +getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','"  +getmsg[4]+ "');";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//ɾ����������
	private static Object DELCARTYPE=new Object();
	public static String delCarType(String id){
		synchronized(DELCARTYPE){
			Connection con=getConnection();
			Statement st=null;
			try{
				st=con.createStatement();
				String sql="delete from car where c_id='"+id+"';";
				st.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally{
				try{st.close();}catch(Exception e){e.printStackTrace();}
				try{con.close();}catch(Exception e){e.printStackTrace();}
			}
		}
		return "ok";
	}
	//������ѡ����
	@SuppressWarnings("unused")
	private static Object UPDATECARTYPE=new Object();
	public static String updateCarType(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update car set c_name='" +getmsg[2]+"',";
			sql+="c_jiazhao='"+getmsg[3]+"',";
			sql+="c_miaoshu='"+getmsg[4]+"'";
			sql+="where c_id='"+getmsg[1]+"';";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ�������Ϣ B�� ID��ʲô��
	public static List<String[]> getCarType()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select* from car;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.carsxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�õ���������
	public static List<String[]> getCarTypeName()
	{
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			st = con.createStatement();
			String task = "select c_name from car;";
			rs = st.executeQuery(task);
			while (rs.next()) {
				String[] str = new String[1];
				for (int i = 0; i < str.length; i++) {
					str[i] = rs.getString(i + 1);
				}
				list.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//�õ�����ID���ֵ
	public static String getCarTypeMaxId()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(c_id) from car;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//�õ�����ID
	public static String[] getCarTypeId()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		int count=getCount("car");
		String[] str=new String[count];
		try{
			st=con.createStatement();
			String task="select c_id from car;";
			rs=st.executeQuery(task);
			int k=0;
			while(rs.next())
			{
				str[k]=rs.getString(1);
				k++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//ͨ�����������Ƶõ�������id
	public static String getCarTypeIdByName(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select c_id from car where c_name='"+string+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�ܹ�����ID�õ�����
	public static String getCarTypeNameById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select c_name from car where c_id='"+string+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return str;
	}

	//==================���ͱ�END=====================
	//====================����Աbegin==========================

	//��ӹ���Ա
	public static String addAdminer(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into adminer values('" +getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','" +getmsg[4]+ "','" +getmsg[5]+ "','" +"��"+"');";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//���Ĺ���Ա��Ϣ
	public static String updateAdminer(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update adminer set ad_name='" +getmsg[1]+"',";
			sql+="ad_password='"+getmsg[2]+"',";
			sql+="ad_sex='"+getmsg[3]+"',";
			sql+="ad_phone='"+getmsg[4]+"'";
			sql+="where ad_id='"+getmsg[0]+"';";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//ɾ������Ա
	public static String delAdminer(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from adminer where ad_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//�õ����й���Ա���id
	public static String getAdminerMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(ad_id) from adminer;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}

	//ͨ��id�ҵ���Ӧ�Ĺ���Ա��������Ϣ
	public static List<String[]> getAdminerdById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String[] str=new String[4];
		List<String[]> list = new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select ad_name,ad_sex,ad_phone,ad_zaizhi from adminer where ad_id='"+string+"';";
			//System.out.println(task);
			rs=st.executeQuery(task);
			while(rs.next())
			{
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
			}
			list.add(str);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//��ѯ���й���Ա
	public static List<String[]> getAdminer()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select ad_id,ad_name,ad_sex,ad_phone,ad_zaizhi from adminer;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.adminersxlen-1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//���ݹ���Աid  ���ҹ���Ա����
	public static String pwdAdminer(String str)
	{
		Connection con=getConnection();
		Statement st = null;
		ResultSet rs = null;
		String info=null;
		try{
			st=con.createStatement();
			String sql="select ad_password from adminer where ad_id='"+str+"'";
			rs=st.executeQuery(sql);
			rs.next();
			info=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return info;
	}
	//���ݹ���Աid  �ҵ�����Ա�Ƿ���ְ  �Ľ��
	public static String getZaizhiByAdminer(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select ad_zaizhi from adminer where ad_id='"+string+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//���Ĺ���Ա�Ƿ���ְ��Ϣ
	public static String updateAdminerzz(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update adminer set ad_zaizhi='" +getmsg[2]+"'";
			sql+="where ad_id='"+getmsg[1]+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//======================����ԱEND=================================
	//=====================��Уbegin====================================
	//���Ӽ�У
	public static String addDrivesc(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into drivesc values('" +getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','" +getmsg[4]+ "','"+getmsg[5]+ "');";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//���ļ�У��Ϣ
	public static String updateDrivesc(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update drivesc set ds_name='" +getmsg[2]+"',";
			sql+="ds_jianjie='"+getmsg[3]+"',";
			sql+="ds_phone='"+getmsg[4]+"',";
			sql+="ds_address='"+getmsg[5]+"'";
			sql+="where ds_id='"+getmsg[1]+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//����idɾ����У
	public static String delDrivesc(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from drivesc where ds_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//��������Уid
	public static String getDrivescMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(ds_id) from drivesc;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//����id�õ���Ӧ��У������Ϣ
	public static List<String []> getDrivescdById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String []> list=new ArrayList<String []>();
		String[] str=new String[4];
		try{
			st=con.createStatement();
			String task="select ds_name,ds_jianjie,ds_phone,ds_address from drivesc where ds_id='"+string+"';";
			System.out.println(task);
			rs=st.executeQuery(task);
			while(rs.next())
			{
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
				//str[4]=rs.getString(5);
			}
			list.add(str);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//��ѯ�������м�У
	public static List<String[]> getDrivesc()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from drivesc;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.drivescsxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�ܹ���УID�õ���У����
	public static String getDriverNameById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select ds_name from drivesc where ds_id='"+string+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return str;
	}
	//��ѯ��У����������
	public static List<String[]> getDrivescCity()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select distinct ds_address from drivesc;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//��ѯ���м�У�ĸ�������
	public static List<String[]> getDrivescCityName(String city)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select ds_name from drivesc where ds_address='"+city+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//=======================��УEND=================================
	//===========================�������begin================================
	//����������
	public static String addTieclass(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into tieclass values('" +getmsg[1]+ "','"+getmsg[2]+ "');";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�����������
	public static String updateTieclass(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update tieclass set tc_name='" +getmsg[2]+"'";
			sql+="where tc_id='"+getmsg[1]+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//ɾ���������
	public static String delTieclass(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from tieclass where tc_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ�������id
	public static String getTieclassMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(tc_id) from tieclass;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//����id�������
	public static String getTieclassNameById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select tc_name from tieclass where tc_id='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�����������ѯ���id
	public static String getTieclassidByName(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select tc_id from tieclass where tc_name='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//��ѯ���������������
	public static List<String[]> getTieclass()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from tieclass;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.tieclasssxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//==============================�������END==============================
	//================================�û���begin================================
	//�����û�
	public static String addUsersb(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into usersb values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','" +getmsg[4]+ "','"
					+getmsg[5]+"','"+getmsg[6]+"','"+getmsg[7]+"','" +getmsg[8]+ "','"
					+getmsg[9]+"','"+getmsg[10]+"','"+getmsg[11]+"','"
					+getmsg[12]+ "');";

			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//��ѯ����ֹ���Ե��û�
	public static List<String[]> getuserfb()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select u_id,u_zhanghao,u_ca_id,u_ds_id,u_nicheng,u_age,u_sex,u_phone,u_address from usersb where u_denglu='��'";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.usersbsxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�����û���Ϣ
	public static String updateUsersb(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_ca_id='" +getmsg[1]+"',";
			sql+="u_ca_id='"+getmsg[2]+"',";
			sql+="u_ds_id='"+getmsg[3]+"',";
			sql+="u_password='"+getmsg[4]+"',";
			sql+="u_name='"+getmsg[5]+"',";
			sql+="u_age='"+getmsg[6]+"',";
			sql+="u_sex='"+getmsg[7]+"',";
			sql+="u_phone='"+getmsg[8]+"',";
			sql+="u_address='"+getmsg[9]+"'";
			sql+="where u_id='"+getmsg[0]+"';";

			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�����û�id  �õ��û��ĳ��ͱ��
	public static String getUsersbcaById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_ca_id from usersb where u_id='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�����û�id�õ��û���ѡ�ļ�У���
	public static String getUsersbdsById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_ds_id from usersb where u_id='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�����û��˺���  �õ��û�Id
	public static String getUserIdByZhangHao(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_id from usersb where u_zhanghao='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�����û�ID �õ��û��˺�
	public static String getUserzhanghaoByid(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_zhanghao from usersb where u_id='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�õ������û�
	public static List<String[]> getUsersb()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select u_id,u_zhanghao,u_ca_id,u_ds_id,u_nicheng,u_age,u_sex,u_phone,u_address from usersb;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.usersbsxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//��ֹ��½
	public static String updateUserdlToObsect(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_denglu='" +"��"+"'";
			sql+="where u_id='"+getmst+"';";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//���Ե�¼
	public static String updateUserdlToObsectTow(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_denglu='" +"��"+"'";
			sql+="where u_id='"+getmst+"';";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	public static String updateAdmlToObsect(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update adminer set ad_zaizhi='" +"��"+"'";
			sql+="where ad_id='"+getmst+"';";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//���Ե�¼
	public static String updateAdmlToObsectTow(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update adminer set ad_zaizhi='" +"��"+"'";
			sql+="where ad_id='"+getmst+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�����û��Ƿ������¼
	public static String updateUserdl(String[] getmsg)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_denglu='" +getmsg[1]+"'";
			sql+="where u_id='"+getmsg[0]+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//����id  ��ѯ�κ��Ƿ���Ե�¼��Ϣ
	public static String getDengluByUser(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_denglu from usersb where u_id='"+string+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//����id���û��ǳ�
	public static String getUserNameById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_nicheng from usersb where u_id='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�����ǳƲ��û�id
	public static String getUseridByName(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_id from usersb where u_nicheng='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//=================================�û���END=================================
	//===============================������begin==========================================
	//���һ���µ�����
	public static String addTiemain(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into tiemain values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','" +getmsg[4]+ "','"+getmsg[5]+ "','"
					+getmsg[6]+ "');";
			st.executeUpdate(sql);

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//ɾ����������Ϣ ��������id
	public static String delTiemain(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from tiemain where tm_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ����з�����������
	public static List<String[]> getTiemain()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from tiemain;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.tiemainsxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�����û�id   �õ����û�������
	public static List<String[]> getTiemainbyuser(String user)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from tiemain where tm_user_id='"+user+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.tiemainsxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//���� ��ѡ���  �õ����е�����
	public static List<String[]> getTiemainbyclass(String user)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from tiemain where tm_class_id='"+user+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.tiemainsxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//������ѡ���id  �õ����ӵ�id
	public static String[] gettmidByclass(String s)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		int count=getCount("car");
		String[] str=new String[count];
		try{
			st=con.createStatement();
			String task="select tm_id from tiemain where tm_class_id='"+s+"' ;";
			rs=st.executeQuery(task);
			int k=0;
			while(rs.next())
			{
				str[k]=rs.getString(1);
				k++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�õ����������id
	public static String getTiemainMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(tm_id) from tiemain;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//������ѡ��������id  �õ����ĸ��û��ķ���
	public static String getUserBytiemainid(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select tm_user_id from tiemain where tm_id='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//����id��ѯ������Ŀ
	public static String getTiemainNameById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select tm_title from tiemain where tm_id='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}

	//=================================������END=======================================

	//================================�ظ���begin===============================================
	//��ӻظ�
	public static String addReply(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into reply values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','" +getmsg[4]+ "','"
					+getmsg[5]+ "');";

			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//ɾ��һ���ظ�
	public static String delReply(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from reply where r_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ����еĻظ�
	public static List<String[]> getReply()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from reply;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.replysxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//��������id �õ��ظ�
	public static List<String[]> getReplybyid(String id)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select r_id,r_user_id,r_neirong,r_time from reply where r_tm_id='"+id+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.replysxlen-1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//������������id  ɾ�����еĻظ�
	public static String delReplybyid(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from reply where r_tm_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ����ظ�id
	public static String getReplyMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(r_id) from reply;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}

	//=====================================�ظ���END==========================================
	//===============================�����begin===============================================
	//�������
	public static String addQuestion(String getmsg[])
	{
		//getmsg[4]="C:/Users/Administrator/Desktop/Pcode.java";
		if(!getmsg[4].equals("��"))
		{
			Pcode.GenerateImage(getmsg[4], getmsg[1]);
			getmsg[4]= "STTP/"+getmsg[1]+".png";// �����ɵ�ͼƬ
		}
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into question values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','"  +getmsg[4]+"','"+getmsg[5]+"','"+getmsg[6]+"','"
					+getmsg[7]+ "');";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//ɾ������
	public static String delQuestion(String id)
	{
		deleteFiles("STTP/"+id+".png");//�������ͼƬɾ��
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from question where q_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//����������Ϣ
	public static String updateQuestion(String[] getmsg)
	{
		deleteFiles("STTP/"+getmsg[1]+".png");//�������ͼƬɾ��
		if(!getmsg[4].equals("��"))
		{
			Pcode.GenerateImage(getmsg[4], getmsg[1]);
			getmsg[4]= "STTP/"+getmsg[1]+".png";// �����ɵ�ͼƬ
		}
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update question set ";
			sql+="q_title='"+getmsg[2]+"',";
			sql+="q_subject='"+getmsg[3]+"',";
			sql+="q_image='"+getmsg[4]+"',";
			sql+="q_class='"+getmsg[5]+"',";
			sql+="q_zhangjie='"+getmsg[6]+"',";
			sql+="q_jiexi='"+getmsg[7]+"'";
			sql+="where q_id='"+getmsg[1]+"';";

			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ���������
	public static List<String[]> getQuestion()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from question;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.questionsxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�õ�����id���ֵ
	public static String getQuestionMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(q_id) from question;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//����������Ŀ�õ���������
	public static List<String[]> getQuestionidBysu(String su)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select q_id from question where q_subject='"+su+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//����id�õ���Ŀ��Ϣ
	public static List<String[]> getQuestionByid(String id)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select  q_title,q_subject,q_image,q_class,q_zhangjie,q_jiexi from question where q_id='"+id+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.questionsxlen-1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//==================================�����END============================================

	//==================================�����begin============================================
	//��Ӵ���
	public static String addError(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into error values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','"
					+getmsg[4]+ "');";

			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//ɾ������   ���ݴ���id
	public static String delError(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from error where e_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//����  ����id   ɾ�����������
	public static String delErrorByque(String user)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from error where e_que_id='"+user+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//ɾ���û����д���
	public static String delErrorByu(String user)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from error where e_user_id='"+user+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ���������id
	public static String getErrorMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(e_id) from error;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//�����û�id  �õ��û��Ĵ�����
	public static List<String[]> getErroridByu(String user)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select e_id from error where e_user_id='"+user+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�����û�id  �õ��û��Ĵ��������ı��
	public static List<String[]> gete_que_idByu(String user)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select e_que_id from error where e_user_id='"+user+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�õ���������� �����û�
	public static String getErrorCount(String user)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select count(e_id) from error where e_user_id='"+user+"';";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//=======================================�����END=========================

	//==========================���������begin===========================================
	//�����������
	public static String addQzhengli(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into qzhengli values" +"('"
					+getmsg[1]+ "','"
					+getmsg[2]+ "');";

			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//��������idɾ�����������
	public static String delQzhengli(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from qzhengli where qz_que_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//���ݳ��ͱ���ҵ�����������
	public static List<String[]> getQzhengliqueBycar(String car)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select qz_que_id from qzhengli where qz_car_id='"+car+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�����������ҵ���Ӧ�ĳ��ͱ��
	public static List<String[]> getQzhenglicarByque(String que)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select qz_car_id from qzhengli where qz_que_id='"+que+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//============================���������END==========================================
	//=============================����𰸱�begin================================================
	//��������
	public static String addAnswer(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into answer values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+ "','"+getmsg[3]+ "','"+getmsg[4]+ "','"
					+getmsg[5]+ "');";

			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ�������е����id
	public static String getAnswerMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(a_id) from answer;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//ɾ��ĳ����������д�
	public static String delAnswer(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from answer where a_que_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�ҵ�ĳ������Ĵ�
	public static List<String[]> getAnswerById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select a_select from answer where a_yorn='��' and a_que_id='"+string+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];

				str[0]=rs.getString(1);

				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�����������ҵ������ڵ�ȫ����
	public static List<String[]> getAnswerqByid(String id)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select a_select,a_se_neirong from answer where a_que_id='"+id+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[2];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//��������𰸣��ҳ�ԭ�������  ��֮����  �������µĴ𰸣�
//	public static String updateAnswer(String[] getmsg)
//	{
//
//			Connection con=getConnection();
//			Statement st=null;
//			try{
//				st=con.createStatement();
//				String qid=getmsg[0];
//				String aselect=getAnswerById(qid);
//				String sql="update answer set a_yorn='��' where a_select='"+aselect+"' and a_que_id='"+qid+"';";
//
//				st.executeUpdate(sql);
//				sql="update answer set a_yorn='��' where a_select='"+getmsg[1]+"' and a_que_id='"+qid+"';";
//
//
//				st.executeUpdate(sql);
//			}catch(Exception e){
//				e.printStackTrace();
//				return null;
//			}finally{
//				try{st.close();}catch(Exception e){e.printStackTrace();}
//				try{con.close();}catch(Exception e){e.printStackTrace();}
//			}
//
//		return "ok";
//	}
	//��������ѡ������
	public static String updateAnswerse(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String qid=getmsg[0];
			String qselect=getmsg[1];
			String qsneirong=getmsg[2];
			String sql="update answer set a_se_neirong ='"+qsneirong+"' where a_select='"+qselect+"' and a_que_id='"+qid+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//���ݴ������Ĵ�id �������� ���Ĵ�
	public static String updateAnswerall(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update answer set a_select ='"+getmsg[3]+"',a_se_neirong='"+getmsg[4]+"',a_yorn='"+getmsg[5]+"'"
					+" where a_id='"+getmsg[1]+"';";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//	//��������id �õ���ȷ��ѡ��
//	public static String getAnswerzhengqueeByid(String id){
//		Connection con=getConnection();
//		Statement st=null;
//		ResultSet rs=null;
//		String str=null;
//		try{
//			st=con.createStatement();
//			String task="select a_select from answer where a_yorn='��' and a_que_id='"+id+"';";
//			rs=st.executeQuery(task);
//			rs.next();
//			str=rs.getString(1);
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			try{rs.close();}catch(SQLException e){e.printStackTrace();}
//			try{st.close();}catch(SQLException e){e.printStackTrace();}
//			try{con.close();}catch(SQLException e){e.printStackTrace();}
//		}
//		return str;
//	}
	//��������id  �ҵ��𰸵�����
	public static String getAnswerneirongByid(String id){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select a_se_neirong from answer where a_yorn='��' and a_que_id='"+id+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�ҵ���������д�
	public static List<String[]> getAnswerAll()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from answer;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[Constant.answersxlen];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//��������id  �ҵ�Aѡ�߱��
	public static String getAnswerAByid(String id){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select a_id from answer where a_select='A' and a_que_id='"+id+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//==============================����𰸱�End===============================================
	//===============================��Ƶ��begin========================================

	public static List<String[]> getVideo()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select * from video;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[5];//===============
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}


	//���һ���µ���Ƶ
	public static String addVideo(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into video values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+ "','"+getmsg[3]+ "','"+getmsg[4]+ "','"
					+getmsg[5]+ "');";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//����������ɾ����Ƶ
	public static String delVideo(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from video where v_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ���Ƶ��ŵ����id
	public static String getVideoMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(v_id) from video;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}

	//���ݳ��ͱ��ɾ����Ƶ
	public static String delVideobycar(String id)
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from video where v_car_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//������Ƶ��Ϣ����id
	public static String updateVideo(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update video set v_name='" +getmsg[2]+"',";
			sql+="v_lujing='"+getmsg[3]+"',";
			sql+="v_image='"+getmsg[4]+"',";
			sql+="v_car_id='"+getmsg[5]+"'";
			sql+="where v_id='"+getmsg[1]+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//����id�õ���Ӧ��Ƶ������Ϣ

	public static List<String []> getVideoById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String []> list=new ArrayList<String []>();
		String[] str=new String[4];
		try{
			st=con.createStatement();
			String task="select v_name,v_lujing,v_image,v_car_id from video where v_id='"+string+"';";
			//System.out.println(task);
			rs=st.executeQuery(task);
			while(rs.next())
			{
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
				//str[4]=rs.getString(5);
			}
			list.add(str);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}

	//���ݳ���id  �ҵ����е�ѧԱ
	public static String[] getuseridBycar(String s)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		int count=getCount("usersb");
		String[] str=new String[count];
		try{
			st=con.createStatement();
			String task="select u_id from usersb where u_ca_id='"+s+"' ;";
			rs=st.executeQuery(task);
			int k=0;
			while(rs.next())
			{
				str[k]=rs.getString(1);
				k++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//����ѡid�û�  �ĳ��͸���Ϊ��  ��01����
	public static String updateusercar(String user)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_ca_id='01'";
			sql+="where u_id='"+user+"';";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}


	//���ݳ���id  �ҵ�����id
	public static String[] getstidBycar(String s)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		int count=getCount("qzhengli");
		String[] str=new String[count];
		try{
			st=con.createStatement();
			String task="select qz_que_id from qzhengli where qz_car_id='"+s+"' ;";
			rs=st.executeQuery(task);
			int k=0;
			while(rs.next())
			{
				str[k]=rs.getString(1);
				k++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//��������id  �ҵ�����id
	public static String[] getcaridByst(String s)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		int count=getCount("qzhengli");
		String[] str=new String[count];
		try{
			st=con.createStatement();
			String task="select qz_car_id from qzhengli where qz_que_id='"+s+"' ;";
			rs=st.executeQuery(task);
			int k=0;
			while(rs.next())
			{
				str[k]=rs.getString(1);
				k++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}

	//��������id�복��idɾ�����������
	public static String delqzbycarque(String getmsg[])
	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from qzhengli where qz_que_id='"+getmsg[0]+"' and qz_car_id='"+getmsg[1]+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}


	//==================================��Ƶ��END====================================
	//==================================
	//��ĳ���
	public static int getCount(String biaoname){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		int num=0;
		try{
			st=con.createStatement();
			String sql="select count(*) from "+biaoname;
			rs=st.executeQuery(sql);
			rs.next();
			num=rs.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return num;
	}

	//=================��У�õ�DBUTIL===================
	//���ݼ�Уid  �ҵ����е�ѧԱ
	public static String[] getuseridBydrive(String s)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		int count=getCount("usersb");
		String[] str=new String[count];
		try{
			st=con.createStatement();
			String task="select u_id from usersb where u_ds_id='"+s+"' ;";
			rs=st.executeQuery(task);
			int k=0;
			while(rs.next())
			{
				str[k]=rs.getString(1);
				k++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//����ѡid�û�  �ļ�У����Ϊ��  ��001��У
	public static String updateuserdrive(String user)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_ds_id='001'";
			sql+="where u_id='"+user+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//===============================��ű�start====================================
	public static List<String[]> getfenghao()//�õ�����б���������
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select * from fenghao;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[4];//===============
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}

	public static String addfenghao(String getmsg[])//���
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into fenghao values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+ "','"+getmsg[3]+ "','"+getmsg[4]+ "');";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�����û�ɾ���û�
	public static String delfenghao(String id)

	{


		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="delete from fenghao where f_id='"+id+"';";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�õ���Ƶ��ŵ����id
	public static String getfenghaoMaxID()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(f_id) from fenghao;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}
	//�����û�ID   �õ��û��Ƿ���Ե�¼
	public static String getdengluByUSER(String string){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_denglu from usersb where u_zhanghao='"+string+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�����û� �����û�����
	public static String pwdUSER(String str){
		Connection con=getConnection();
		Statement st = null;
		ResultSet rs = null;
		String info=null;
		try{
			st=con.createStatement();
			String sql="select u_password from usersb where u_zhanghao='"+str+"'";
			rs=st.executeQuery(sql);
			rs.next();
			info=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return info;
	}
	//===============================��ű�end====================================
	//�����û��˺� �õ��û���Ϣ
	public static List<String[]> getUserAllByZhangHao(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select u_ca_id,u_ds_id,u_nicheng,u_touxiang,u_age,u_sex,u_phone,u_address from usersb where u_zhanghao='"+string+"';";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[8];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//�����û�ID �õ��û���ͷ��
	public static String getUsertouxiangByid(String string){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_touxiang from usersb where u_id='"+string+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//����������Ŀ��ѯID
	public static String getTiemainIDByName(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select tm_id from tiemain where tm_title='"+string+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	//�����Ա� ��
	public static String updatePerSon_SexToMen(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_sex='" +"��"+"'";
			sql+="where u_zhanghao='"+getmst+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}
	//�����Ա�  Ů
	public static String updatePerSon_SexToWoMen(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_sex='" +"Ů"+"'";
			sql+="where u_zhanghao='"+getmst+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//�����ǳ�
	public static String updateNiCheng(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			//ע�� ��һ�����һ���� �ֺ� �����߻��ж����Ļ��Ƕ���
			String sql="update usersb set u_nicheng='" +getmsg[2]+"'";
//							sql+="ds_jianjie='"+getmsg[3]+"',";
//							sql+="ds_phone='"+getmsg[4]+"',";
//							sql+="ds_address='"+getmsg[5]+"'";
			sql+="where u_zhanghao='"+getmsg[1]+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//�����ǳ�
	public static String updatePhone(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			//ע�� ��һ�����һ���� �ֺ� �����߻��ж����Ļ��Ƕ���
			String sql="update usersb set u_phone='" +getmsg[2]+"'";
//							sql+="ds_jianjie='"+getmsg[3]+"',";
//							sql+="ds_phone='"+getmsg[4]+"',";
//							sql+="ds_address='"+getmsg[5]+"'";
			sql+="where u_zhanghao='"+getmsg[1]+"';";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//�õ��û�ID���ֵ
	public static String getUserMaxIdForPhone(){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String string=new String();
		try{
			st=con.createStatement();
			String task="select max(u_id) from usersb;";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}

	//�ֻ��û�ע����Ϣ ����ֻ��û�
	public static String addUserForPhone(String getmsg[])//���
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into usersb values" +"('"
					+getmsg[1]+ "','"+getmsg[2]+
					"','"+getmsg[3]+"','"+getmsg[4]+"','"+getmsg[5]+"','"+getmsg[6]+
					"','"+getmsg[7]+"','"+getmsg[8]+"','"+getmsg[9]+"','"+getmsg[10]+"','"+getmsg[11]+
					"','"+getmsg[12]+ "','"+getmsg[13]+ "');";
			System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//ͨ����Ƶ���ƺ���Ƶ��Ӧ��С����ŵõ� ��Ƶ�ĵ�ַ
	public static String getAddressByNameAndId(String[] stbb){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select v_lujing from video where v_name='"+stbb[1]+"' and v_car_id='"+stbb[2]+"';";
			rs=st.executeQuery(task);
			rs.next();
			str=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}

	//��ѯ�û����������˺�
	public static List<String[]> getUserSbZhangHao()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select u_zhanghao from usersb;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[1];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}

	//�һ�������

	public static String getPassWordByZhanghaoAndAnquanma(String stringzhanghao,String stringzhaohuima)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select u_password from usersb where u_zhanghao='"+stringzhanghao+"' and u_anquanma='"+stringzhaohuima+"';";

			rs=st.executeQuery(task);
			while(rs.next())
			{
				str=rs.getString(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}

	//ѡ��������Ŀ��ȡ
	//======================�õ���Ŀһ ����Ŀ
	public static String getTiMu_SumOne(String one){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String subjectone="��Ŀһ";
		String string=new String();
		try{
			st=con.createStatement();
			//======================
			String task="select count(q_id) from question where q_chexing='"+one+"' and q_subject='"+subjectone+"';";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}


	//======================�õ���Ŀ�� ����Ŀ
	public static String getTiMu_SumFour(String four){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String subjectfour="��Ŀ��";
		String string=new String();
		try{
			st=con.createStatement();
			//======================
			String task="select count(q_id) from question where q_chexing='"+four+"' and q_subject='"+subjectfour+"';";
			rs=st.executeQuery(task);
			rs.next();
			if(rs.getString(1)==null){
				string=String.valueOf(0);
			}
			else{
				string=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		return string;
	}

	//===============ͼƬ
	//����ʮһ�� ���dbutil
	public static String updateUserTouxiang(String getmst[])
	{//������  1  ��ID  2��ͼƬ�ַ���
		deleteFiles("YHTP/"+getmst[1]+".png");//��֮ǰ��ͼƬɾ��
		Pcode.GenerateImageToUser(getmst[2], getmst[1]);
		getmst[2]= "YHTP/"+getmst[1]+".png";// �����ɵ�ͼƬ
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_touxiang='"+getmst[2]+"'";
			sql+="where u_id='"+getmst[1]+"';";
			//System.out.println(sql);
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{st.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return "ok";
	}

	//================��������25����ӵ�DBUtil==============================
	//�õ���������
	public static List<String[]> getQzhengli()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select *from qzhengli;";
			rs=st.executeQuery(task);
			while(rs.next()){
				String[] str=new String[2];
				for(int i=0;i<str.length;i++){
					str[i]=rs.getString(i+1);
				}
				list.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return list;
	}
	//=====================================


	//==================================================
	//ɾ��ָ���ļ�   ������  ��ɾ������ʱ   ��  �����ͼƬ��֮ɾ��
	public static void deleteFiles(String path)
	{
		File file = new File(path);
		//1���ļ��h��
		if(!file.isDirectory())
		{
			file.delete();
		}
	}
	//=============================================





	public static void main(String args[])
	{ }

}
