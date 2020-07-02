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
	{		  //编写与数据库建立连接的方法
		Connection con = null;                         //声明连接
		try{
			Class.forName(Constant.MySQL_Driver_Version_8);	//声明驱动
			con=DriverManager.getConnection(Constant.MySQL_Connection_URL,
					Constant.MySQL_Root_User,Constant.MySQL_Root_Password);
		}catch(Exception e){
			e.printStackTrace();             //捕获异常
		}
		return con;                           //返回连接
	}
	//===================车型表begin======================

	//通过id找到对应的车型的其他信息

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

	//添加所报车型 大车小车
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

	//删除所报车型
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
	//更新所选车型
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
	//得到车型信息 B本 ID号什么的
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
	//得到车型名称
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
	//得到车型ID最大值
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
	//得到车型ID
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
	//通过车类型名称得到车类型id
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
	//能过车型ID得到车名
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

	//==================车型表END=====================
	//====================管理员begin==========================

	//添加管理员
	public static String addAdminer(String getmsg[])
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="insert into adminer values('" +getmsg[1]+ "','"+getmsg[2]+"','"+getmsg[3]+"','" +getmsg[4]+ "','" +getmsg[5]+ "','" +"是"+"');";
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

	//更改管理员信息
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
	//删除管理员
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

	//得到现有管理员最大id
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

	//通过id找到对应的管理员的其他信息
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
	//查询所有管理员
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
	//根据管理员id  查找管理员密码
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
	//根据管理员id  找到管理员是否在职  的结果
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
	//更改管理员是否在职信息
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
	//======================管理员END=================================
	//=====================驾校begin====================================
	//增加驾校
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

	//更改驾校信息
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
	//根据id删除驾校
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
	//查找最大驾校id
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
	//根据id得到相应驾校所有信息
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
	//查询现有所有驾校
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
	//能过驾校ID得到驾校名称
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
	//查询驾校中所含城市
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
	//查询城市驾校的各个名称
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
	//=======================驾校END=================================
	//===========================主题类别begin================================
	//添加主贴类别
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
	//更改帖子类别
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
	//删除帖子类别
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
	//得到最大类别id
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
	//根据id查类别名
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
	//根据类别名查询类别id
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
	//查询现有所有主贴类别
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
	//==============================主贴类别END==============================
	//================================用户表begin================================
	//增加用户
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
	//查询被禁止发言的用户
	public static List<String[]> getuserfb()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select u_id,u_zhanghao,u_ca_id,u_ds_id,u_nicheng,u_age,u_sex,u_phone,u_address from usersb where u_denglu='否'";
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
	//更改用户信息
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
	//根据用户id  得到用户的车型编号
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
	//根据用户id得到用户所选的驾校编号
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
	//根据用户账号名  得到用户Id
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
	//根据用户ID 得到用户账号
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
	//得到所有用户
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
	//禁止登陆
	public static String updateUserdlToObsect(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_denglu='" +"否"+"'";
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
	//可以登录
	public static String updateUserdlToObsectTow(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_denglu='" +"是"+"'";
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
			String sql="update adminer set ad_zaizhi='" +"否"+"'";
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
	//可以登录
	public static String updateAdmlToObsectTow(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update adminer set ad_zaizhi='" +"是"+"'";
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
	//更改用户是否允许登录
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
	//根据id  查询晕乎是否可以登录信息
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
	//根据id查用户昵称
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
	//根据昵称查用户id
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
	//=================================用户表END=================================
	//===============================主贴表begin==========================================
	//添加一个新的帖子
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

	//删除主贴表信息 根据帖子id
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
	//得到多有发过的朱帖子
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
	//根据用户id   得到该用户的帖子
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
	//根据 所选类别  得到所有的帖子
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
	//根据所选类别id  得到帖子的id
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
	//得到主贴表最大id
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
	//根据所选的主贴的id  得到是哪个用户的发帖
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
	//根据id查询帖子题目
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

	//=================================主贴表END=======================================

	//================================回复表begin===============================================
	//添加回复
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

	//删除一个回复
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
	//得到所有的回复
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
	//根据帖子id 得到回复
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
	//根据所属帖子id  删除所有的回复
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
	//得到最大回复id
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

	//=====================================回复表END==========================================
	//===============================试题表begin===============================================
	//添加试题
	public static String addQuestion(String getmsg[])
	{
		//getmsg[4]="C:/Users/Administrator/Desktop/Pcode.java";
		if(!getmsg[4].equals("空"))
		{
			Pcode.GenerateImage(getmsg[4], getmsg[1]);
			getmsg[4]= "STTP/"+getmsg[1]+".png";// 新生成的图片
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
	//删除试题
	public static String delQuestion(String id)
	{
		deleteFiles("STTP/"+id+".png");//将试题的图片删除
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
	//更改试题信息
	public static String updateQuestion(String[] getmsg)
	{
		deleteFiles("STTP/"+getmsg[1]+".png");//将试题的图片删除
		if(!getmsg[4].equals("空"))
		{
			Pcode.GenerateImage(getmsg[4], getmsg[1]);
			getmsg[4]= "STTP/"+getmsg[1]+".png";// 新生成的图片
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
	//得到所有试题
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
	//得到试题id最大值
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
	//根据所属科目得到所有试题
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
	//根据id得到题目信息
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
	//==================================试题表END============================================

	//==================================错题表begin============================================
	//添加错题
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
	//删除错题   根据错题id
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
	//根据  试题id   删除该试题错题
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
	//删除用户所有错题
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
	//得到错题的最大id
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
	//根据用户id  得到用户的错题编号
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
	//根据用户id  得到用户的错误的试题的编号
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
	//得到错题的总数 根据用户
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
	//=======================================错题表END=========================

	//==========================考题整理表begin===========================================
	//添加类型整理
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

	//根据试题id删除整理表内容
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
	//根据车型编号找到所有试题编号
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
	//根据试题编号找到对应的车型编号
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
	//============================考题整理表END==========================================
	//=============================试题答案表begin================================================
	//添加试题答案
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
	//得到试题答案中的最大id
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
	//删除某个试题的所有答案
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
	//找到某个试题的答案
	public static List<String[]> getAnswerById(String string)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			String task="select a_select from answer where a_yorn='是' and a_que_id='"+string+"';";
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
	//根据试题编号找到所属于的全部答案
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
	//更改试题答案（找出原来试题答案  将之“否”  ，更改新的答案）
//	public static String updateAnswer(String[] getmsg)
//	{
//
//			Connection con=getConnection();
//			Statement st=null;
//			try{
//				st=con.createStatement();
//				String qid=getmsg[0];
//				String aselect=getAnswerById(qid);
//				String sql="update answer set a_yorn='否' where a_select='"+aselect+"' and a_que_id='"+qid+"';";
//
//				st.executeUpdate(sql);
//				sql="update answer set a_yorn='是' where a_select='"+getmsg[1]+"' and a_que_id='"+qid+"';";
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
	//更改试题选项内容
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
	//根据传进来的答案id 级试题编号 更改答案
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
	//	//根据试题id 得到正确答案选项
//	public static String getAnswerzhengqueeByid(String id){
//		Connection con=getConnection();
//		Statement st=null;
//		ResultSet rs=null;
//		String str=null;
//		try{
//			st=con.createStatement();
//			String task="select a_select from answer where a_yorn='是' and a_que_id='"+id+"';";
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
	//根据试题id  找到答案的内容
	public static String getAnswerneirongByid(String id){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		try{
			st=con.createStatement();
			String task="select a_se_neirong from answer where a_yorn='是' and a_que_id='"+id+"';";
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
	//找到试题的所有答案
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
	//根据试题id  找到A选线编号
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
	//==============================试题答案表End===============================================
	//===============================视频表begin========================================

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


	//添加一个新的视频
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
	//根据试题编号删除视频
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
	//得到视频编号的最大id
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

	//根据车型编号删除视频
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
	//更改视频信息根据id
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

	//根据id得到相应视频所有信息

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

	//根据车型id  找到所有的学员
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
	//将所选id用户  的车型更改为空  即01车型
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


	//根据车型id  找到试题id
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
	//根据试题id  找到车型id
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

	//根据试题id与车型id删除整理表内容
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


	//==================================视频表END====================================
	//==================================
	//表的长度
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

	//=================驾校用的DBUTIL===================
	//根据驾校id  找到所有的学员
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
	//将所选id用户  的驾校更改为空  即001驾校
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

	//===============================封号表start====================================
	public static List<String[]> getfenghao()//得到封号列表所有属性
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

	public static String addfenghao(String getmsg[])//添加
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
	//根据用户删除用户
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
	//得到视频编号的最大id
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
	//根据用户ID   得到用户是否可以登录
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
	//根据用户 查找用户密码
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
	//===============================封号表end====================================
	//根据用户账号 得到用户信息
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
	//根据用户ID 得到用户的头像
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
	//根据帖子题目查询ID
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
	//更新性别 男
	public static String updatePerSon_SexToMen(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_sex='" +"男"+"'";
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
	//更新性别  女
	public static String updatePerSon_SexToWoMen(String getmst)
	{
		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			String sql="update usersb set u_sex='" +"女"+"'";
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

	//更新昵称
	public static String updateNiCheng(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			//注意 第一行最后一个是 分号 如果后边还有东西的话是逗号
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

	//更新昵称
	public static String updatePhone(String[] getmsg)
	{

		Connection con=getConnection();
		Statement st=null;
		try{
			st=con.createStatement();
			//注意 第一行最后一个是 分号 如果后边还有东西的话是逗号
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

	//得到用户ID最大值
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

	//手机用户注册信息 添加手机用户
	public static String addUserForPhone(String getmsg[])//添加
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

	//通过视频名称和视频对应的小车编号得到 视频的地址
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

	//查询用户表中所有账号
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

	//找回密码用

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

	//选车试题数目获取
	//======================得到科目一 题数目
	public static String getTiMu_SumOne(String one){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String subjectone="科目一";
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


	//======================得到科目四 题数目
	public static String getTiMu_SumFour(String four){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String subjectfour="科目四";
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

	//===============图片
	//四月十一号 添加dbutil
	public static String updateUserTouxiang(String getmst[])
	{//传进来  1  是ID  2是图片字符串
		deleteFiles("YHTP/"+getmst[1]+".png");//将之前的图片删除
		Pcode.GenerateImageToUser(getmst[2], getmst[1]);
		getmst[2]= "YHTP/"+getmst[1]+".png";// 新生成的图片
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

	//================这是四月25号添加的DBUtil==============================
	//得到所有试题
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
	//删除指定文件   这里是  在删除试题时   将  试题的图片随之删除
	public static void deleteFiles(String path)
	{
		File file = new File(path);
		//1級文件刪除
		if(!file.isDirectory())
		{
			file.delete();
		}
	}
	//=============================================





	public static void main(String args[])
	{ }

}
