package com.bn.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.bn.Database.DBUtil;
import com.bn.util.IOUtil;
import com.bn.util.MyConverter;
import com.bn.util.Pcode;
import com.bn.util.TypeExchangeUtil;
import static com.bn.util.Constant.*;
import static com.bn.util.TypeExchangeUtil.*;

@SuppressWarnings("unused")
public class ServerAgentThread extends Thread {

	private Socket sc; // 声明Socket
	private DataInputStream in;// 声明数据输入流
	private DataOutputStream out;// 声明数据输出流
	private static final String ok = "ok";// 定义字符串ok
	private static final String fail = "fail";// 定义字符串fail

	public ServerAgentThread(Socket sc) {// 定义构造器
		try {
			this.sc = sc;// 接收Socket
			in = new DataInputStream(sc.getInputStream());// 创建新数据输入流
			out = new DataOutputStream(sc.getOutputStream()); // 创建新数据输出流
		} catch (Exception e) {
			e.printStackTrace();// 捕获异常
		}
	}

	public void run() 
	{
		try
		{
			String readinfo = IOUtil.readStr(in);
			System.out.println("Feedback information:->" + readinfo);
			if (readinfo.startsWith(SUPER_LOGIN)) //判断数据库中该账号的密码是否正确    然后判断是否可以登录
				{
				String[] getmsg = readinfo.split(SUPER_LOGIN);
				String zaizhi=DBUtil.getZaizhiByAdminer(getmsg[1]);
				if(!zaizhi.equals("否"))
				{
					String wmm = DBUtil.pwdAdminer(getmsg[1]);
			if (wmm!=null && wmm.equals(getmsg[2])) {// 若信息不空，且管理员密码正确
					IOUtil.writeStr(out, MyConverter.escape(ok));
					} 
				else
					{
						IOUtil.writeStr(out, MyConverter.escape(fail));
					}		
				}
				else
				{
					String jinzhi="jinzhi";
					IOUtil.writeStr(out, MyConverter.escape(jinzhi));
				}
					
				}
			else if(readinfo.startsWith(ADD_USER))
			{
				//加用户
				String[] getmsg=readinfo.split(ADD_USER);
				String isok=DBUtil.addUsersb(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			else if(readinfo.equals(GET_USER))
			{
				//得到所有用户
				//==========================================
				String liststr=TypeExchangeUtil.listToString(DBUtil.getUsersb());
				
				System.out.print(liststr);
				IOUtil.writeStr(out, MyConverter.escape(liststr));
			}
			
			
			else if(readinfo.startsWith(USER_NOT_TALK))
			{
				//禁言
				String[] getmsg=readinfo.split(USER_NOT_TALK);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.updateUserdlToObsect(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(USER_CAN_TALK))
			{
				//不禁言
				String[] getmsg=readinfo.split(USER_CAN_TALK);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.updateUserdlToObsectTow(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			
			else if(readinfo.startsWith(ADM_NOT_TALK))
			{
				//禁言
				String[] getmsg=readinfo.split(ADM_NOT_TALK);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.updateAdmlToObsect(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(ADM_CAN_TALK))
			{
				//不禁言
				String[] getmsg=readinfo.split(ADM_CAN_TALK);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.updateAdmlToObsectTow(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			else if(readinfo.startsWith(ADD_ADMIN))
			{
				//加管理
				String[] getmsg=readinfo.split(ADD_ADMIN);
				String isok=DBUtil.addAdminer(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(DEL_ADMIN))
			{
				//删管理
				String[] getmsg=readinfo.split(DEL_ADMIN);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.delAdminer(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.equals(GET_ADMID))
			{
				//得到所有管理员
				//==========================================
				String liststr=TypeExchangeUtil.listToString(DBUtil.getAdminer());
				
				System.out.print(liststr);
				IOUtil.writeStr(out, MyConverter.escape(liststr));
				//IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(GET_REPLYbyid))
			{
				String[] getmsg=readinfo.split(GET_REPLYbyid);
				String sres=TypeExchangeUtil.listToString(DBUtil.getReplybyid(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(DEL_REPLY))
			{
				String[] getmsg=readinfo.split(DEL_REPLY);
				String isok=DBUtil.delReply(getmsg[1]);
				System.out.println(isok);
					IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(delTiemain))
			{
				String[] getmsg=readinfo.split(delTiemain);
				DBUtil.delReplybyid(getmsg[1]);//先删除  该帖子的所有回复   然后在删除该帖子
				String isok=DBUtil.delTiemain(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.equals(getTiemain))
			{
				
				String sres=TypeExchangeUtil.listToString(DBUtil.getTiemain());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getTiemainbyuser))
			{
				String[] getmsg=readinfo.split(getTiemainbyuser);
				String sres=TypeExchangeUtil.listToString(DBUtil.getTiemainbyuser(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getTiemainbyclass))
			{
				String[] getmsg=readinfo.split(getTiemainbyclass);
				String sres=TypeExchangeUtil.listToString(DBUtil.getTiemainbyclass(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			else if(readinfo.equals(getTieclass))
			{
				
				String sres=TypeExchangeUtil.listToString(DBUtil.getTieclass());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.equals(getTieclassMaxID))
			{
				
				String sres=DBUtil.getTieclassMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(addTieclass))
			{
				String[] getmsg=readinfo.split(addTieclass);//得到要删除的类别
				String isok=DBUtil.addTieclass(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(delTieclass))
			{
				String[] getmsg=readinfo.split(delTieclass);
				String[] tiemid=DBUtil.gettmidByclass(getmsg[1]);//根据类别id   找到主贴表的提帖子id
				for(int i=0;i<tiemid.length;i++)
				{
					DBUtil.delReplybyid(tiemid[i]);//删除每个帖子  的所有回复
				}
				for(int i=0;i<tiemid.length;i++)
				{
					DBUtil.delTiemain(tiemid[i]);//根据id  删除每个类别下的帖子
				}
				
				String isok=DBUtil.delTieclass(getmsg[1]);//不能先删除  应该先删除起类别下的帖子   +回复
				
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(addQuestion))
			{
				String[] getmsg=readinfo.split(addQuestion);
				String isok=DBUtil.addQuestion(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.equals(getQuestionMaxID))
			{
				
				String sres=DBUtil.getQuestionMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.equals(getAnswerMaxID))
			{
				
				String sres=DBUtil.getAnswerMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(addAnswer))
			{
				String[] getmsg=readinfo.split(addAnswer);
				String isok=DBUtil.addAnswer(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.equals(getVideoMaxID))
			{
				
				String sres=DBUtil.getVideoMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(addVideo))
			{
				String[] getmsg=readinfo.split(addVideo);
				String isok=DBUtil.addVideo(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.equals(getCarType))
			{
				
				String sres=TypeExchangeUtil.listToString(DBUtil.getCarType());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(addQzhengli))
			{
				String[] getmsg=readinfo.split(addQzhengli);
				String isok=DBUtil.addQzhengli(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(delQuestion))
			{
				String[] getmsg=readinfo.split(delQuestion);//当前  getmsg[1]为要删除的  试题编号
					DBUtil.delErrorByque(getmsg[1]);//删除  错题表中的   这道题
					DBUtil.delQzhengli(getmsg[1]);
					DBUtil.delAnswer(getmsg[1]);
					String isok=DBUtil.delQuestion(getmsg[1]);
				//System.out.println(isok);
					IOUtil.writeStr(out, MyConverter.escape(isok));
			}
		
			//试题
			else if(readinfo.startsWith(ADD_QUESTION))
			{
				//不禁言
				String[] getmsg=readinfo.split(ADD_QUESTION);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String sres=TypeExchangeUtil.listToString(DBUtil.getQuestion());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			//==================驾校
			else if(readinfo.startsWith(JiaXiao_XinXi))
			{
				//加用户
				String[] getmsg=readinfo.split(JiaXiao_XinXi);
				String sres=TypeExchangeUtil.listToString(DBUtil.getDrivesc());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			else if(readinfo.startsWith(JiaXiao_Add))
			{
				//加用户
				String[] getmsg=readinfo.split(JiaXiao_Add);
				String sres=(DBUtil.addDrivesc(getmsg));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getQuestionByid))
			{
				String[] getmsg=readinfo.split(getQuestionByid);
				String sres=TypeExchangeUtil.listToString(DBUtil.getQuestionByid(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getQzhenglicarByque))
			{
				String[] getmsg=readinfo.split(getQzhenglicarByque);
				String sres=TypeExchangeUtil.listToString(DBUtil.getQzhenglicarByque(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getAnswerqByid))
			{
				String[] getmsg=readinfo.split(getAnswerqByid);
				String sres=TypeExchangeUtil.listToString(DBUtil.getAnswerqByid(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getAnswerById))
			{
				String[] getmsg=readinfo.split(getAnswerById);
				String sres=TypeExchangeUtil.listToString(DBUtil.getAnswerById(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(updateQuestion))
			{
				String[] getmsg=readinfo.split(updateQuestion);
				String isok=DBUtil.updateQuestion(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(delQzhengli))
			{
				String[] getmsg=readinfo.split(delQzhengli);
				String isok=DBUtil.delQzhengli(getmsg[1]);
				//System.out.println(isok);
					IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(delAnswer))
			{
				String[] getmsg=readinfo.split(delAnswer);
				String isok=DBUtil.delAnswer(getmsg[1]);
				//System.out.println(isok);
					IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(updateAnswerall))
			{
				String[] getmsg=readinfo.split(updateAnswerall);
				String isok=DBUtil.updateAnswerall(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(getAnswerAByid))
			{
				String[] getmsg=readinfo.split(getAnswerAByid);
				String sres=DBUtil.getAnswerAByid(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			else if(readinfo.startsWith(addVideo))
			{
				String[] getmsg=readinfo.split(addVideo);
				String isok=DBUtil.addVideo(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(updateTieclass))
			{
				String[] getmsg=readinfo.split(updateTieclass);//得到要修改的类别
				String isok=DBUtil.updateTieclass(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
//的视频
			else if(readinfo.startsWith(GET_VIEDIO))
			{
				String[] getmsg=readinfo.split(GET_VIEDIO);
				String sres=TypeExchangeUtil.listToString(DBUtil.getVideo());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//public static final String JiaXiao_Add="JiaXiao_Add";
			//===================================
			else if(readinfo.startsWith(DEL_VIEDIO))
			{
				String[] getmsg=readinfo.split(DEL_VIEDIO);
				String sres=DBUtil.delVideo(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//车型
			else if(readinfo.equals(CheXingXinXi))
			{
				String[] getmsg=readinfo.split(CheXingXinXi);
				String sres=TypeExchangeUtil.listToString(DBUtil.getCarType());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(CheXingAdd))
			{
				String[] getmsg=readinfo.split(CheXingAdd);
				String isok=DBUtil.addCarType(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.equals(getTiemainMaxID))
			{
				
				String sres=DBUtil.getTiemainMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(dandftiemain))//删除该贴及回复  并将用户封掉
			{
				String[] getmsg=readinfo.split(dandftiemain);
				//先拿出该帖子的用户
				String user=DBUtil.getUserBytiemainid(getmsg[1]);
				//将用户设置不可登录
				DBUtil.updateUserdlToObsect(user);
				DBUtil.delReplybyid(getmsg[1]);//先删除  该帖子的所有回复   然后在删除该帖子
				String isok=DBUtil.delTiemain(getmsg[1]);
				
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//驾校最大ID
			else if(readinfo.equals(JiaXiao_GetMaxId))
			{
				
				String sres=DBUtil.getDrivescMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//管理员最大ID
			else if(readinfo.equals(ADM_GETMAXID))
			{
				
				String sres=DBUtil.getAdminerMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//车型最大编号CHEXING_MAXID
			else if(readinfo.equals(CHEXING_MAXID))
			{
				
				String sres=DBUtil.getCarTypeMaxId();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//视频最大ID  getVideoMaxID
			else if(readinfo.equals(getVideoMaxID))
			{
				
				String sres=DBUtil.getVideoMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//==========驾校用
			else if(readinfo.startsWith(delDrivesc))
			{
			String[] getmsg=readinfo.split(delDrivesc);//得到了要删除的驾校id
			String[] user=DBUtil.getuseridBydrive(getmsg[1]);
			for(int i=0;i<user.length;i++)
			{
			DBUtil.updateuserdrive(user[i]);
			}
			String isok=DBUtil.delDrivesc(getmsg[1]);

			IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//封号用
			else if(readinfo.equals(GET_USERFB))
			{
				String liststr=TypeExchangeUtil.listToString(DBUtil.getuserfb());
				System.out.print(liststr);
				IOUtil.writeStr(out, MyConverter.escape(liststr));
			}
			//由ID得到驾校信息 n 
			else if(readinfo.startsWith(GET_JIAXIAO_MESSAGE))
			{
				String[] getmsg=readinfo.split(GET_JIAXIAO_MESSAGE);
				String sres=TypeExchangeUtil.listToString(DBUtil.getDrivescdById(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//更改驾校信息UPDATE_JIAXIAO 
			else if(readinfo.startsWith(UPDATE_JIAXIAO))
			{
				String[] getmsg=readinfo.split(UPDATE_JIAXIAO);
				String sres=(DBUtil.updateDrivesc(getmsg));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.equals(GET_FENGHAO))
			{
				String[] getmsg=readinfo.split(GET_FENGHAO);
				String sres=TypeExchangeUtil.listToString(DBUtil.getfenghao());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(ADD_FENGHAO))
			{
				String[] getmsg=readinfo.split(ADD_FENGHAO);
				String isok=DBUtil.addfenghao(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(DEL_FENGHAO))
			{
				String[] getmsg=readinfo.split(DEL_FENGHAO);
				String sres=DBUtil.delfenghao(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.equals(GET_FengHaoMaxID))
			{
				String sres=DBUtil.getfenghaoMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			else if(readinfo.startsWith(updateCarType))
			{
				String[] getmsg=readinfo.split(updateCarType);
				String isok=DBUtil.updateCarType(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}

			else if(readinfo.startsWith(delCarType))
			{
				String[] getmsg=readinfo.split(delCarType);//得到了要删除的车型id
				//先删除视频
				
				DBUtil.delVideobycar(getmsg[1]);
				//将用户表所有选择该成型的 拿出来
				
				String[] user=DBUtil.getuseridBycar(getmsg[1]);
				//将所有用户的 车型第  改为空  级01
				for(int i=0;i<user.length;i++)
				{
					
					DBUtil.updateusercar(user[i]);
				}
				//考题整理表
				//通过考题整理表找到所有属于该车型的试题id
				String stall[]=DBUtil.getstidBycar(getmsg[1]);
				for(int j=0;j<stall.length;j++)
				{
					//删除这个车型的考题整理表
					String []standcar=new String[2];
					standcar[0]=stall[j];
					standcar[1]=getmsg[1];
					DBUtil.delqzbycarque(standcar);
				}
				//考题整理表该车型删除    判断其他试题
				for(int j=0;j<stall.length;j++)
				{
					String carall[]=DBUtil.getcaridByst(stall[j]);//得到该试题的  车型id
					if(carall[0]==null)
					{
						//这个试题只有这一个车型
						//删除这道题
						
						DBUtil.delErrorByque(stall[j]);//删除  错题表中的   这道题
						DBUtil.delQzhengli(stall[j]);
						DBUtil.delAnswer(stall[j]);
						DBUtil.delQuestion(stall[j]);
					}
				}
				String isok=DBUtil.delCarType(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}

			else if(readinfo.startsWith(getcarById))
			{
				String[] getmsg=readinfo.split(getcarById);
				String sres=TypeExchangeUtil.listToString(DBUtil.getcarById(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getCarTypeNameById))
			{
				String[] getmsg=readinfo.split(getCarTypeNameById);
				String sres=DBUtil.getCarTypeNameById(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getDriverNameById))
			{
				String[] getmsg=readinfo.split(getDriverNameById);
				String sres=DBUtil.getDriverNameById(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getTieclassNameById))
			{
				String[] getmsg=readinfo.split(getTieclassNameById);
				String sres=DBUtil.getTieclassNameById(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getUserNameById))
			{
				String[] getmsg=readinfo.split(getUserNameById);
				String sres=DBUtil.getUserNameById(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getUseridByName))
			{
				String[] getmsg=readinfo.split(getUseridByName);
				String sres=DBUtil.getUseridByName(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getTieclassidByName))
			{
				String[] getmsg=readinfo.split(getTieclassidByName);
				String sres=DBUtil.getTieclassidByName(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getVideoById))
			{
				String[] getmsg=readinfo.split(getVideoById);
				String sres=TypeExchangeUtil.listToString(DBUtil.getVideoById(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(updateVideo))
			{
				String[] getmsg=readinfo.split(updateVideo);
				String isok=DBUtil.updateVideo(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(getAnswerneirongByid))
			{
				String[] getmsg=readinfo.split(getAnswerneirongByid);
				String sres=DBUtil.getAnswerneirongByid(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.equals(getQuestion))
			{
				
				String sres=TypeExchangeUtil.listToString(DBUtil.getQuestion());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.equals(getAnswerAll))
			{
				
				String sres=TypeExchangeUtil.listToString(DBUtil.getAnswerAll());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			// 测试连接
			else if (readinfo.startsWith(TESTCONNECT)) 
			{
				IOUtil.writeStr(out, MyConverter.escape("success"));// 将提示成功信息写入流
			}
			//根据传进来的图片位置  得到图片并传送回去
			else if(readinfo.startsWith(getimage))
			{
				String[] getmsg=readinfo.split(getimage);//getmsg[1]为图片位置
				String tupianxinxi=Pcode.GetImageStr(getmsg[1]);//将图片信息装化为字符串
				IOUtil.writeStr(out, MyConverter.escape(tupianxinxi));
			}
			//手机用户登录
			else if (readinfo.startsWith(PHONE_LOGIN)) //判断数据库中该账号的密码是否正确    然后判断是否可以登录
			{
			String[] getmsg = readinfo.split(PHONE_LOGIN);
			String zaizhi=DBUtil.getdengluByUSER(getmsg[1]);
			if(!zaizhi.equals("否"))
			{
				String wmm = DBUtil.pwdUSER(getmsg[1]);
				if (wmm!=null&&wmm.equals(getmsg[2])) {// 若信息不空，且管理员密码正确
				IOUtil.writeStr(out, MyConverter.escape(ok));
				} 
			else
				{
					IOUtil.writeStr(out, MyConverter.escape(fail));
				}		
			}
			else
			{
				String jinzhi="jinzhi";
				IOUtil.writeStr(out, MyConverter.escape(jinzhi));
			}
				
			}
			//得到个人所有信息
			else if(readinfo.startsWith(PERSON_ALL))
			{
				//这三句是主要的
				String[] getmsg=readinfo.split(PERSON_ALL);
				String sres=TypeExchangeUtil.listToString(DBUtil.getUserAllByZhangHao(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
				
			}
			//得到头像
			else if(readinfo.startsWith(getUsertouxiangByid))
			{
				String[] getmsg=readinfo.split(getUsertouxiangByid);//拿到了要取头像的账号
				//下面对头像进行判断
				String touxiang=DBUtil.getUsertouxiangByid(getmsg[1]);
				if(!touxiang.equals("空"))
				{
					String tupianxinxi=Pcode.GetImageStr(touxiang);//将图片信息装化为字符串
					IOUtil.writeStr(out, MyConverter.escape(tupianxinxi));
				}
				else 
				{
					IOUtil.writeStr(out, MyConverter.escape("空"));
				}
				
			}
			//根据题目得到主贴表的题目ID
			else if(readinfo.startsWith(getTiemainIDByName))
			{
				String[] getmsg=readinfo.split(getTiemainIDByName);
				String sres=DBUtil.getTiemainIDByName(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(UPDATE_PERSON_SEX_MAN))
			{
				//变性别为 男
				String[] getmsg=readinfo.split(UPDATE_PERSON_SEX_MAN);
				String isok=DBUtil.updatePerSon_SexToMen(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(UPDATE_PERSON_SEX_WOMAN))
			{
				//变性别为 女
				String[] getmsg=readinfo.split(UPDATE_PERSON_SEX_WOMAN);
				String isok=DBUtil.updatePerSon_SexToWoMen(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			//更改昵称 手机端
			else if(readinfo.startsWith(UPDATE_PERSON_NICHENG))
			{
				String[] getmsg=readinfo.split(UPDATE_PERSON_NICHENG);
				String sres=(DBUtil.updateNiCheng(getmsg));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			//更改昵称 手机端
			else if(readinfo.startsWith(UPDATE_PERSON_PHONE))
			{
				String[] getmsg=readinfo.split(UPDATE_PERSON_PHONE);
				String sres=(DBUtil.updatePhone(getmsg));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			//手机用户最大ID
			else if(readinfo.equals(GET_MAXUSER_ID_FORPHOEN))
			{
				
				String sres=DBUtil.getUserMaxIdForPhone();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//添加手机用户
			else if(readinfo.startsWith(FORPHONE))
			{
				String[] getmsg=readinfo.split(FORPHONE);
				String isok=DBUtil.addUserForPhone(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//得到所有用户的账号
			else if(readinfo.equals(GET_USER_ZHANGHAO_FORPHONE))
			{
				//==========================================
				String liststr=TypeExchangeUtil.listToString(DBUtil.getUserSbZhangHao());
				System.out.print(liststr);
				IOUtil.writeStr(out, MyConverter.escape(liststr));
				//IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//找回用户密码
			else if(readinfo.startsWith(GET_USER_PASSWORD_FORPHONE))
			{
				String[] getmsg=readinfo.split(GET_USER_PASSWORD_FORPHONE);
				String isok=DBUtil.getPassWordByZhanghaoAndAnquanma(getmsg[1],getmsg[2]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			// 科目一 试题   通过传参得到是 小车、货车等 总数
			else if(readinfo.startsWith(GET_SUBJECT_ONE_FOR_XIAOCHE))
			{
				String[] getmsg=readinfo.split(GET_SUBJECT_ONE_FOR_XIAOCHE);
				String sres=DBUtil.getTiMu_SumOne(getmsg[1]);
				System.out.println("sres=="+sres);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//小车 科目四 试题总数
			else if(readinfo.startsWith(GET_SUBJECT_FOUR_FOR_XIAOCHE))
			{
				String[] getmsg=readinfo.split(GET_SUBJECT_FOUR_FOR_XIAOCHE);
				String sres=DBUtil.getTiMu_SumFour(getmsg[1]);
				System.out.println("sres=="+sres);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			
			//===================
			
			//============图片上传
			//后添加
			else if(readinfo.startsWith(addTiemain))
			{
				//加tiemain
				String[] getmsg=readinfo.split(addTiemain);
				String isok=DBUtil.addTiemain(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.equals(getReplyMaxID))
			{
				
				String sres=DBUtil.getReplyMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(addReply))
			{
				//加tiemain
				String[] getmsg=readinfo.split(addReply);
				String isok=DBUtil.addReply(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(updateUserTouxiang))
			{
				String[] getmsg=readinfo.split(updateUserTouxiang);
				String isok=DBUtil.updateUserTouxiang(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//由账号得到用户ID GETID_BY_ZHANGHAO
			else if(readinfo.startsWith(GETID_BY_ZHANGHAO))
			{
				String[] getmsg=readinfo.split(GETID_BY_ZHANGHAO);
				String isok=DBUtil.getUserIdByZhangHao(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//由ID得到用户账号 GETID_BY_ZHANGHAO
			else if(readinfo.startsWith(getUserzhanghaoByid))
			{
				String[] getmsg=readinfo.split(getUserzhanghaoByid);
				String isok=DBUtil.getUserzhanghaoByid(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//得到所有的考题整理表信息
			else if(readinfo.equals(getQzhengli))
			{
				
				String sres=TypeExchangeUtil.listToString(DBUtil.getQzhengli());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//得到错题表最大ID
			else if(readinfo.equals(getErrorMaxID))
			{
				
				String sres=DBUtil.getErrorMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//添加错题
			else if(readinfo.startsWith(addError))
			{
				String[] getmsg=readinfo.split(addError);
				String isok=DBUtil.addError(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//删除错题根据用户
			else if(readinfo.startsWith(delErrorByu))
			{
				String[] getmsg=readinfo.split(delErrorByu);
				String isok=DBUtil.delErrorByu(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//得到错题表信息
			else if(readinfo.startsWith(gete_que_idByu))
			{
				String[] getmsg=readinfo.split(gete_que_idByu);
				String sres=TypeExchangeUtil.listToString(DBUtil.gete_que_idByu(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(getErrorCount))
			{
				String[] getmsg=readinfo.split(getErrorCount);
				String sres=DBUtil.getErrorCount(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//得到驾校城市
			else if(readinfo.equals(getDrivescCity))
			{
				String liststr=TypeExchangeUtil.listToString(DBUtil.getDrivescCity());
				IOUtil.writeStr(out, MyConverter.escape(liststr));
			}
			//得到城市中的驾校
			else if(readinfo.startsWith(getDrivescCityName))
			{
				String[] getmsg=readinfo.split(getDrivescCityName);
				String liststr=TypeExchangeUtil.listToString(DBUtil.getDrivescCityName(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(liststr));
			}
			//通过视频名称和视频对应的小车编号得到 视频的地址
			else if(readinfo.startsWith(GET_ADDRESS_BY_NAMEANDCARID))
			{
				String[] getmsg=readinfo.split(GET_ADDRESS_BY_NAMEANDCARID);
				String isok=DBUtil.getAddressByNameAndId(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}

			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}// 关闭流
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String gettime() {// 创建时间获取方法
		Calendar calendar = Calendar.getInstance(); // 使用默认时区和语言环境获得一个日历
		calendar.setTime(new java.util.Date()); // 使用给定的 Date 设置此 Calendar 的时间
		int year = calendar.get(Calendar.YEAR); // 返回给定日历年字段的值
		int month = calendar.get(Calendar.MONTH) + 1; // 返回给定日历月字段的值
		int day = calendar.get(Calendar.DAY_OF_MONTH); // 返回给定日历日字段的值
		int hour = calendar.get(Calendar.HOUR_OF_DAY); // 返回给定日历时字段的值
		int minute = calendar.get(Calendar.MINUTE); // 返回给定日历分字段的值
		int second = calendar.get(Calendar.SECOND);// 返回给定日历秒字段的值
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":"
				+ second;// 将得到的时间信息返回
	}
}
