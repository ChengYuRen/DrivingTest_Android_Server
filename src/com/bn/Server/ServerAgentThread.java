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

	private Socket sc; // ����Socket
	private DataInputStream in;// ��������������
	private DataOutputStream out;// �������������
	private static final String ok = "ok";// �����ַ���ok
	private static final String fail = "fail";// �����ַ���fail

	public ServerAgentThread(Socket sc) {// ���幹����
		try {
			this.sc = sc;// ����Socket
			in = new DataInputStream(sc.getInputStream());// ����������������
			out = new DataOutputStream(sc.getOutputStream()); // ���������������
		} catch (Exception e) {
			e.printStackTrace();// �����쳣
		}
	}

	public void run() 
	{
		try
		{
			String readinfo = IOUtil.readStr(in);
			System.out.println("Feedback information:->" + readinfo);
			if (readinfo.startsWith(SUPER_LOGIN)) //�ж����ݿ��и��˺ŵ������Ƿ���ȷ    Ȼ���ж��Ƿ���Ե�¼
				{
				String[] getmsg = readinfo.split(SUPER_LOGIN);
				String zaizhi=DBUtil.getZaizhiByAdminer(getmsg[1]);
				if(!zaizhi.equals("��"))
				{
					String wmm = DBUtil.pwdAdminer(getmsg[1]);
			if (wmm!=null && wmm.equals(getmsg[2])) {// ����Ϣ���գ��ҹ���Ա������ȷ
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
				//���û�
				String[] getmsg=readinfo.split(ADD_USER);
				String isok=DBUtil.addUsersb(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			else if(readinfo.equals(GET_USER))
			{
				//�õ������û�
				//==========================================
				String liststr=TypeExchangeUtil.listToString(DBUtil.getUsersb());
				
				System.out.print(liststr);
				IOUtil.writeStr(out, MyConverter.escape(liststr));
			}
			
			
			else if(readinfo.startsWith(USER_NOT_TALK))
			{
				//����
				String[] getmsg=readinfo.split(USER_NOT_TALK);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.updateUserdlToObsect(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(USER_CAN_TALK))
			{
				//������
				String[] getmsg=readinfo.split(USER_CAN_TALK);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.updateUserdlToObsectTow(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			
			else if(readinfo.startsWith(ADM_NOT_TALK))
			{
				//����
				String[] getmsg=readinfo.split(ADM_NOT_TALK);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.updateAdmlToObsect(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(ADM_CAN_TALK))
			{
				//������
				String[] getmsg=readinfo.split(ADM_CAN_TALK);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.updateAdmlToObsectTow(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			else if(readinfo.startsWith(ADD_ADMIN))
			{
				//�ӹ���
				String[] getmsg=readinfo.split(ADD_ADMIN);
				String isok=DBUtil.addAdminer(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(DEL_ADMIN))
			{
				//ɾ����
				String[] getmsg=readinfo.split(DEL_ADMIN);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String isok=DBUtil.delAdminer(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.equals(GET_ADMID))
			{
				//�õ����й���Ա
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
				DBUtil.delReplybyid(getmsg[1]);//��ɾ��  �����ӵ����лظ�   Ȼ����ɾ��������
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
				String[] getmsg=readinfo.split(addTieclass);//�õ�Ҫɾ�������
				String isok=DBUtil.addTieclass(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(delTieclass))
			{
				String[] getmsg=readinfo.split(delTieclass);
				String[] tiemid=DBUtil.gettmidByclass(getmsg[1]);//�������id   �ҵ��������������id
				for(int i=0;i<tiemid.length;i++)
				{
					DBUtil.delReplybyid(tiemid[i]);//ɾ��ÿ������  �����лظ�
				}
				for(int i=0;i<tiemid.length;i++)
				{
					DBUtil.delTiemain(tiemid[i]);//����id  ɾ��ÿ������µ�����
				}
				
				String isok=DBUtil.delTieclass(getmsg[1]);//������ɾ��  Ӧ����ɾ��������µ�����   +�ظ�
				
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
				String[] getmsg=readinfo.split(delQuestion);//��ǰ  getmsg[1]ΪҪɾ����  ������
					DBUtil.delErrorByque(getmsg[1]);//ɾ��  ������е�   �����
					DBUtil.delQzhengli(getmsg[1]);
					DBUtil.delAnswer(getmsg[1]);
					String isok=DBUtil.delQuestion(getmsg[1]);
				//System.out.println(isok);
					IOUtil.writeStr(out, MyConverter.escape(isok));
			}
		
			//����
			else if(readinfo.startsWith(ADD_QUESTION))
			{
				//������
				String[] getmsg=readinfo.split(ADD_QUESTION);
				//System.out.println(getmsg[1]+"fdsfadfasdfasdf");
				String sres=TypeExchangeUtil.listToString(DBUtil.getQuestion());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			//==================��У
			else if(readinfo.startsWith(JiaXiao_XinXi))
			{
				//���û�
				String[] getmsg=readinfo.split(JiaXiao_XinXi);
				String sres=TypeExchangeUtil.listToString(DBUtil.getDrivesc());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			else if(readinfo.startsWith(JiaXiao_Add))
			{
				//���û�
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
				String[] getmsg=readinfo.split(updateTieclass);//�õ�Ҫ�޸ĵ����
				String isok=DBUtil.updateTieclass(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
//����Ƶ
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
			//����
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
			else if(readinfo.startsWith(dandftiemain))//ɾ���������ظ�  �����û����
			{
				String[] getmsg=readinfo.split(dandftiemain);
				//���ó������ӵ��û�
				String user=DBUtil.getUserBytiemainid(getmsg[1]);
				//���û����ò��ɵ�¼
				DBUtil.updateUserdlToObsect(user);
				DBUtil.delReplybyid(getmsg[1]);//��ɾ��  �����ӵ����лظ�   Ȼ����ɾ��������
				String isok=DBUtil.delTiemain(getmsg[1]);
				
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//��У���ID
			else if(readinfo.equals(JiaXiao_GetMaxId))
			{
				
				String sres=DBUtil.getDrivescMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//����Ա���ID
			else if(readinfo.equals(ADM_GETMAXID))
			{
				
				String sres=DBUtil.getAdminerMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//���������CHEXING_MAXID
			else if(readinfo.equals(CHEXING_MAXID))
			{
				
				String sres=DBUtil.getCarTypeMaxId();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//��Ƶ���ID  getVideoMaxID
			else if(readinfo.equals(getVideoMaxID))
			{
				
				String sres=DBUtil.getVideoMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//==========��У��
			else if(readinfo.startsWith(delDrivesc))
			{
			String[] getmsg=readinfo.split(delDrivesc);//�õ���Ҫɾ���ļ�Уid
			String[] user=DBUtil.getuseridBydrive(getmsg[1]);
			for(int i=0;i<user.length;i++)
			{
			DBUtil.updateuserdrive(user[i]);
			}
			String isok=DBUtil.delDrivesc(getmsg[1]);

			IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//�����
			else if(readinfo.equals(GET_USERFB))
			{
				String liststr=TypeExchangeUtil.listToString(DBUtil.getuserfb());
				System.out.print(liststr);
				IOUtil.writeStr(out, MyConverter.escape(liststr));
			}
			//��ID�õ���У��Ϣ n 
			else if(readinfo.startsWith(GET_JIAXIAO_MESSAGE))
			{
				String[] getmsg=readinfo.split(GET_JIAXIAO_MESSAGE);
				String sres=TypeExchangeUtil.listToString(DBUtil.getDrivescdById(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//���ļ�У��ϢUPDATE_JIAXIAO 
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
				String[] getmsg=readinfo.split(delCarType);//�õ���Ҫɾ���ĳ���id
				//��ɾ����Ƶ
				
				DBUtil.delVideobycar(getmsg[1]);
				//���û�������ѡ��ó��͵� �ó���
				
				String[] user=DBUtil.getuseridBycar(getmsg[1]);
				//�������û��� ���͵�  ��Ϊ��  ��01
				for(int i=0;i<user.length;i++)
				{
					
					DBUtil.updateusercar(user[i]);
				}
				//���������
				//ͨ������������ҵ��������ڸó��͵�����id
				String stall[]=DBUtil.getstidBycar(getmsg[1]);
				for(int j=0;j<stall.length;j++)
				{
					//ɾ��������͵Ŀ��������
					String []standcar=new String[2];
					standcar[0]=stall[j];
					standcar[1]=getmsg[1];
					DBUtil.delqzbycarque(standcar);
				}
				//���������ó���ɾ��    �ж���������
				for(int j=0;j<stall.length;j++)
				{
					String carall[]=DBUtil.getcaridByst(stall[j]);//�õ��������  ����id
					if(carall[0]==null)
					{
						//�������ֻ����һ������
						//ɾ�������
						
						DBUtil.delErrorByque(stall[j]);//ɾ��  ������е�   �����
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
			// ��������
			else if (readinfo.startsWith(TESTCONNECT)) 
			{
				IOUtil.writeStr(out, MyConverter.escape("success"));// ����ʾ�ɹ���Ϣд����
			}
			//���ݴ�������ͼƬλ��  �õ�ͼƬ�����ͻ�ȥ
			else if(readinfo.startsWith(getimage))
			{
				String[] getmsg=readinfo.split(getimage);//getmsg[1]ΪͼƬλ��
				String tupianxinxi=Pcode.GetImageStr(getmsg[1]);//��ͼƬ��Ϣװ��Ϊ�ַ���
				IOUtil.writeStr(out, MyConverter.escape(tupianxinxi));
			}
			//�ֻ��û���¼
			else if (readinfo.startsWith(PHONE_LOGIN)) //�ж����ݿ��и��˺ŵ������Ƿ���ȷ    Ȼ���ж��Ƿ���Ե�¼
			{
			String[] getmsg = readinfo.split(PHONE_LOGIN);
			String zaizhi=DBUtil.getdengluByUSER(getmsg[1]);
			if(!zaizhi.equals("��"))
			{
				String wmm = DBUtil.pwdUSER(getmsg[1]);
				if (wmm!=null&&wmm.equals(getmsg[2])) {// ����Ϣ���գ��ҹ���Ա������ȷ
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
			//�õ�����������Ϣ
			else if(readinfo.startsWith(PERSON_ALL))
			{
				//����������Ҫ��
				String[] getmsg=readinfo.split(PERSON_ALL);
				String sres=TypeExchangeUtil.listToString(DBUtil.getUserAllByZhangHao(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(sres));
				
			}
			//�õ�ͷ��
			else if(readinfo.startsWith(getUsertouxiangByid))
			{
				String[] getmsg=readinfo.split(getUsertouxiangByid);//�õ���Ҫȡͷ����˺�
				//�����ͷ������ж�
				String touxiang=DBUtil.getUsertouxiangByid(getmsg[1]);
				if(!touxiang.equals("��"))
				{
					String tupianxinxi=Pcode.GetImageStr(touxiang);//��ͼƬ��Ϣװ��Ϊ�ַ���
					IOUtil.writeStr(out, MyConverter.escape(tupianxinxi));
				}
				else 
				{
					IOUtil.writeStr(out, MyConverter.escape("��"));
				}
				
			}
			//������Ŀ�õ����������ĿID
			else if(readinfo.startsWith(getTiemainIDByName))
			{
				String[] getmsg=readinfo.split(getTiemainIDByName);
				String sres=DBUtil.getTiemainIDByName(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			else if(readinfo.startsWith(UPDATE_PERSON_SEX_MAN))
			{
				//���Ա�Ϊ ��
				String[] getmsg=readinfo.split(UPDATE_PERSON_SEX_MAN);
				String isok=DBUtil.updatePerSon_SexToMen(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			else if(readinfo.startsWith(UPDATE_PERSON_SEX_WOMAN))
			{
				//���Ա�Ϊ Ů
				String[] getmsg=readinfo.split(UPDATE_PERSON_SEX_WOMAN);
				String isok=DBUtil.updatePerSon_SexToWoMen(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			//�����ǳ� �ֻ���
			else if(readinfo.startsWith(UPDATE_PERSON_NICHENG))
			{
				String[] getmsg=readinfo.split(UPDATE_PERSON_NICHENG);
				String sres=(DBUtil.updateNiCheng(getmsg));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			//�����ǳ� �ֻ���
			else if(readinfo.startsWith(UPDATE_PERSON_PHONE))
			{
				String[] getmsg=readinfo.split(UPDATE_PERSON_PHONE);
				String sres=(DBUtil.updatePhone(getmsg));
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			//�ֻ��û����ID
			else if(readinfo.equals(GET_MAXUSER_ID_FORPHOEN))
			{
				
				String sres=DBUtil.getUserMaxIdForPhone();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//����ֻ��û�
			else if(readinfo.startsWith(FORPHONE))
			{
				String[] getmsg=readinfo.split(FORPHONE);
				String isok=DBUtil.addUserForPhone(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//�õ������û����˺�
			else if(readinfo.equals(GET_USER_ZHANGHAO_FORPHONE))
			{
				//==========================================
				String liststr=TypeExchangeUtil.listToString(DBUtil.getUserSbZhangHao());
				System.out.print(liststr);
				IOUtil.writeStr(out, MyConverter.escape(liststr));
				//IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//�һ��û�����
			else if(readinfo.startsWith(GET_USER_PASSWORD_FORPHONE))
			{
				String[] getmsg=readinfo.split(GET_USER_PASSWORD_FORPHONE);
				String isok=DBUtil.getPassWordByZhanghaoAndAnquanma(getmsg[1],getmsg[2]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			
			// ��Ŀһ ����   ͨ�����εõ��� С���������� ����
			else if(readinfo.startsWith(GET_SUBJECT_ONE_FOR_XIAOCHE))
			{
				String[] getmsg=readinfo.split(GET_SUBJECT_ONE_FOR_XIAOCHE);
				String sres=DBUtil.getTiMu_SumOne(getmsg[1]);
				System.out.println("sres=="+sres);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//С�� ��Ŀ�� ��������
			else if(readinfo.startsWith(GET_SUBJECT_FOUR_FOR_XIAOCHE))
			{
				String[] getmsg=readinfo.split(GET_SUBJECT_FOUR_FOR_XIAOCHE);
				String sres=DBUtil.getTiMu_SumFour(getmsg[1]);
				System.out.println("sres=="+sres);
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			
			
			//===================
			
			//============ͼƬ�ϴ�
			//�����
			else if(readinfo.startsWith(addTiemain))
			{
				//��tiemain
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
				//��tiemain
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
			//���˺ŵõ��û�ID GETID_BY_ZHANGHAO
			else if(readinfo.startsWith(GETID_BY_ZHANGHAO))
			{
				String[] getmsg=readinfo.split(GETID_BY_ZHANGHAO);
				String isok=DBUtil.getUserIdByZhangHao(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//��ID�õ��û��˺� GETID_BY_ZHANGHAO
			else if(readinfo.startsWith(getUserzhanghaoByid))
			{
				String[] getmsg=readinfo.split(getUserzhanghaoByid);
				String isok=DBUtil.getUserzhanghaoByid(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//�õ����еĿ����������Ϣ
			else if(readinfo.equals(getQzhengli))
			{
				
				String sres=TypeExchangeUtil.listToString(DBUtil.getQzhengli());
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//�õ���������ID
			else if(readinfo.equals(getErrorMaxID))
			{
				
				String sres=DBUtil.getErrorMaxID();
				IOUtil.writeStr(out, MyConverter.escape(sres));
			}
			//��Ӵ���
			else if(readinfo.startsWith(addError))
			{
				String[] getmsg=readinfo.split(addError);
				String isok=DBUtil.addError(getmsg);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//ɾ����������û�
			else if(readinfo.startsWith(delErrorByu))
			{
				String[] getmsg=readinfo.split(delErrorByu);
				String isok=DBUtil.delErrorByu(getmsg[1]);
				IOUtil.writeStr(out, MyConverter.escape(isok));
			}
			//�õ��������Ϣ
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
			//�õ���У����
			else if(readinfo.equals(getDrivescCity))
			{
				String liststr=TypeExchangeUtil.listToString(DBUtil.getDrivescCity());
				IOUtil.writeStr(out, MyConverter.escape(liststr));
			}
			//�õ������еļ�У
			else if(readinfo.startsWith(getDrivescCityName))
			{
				String[] getmsg=readinfo.split(getDrivescCityName);
				String liststr=TypeExchangeUtil.listToString(DBUtil.getDrivescCityName(getmsg[1]));
				IOUtil.writeStr(out, MyConverter.escape(liststr));
			}
			//ͨ����Ƶ���ƺ���Ƶ��Ӧ��С����ŵõ� ��Ƶ�ĵ�ַ
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
			}// �ر���
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

	public String gettime() {// ����ʱ���ȡ����
		Calendar calendar = Calendar.getInstance(); // ʹ��Ĭ��ʱ�������Ի������һ������
		calendar.setTime(new java.util.Date()); // ʹ�ø����� Date ���ô� Calendar ��ʱ��
		int year = calendar.get(Calendar.YEAR); // ���ظ����������ֶε�ֵ
		int month = calendar.get(Calendar.MONTH) + 1; // ���ظ����������ֶε�ֵ
		int day = calendar.get(Calendar.DAY_OF_MONTH); // ���ظ����������ֶε�ֵ
		int hour = calendar.get(Calendar.HOUR_OF_DAY); // ���ظ�������ʱ�ֶε�ֵ
		int minute = calendar.get(Calendar.MINUTE); // ���ظ����������ֶε�ֵ
		int second = calendar.get(Calendar.SECOND);// ���ظ����������ֶε�ֵ
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":"
				+ second;// ���õ���ʱ����Ϣ����
	}
}
