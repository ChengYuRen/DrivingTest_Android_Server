package com.bn.util;

public class Constant {

	public static final int carsxlen=4;//���ͱ�  ���������Ը���
	public static final int adminersxlen=6;//����Ա�����Ը���
	public static final int drivescsxlen=5;//��У��Ϣ�����Ը���
	public static final int tieclasssxlen=2;//�����������Ը���
	public static final int usersbsxlen=9;//�û������Ը���
	public static final int tiemainsxlen=6;//����������Ը���
	public static final int replysxlen=5;//����������Ը���
	public static final int questionsxlen=7;//��������Ը���
	public static final int answersxlen=5;//��������Ը���
	
	//��У
	public static final String JiaXiao_XinXi="JiaXiao_XinXi";
	public static final String JiaXiao_Add="JiaXiao_Add";
	public static final String JiaXiao_GetMaxId="JiaXiao_GetMaxId";
	public static final String delDrivesc="delDrivesc";//����IDɾ����ѡ��У
	public static final String GET_JIAXIAO_MESSAGE="GET_JIAXIAO_MESSAGE";
	public static final String UPDATE_JIAXIAO="UPDATE_JIAXIAO";
	public static final String getDriverNameById="getDriverNameById";//����id �Ҵ��У����
	
	public static final String SUPER_LOGIN="SUPER_LOGIN";//����Ա��½����
	 public static final String updateTieclass="updateTieclass";//����ID����������������

	//���ͱ�
	public static final String CheXingXinXi="CheXingXinXi";//�õ����͵�������Ϣ
	public static final String CheXingAdd="CheXingAdd";
	public static final String CHEXING_MAXID="CHEXING_MAXID";
	public static final String updateCarType="updateCarType";//���ĳ�����Ϣ
	public static final String delCarType="delCarType";//ɾ��������Ϣ
	public static final String getcarById="getcarById";//ɾ��������Ϣ
	public static final String getCarTypeNameById="getCarTypeNameById";//���ݳ���id �õ���������

	
	//�������
	
	public static final String ADD_QUESTION="ADD_QUESTION";
	public static final String getQuestionByid="getQuestionByid"; //����id�õ�����������Ϣ
	public static final String getAnswerqByid="getAnswerqByid"; //����id �ҵ����������ѡ������
	public static final String getAnswerById="getAnswerById"; //����id �ҵ����������ѡ������
	public static final String updateQuestion="updateQuestion"; //����id  ������������
	public static final String delAnswer="delAnswer"; //����id  ɾ������ȫ������
	public static final String updateAnswerall="updateAnswerall"; //���ݴ�id ������id  ����������Ϣ
	public static final String getAnswerAByid="getAnswerAByid"; //�õ������A ѡ��λ��
	
	public static final String GET_ADDRESS_BY_NAMEANDCARID="GET_ADDRESS_BY_NAMEANDCARID";//ͨ����Ƶ���ƺ���Ƶ��Ӧ��С����ŵõ� ��Ƶ�ĵ�ַ
	
	//����û�����
	public static final String ADD_USER="ADD_USER";
	public static final String GET_USER="GET_USER";
	public static final String DEL_USER="DEL_USER";
	public static final String USER_NOT_TALK="USER_NOT_TALK";
	public static final String USER_CAN_TALK="USER_CAN_TALK";
	public static final String GET_USERFB="GET_USERFB";//��ѯ����ŵ��û�
	public static final String getUserNameById="getUserNameById";//����id��ѯ�û��ǳ�
	public static final String getUseridByName="getUseridByName";//�����ǳƲ�ѯ�û�id
	//��ű�
	public static final String GET_FENGHAO="GET_FENGHAO";
	public static final String ADD_FENGHAO="ADD_FENGHAO";
	public static final String DEL_FENGHAO="DEL_FENGHAO";
	public static final String GET_FengHaoMaxID="GET_FengHaoMaxID";


	//����Ա����
	public static final String ADD_ADMIN="ADD_ADMIN";
	public static final String DEL_ADMIN="DEL_ADMIN";
	public static final String GET_ADMID="GET_ADMID";
	public static final String ADM_NOT_TALK="ADM_NOT_TALK";
	public static final String ADM_CAN_TALK="ADM_CAN_TALK";
	public static final String ADM_GETMAXID="ADM_GETMAXID";
	
	//��̳
	public static final String GET_REPLYbyid="GET_REPLYbyid";//�õ����еĻظ�   ����id
	public static final String DEL_REPLY="DEL_REPLY";//ɾ����ѡ�ظ�     
	public static final String delTiemain="delTiemain";//ɾ���������  ����   ����id
	public static final String getTiemain="getTiemain";//�õ�  ���з���������
	public static final String getTiemainbyuser="getTiemainbyuser";//�õ�  ���û�  ���з���������
	public static final String getTiemainbyclass="getTiemainbyclass";//�õ�  �����  ���з���������
	public static final String delTieclass="delTieclass";//�������id  ɾ���������
	public static final String getTieclass="getTieclass";//�õ����е��������
	public static final String getTieclassMaxID="getTieclassMaxID";//�õ�����������id
	public static final String addTieclass="addTieclass";//����µ����
	public static final String getTiemainMaxID="getTiemainMaxID";//�õ�����������ID
	public static final String dandftiemain="dandftiemain";//ɾ���������������û���¼
	public static final String getTieclassNameById="getTieclassNameById";//����id�õ�������������
	public static final String getTieclassidByName="getTieclassidByName";//����������Ƶõ�id
	
	//����
	public static final String addQuestion="addQuestion";//����µ�����
	public static final String getQuestionMaxID="getQuestionMaxID";//���ID
	public static final String delQuestion="delQuestion";//����id  ɾ������
	
	
	//�𰸱�
	public static final String getAnswerMaxID="getAnswerMaxID";//�õ��𰸵����id
	public static final String addAnswer="addAnswer";//���  һ���µĴ�

	
	//��Ƶ��
	public static final String addVideo="addVideo";//���  һ���µ���Ƶ
	public static final String getVideoMaxID="getVideoMaxID";//�õ���Ƶ��ŵ����id
	public static final String GET_VIEDIO="GET_VIEDIO";
	public static final String DEL_VIEDIO="DEL_VIEDIO";
	public static final String getVideoById="getVideoById";//�õ���Ƶ��Ϣ ����id
	public static final String updateVideo="updateVideo";//������Ƶ��Ϣ

	//���ͱ�
		public static final String getCarType="getCarType";//�õ����͵�������Ϣ
		
		
		//���������
		public static final String addQzhengli="addQzhengli";//�뿼�������  ��������
		public static final String getQzhenglicarByque="getQzhenglicarByque";//��������id �ҵ���Ӧ�ĳ���
		public static final String delQzhengli="delQzhengli";//����������  ɾ�����������
		//����   ��׿��    ʵ�鿴
		//��У��Ϣ
		public static final String getjiaxiaoxinxi="getjiaxiaoxinxi";//�õ�������Ϣ
		//����
		public static final String getAnswerneirongByid="getAnswerneirongByid";//�õ�id����Ĵ�������
		public static final String getQuestion="getQuestion";//�õ����е�����
		public static final String getAnswerAll="getAnswerAll";//�õ����е�����
		//��������ר��
		public static final String TESTCONNECT = "TESTCONNECT";//��������
		public static final String PHONE_LOGIN = "PHONE_LOGIN";
		public static final String PERSON_ALL = "PERSON_ALL";
		public static final String getimage="getimage";//��������õ�ͼƬ
		public static final String getUsertouxiangByid="getUsertouxiangByid";//����id��ѯ�û�ͷ��
		public static final String getTiemainIDByName="getTiemainIDByName";//������Ŀ���ݲ�ѯ��ĿID
		//������  ��������
		public static final String UPDATE_PERSON_SEX_MAN="UPDATE_PERSON_SEX_MAN";//�޸��Ա�Ϊ����
		public static final String UPDATE_PERSON_SEX_WOMAN="UPDATE_PERSON_SEX_WOMAN";//�޸��Ա�ΪŮ��
		public static final String UPDATE_PERSON_NICHENG="UPDATE_PERSON_NICHENG";//�����ǳ�
		public static final String GET_MAXUSER_ID_FORPHOEN="GET_MAXUSER_ID_FORPHOEN";//�õ��û����ID
		public static final String FORPHONE="FORPHONE";//����ֻ��û�
		public static final String UPDATE_PERSON_PHONE="UPDATE_PERSON_PHONE";//���µ绰
		public static final String GET_USER_ZHANGHAO_FORPHONE="GET_USER_ZHANGHAO_FORPHONE";//�õ��ֻ��û����˺�
		public static final String GET_USER_PASSWORD_FORPHONE="GET_USER_PASSWORD_FORPHONE";//�һ�����
		
		//�������
		public static final String GET_SUBJECT_ONE_FOR_XIAOCHE="GET_SUBJECT_ONE_FOR_XIAOCHE";//��ȡС����Ŀһ����Ŀ����
		public static final String GET_SUBJECT_FOUR_FOR_XIAOCHE="GET_SUBJECT_FOUR_FOR_XIAOCHE";//��ȡС����Ŀ�ĵ���Ŀ����
		//����Ϊ  ������
		
		public static final String addTiemain="addTiemain";//���һ���µ�����
		public static final String getReplyMaxID="getReplyMaxID";//�õ��ظ������ID
		public static final String addReply="addReply";//���һ���µĻظ�
		public static final String updateUserTouxiang="updateUserTouxiang";//�����û�ͷ��ͼƬ
		public static final String GETID_BY_ZHANGHAO="GETID_BY_ZHANGHAO";//���û��˺� �õ�ID
		public static final String getQzhengli="getQzhengli";//�õ������������Ϣ
		//5-5
		public static final String getErrorMaxID="getErrorMaxID";//�õ���������ID
		public static final String addError="addError";//��Ӵ����
		public static final String delErrorByu="delErrorByu";//�����û�ɾ�������
		public static final String gete_que_idByu="gete_que_idByu";//�����û��õ���������ı��
		public static final String getErrorCount="getErrorCount";//�����û��õ���������ı��
		public static final String getUserzhanghaoByid="getUserzhanghaoByid";//�����û�ID�õ�һ���˺�
		public static final String getDrivescCity="getDrivescCity";//��ѯ��У�г���
		public static final String getDrivescCityName="getDrivescCityName";//��ѯ�����м�У
	//���ݿ�ĳ���
	   public static final String MySQL_Connection_URL="jdbc:mysql://localhost/driving_test?useSSL=false&serverTimezone=UTC";
	   public static final String MySQL_Root_User="root";
	   public static final String MySQL_Root_Password="123456";
	   public static final String MySQL_Driver_Version_8="com.mysql.cj.jdbc.Driver";
}

