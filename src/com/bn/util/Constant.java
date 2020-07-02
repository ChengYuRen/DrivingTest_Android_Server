package com.bn.util;

public class Constant {

	public static final int carsxlen=4;//车型表  的所含属性个数
	public static final int adminersxlen=6;//管理员表属性个数
	public static final int drivescsxlen=5;//驾校信息表属性个数
	public static final int tieclasssxlen=2;//主贴类别表属性个数
	public static final int usersbsxlen=9;//用户表属性个数
	public static final int tiemainsxlen=6;//主贴表表属性个数
	public static final int replysxlen=5;//主贴表表属性个数
	public static final int questionsxlen=7;//试题表属性个数
	public static final int answersxlen=5;//试题表属性个数
	
	//驾校
	public static final String JiaXiao_XinXi="JiaXiao_XinXi";
	public static final String JiaXiao_Add="JiaXiao_Add";
	public static final String JiaXiao_GetMaxId="JiaXiao_GetMaxId";
	public static final String delDrivesc="delDrivesc";//根据ID删除所选驾校
	public static final String GET_JIAXIAO_MESSAGE="GET_JIAXIAO_MESSAGE";
	public static final String UPDATE_JIAXIAO="UPDATE_JIAXIAO";
	public static final String getDriverNameById="getDriverNameById";//根据id 找打驾校名称
	
	public static final String SUPER_LOGIN="SUPER_LOGIN";//管理员登陆密码
	 public static final String updateTieclass="updateTieclass";//根据ID更改帖子类型名称

	//车型表
	public static final String CheXingXinXi="CheXingXinXi";//得到车型的所有信息
	public static final String CheXingAdd="CheXingAdd";
	public static final String CHEXING_MAXID="CHEXING_MAXID";
	public static final String updateCarType="updateCarType";//更改车型信息
	public static final String delCarType="delCarType";//删除车型信息
	public static final String getcarById="getcarById";//删除车型信息
	public static final String getCarTypeNameById="getCarTypeNameById";//根据车型id 得到车型名称

	
	//试题界面
	
	public static final String ADD_QUESTION="ADD_QUESTION";
	public static final String getQuestionByid="getQuestionByid"; //根据id得到试题其他信息
	public static final String getAnswerqByid="getAnswerqByid"; //根据id 找到试题的所欲选项内容
	public static final String getAnswerById="getAnswerById"; //根据id 找到试题的所欲选项内容
	public static final String updateQuestion="updateQuestion"; //根据id  更改试题内容
	public static final String delAnswer="delAnswer"; //根据id  删除试题全部达纳
	public static final String updateAnswerall="updateAnswerall"; //根据答案id 及试题id  更改其他信息
	public static final String getAnswerAByid="getAnswerAByid"; //得到试题的A 选线位置
	
	public static final String GET_ADDRESS_BY_NAMEANDCARID="GET_ADDRESS_BY_NAMEANDCARID";//通过视频名称和视频对应的小车编号得到 视频的地址
	
	//添加用户界面
	public static final String ADD_USER="ADD_USER";
	public static final String GET_USER="GET_USER";
	public static final String DEL_USER="DEL_USER";
	public static final String USER_NOT_TALK="USER_NOT_TALK";
	public static final String USER_CAN_TALK="USER_CAN_TALK";
	public static final String GET_USERFB="GET_USERFB";//查询被封号的用户
	public static final String getUserNameById="getUserNameById";//根据id查询用户昵称
	public static final String getUseridByName="getUseridByName";//根据昵称查询用户id
	//封号表
	public static final String GET_FENGHAO="GET_FENGHAO";
	public static final String ADD_FENGHAO="ADD_FENGHAO";
	public static final String DEL_FENGHAO="DEL_FENGHAO";
	public static final String GET_FengHaoMaxID="GET_FengHaoMaxID";


	//管理员界面
	public static final String ADD_ADMIN="ADD_ADMIN";
	public static final String DEL_ADMIN="DEL_ADMIN";
	public static final String GET_ADMID="GET_ADMID";
	public static final String ADM_NOT_TALK="ADM_NOT_TALK";
	public static final String ADM_CAN_TALK="ADM_CAN_TALK";
	public static final String ADM_GETMAXID="ADM_GETMAXID";
	
	//论坛
	public static final String GET_REPLYbyid="GET_REPLYbyid";//得到所有的回复   根据id
	public static final String DEL_REPLY="DEL_REPLY";//删除所选回复     
	public static final String delTiemain="delTiemain";//删除主贴表的  帖子   根据id
	public static final String getTiemain="getTiemain";//得到  所有发过的帖子
	public static final String getTiemainbyuser="getTiemainbyuser";//得到  该用户  所有发过的帖子
	public static final String getTiemainbyclass="getTiemainbyclass";//得到  该类别  所有发过的帖子
	public static final String delTieclass="delTieclass";//根据类别id  删除帖子类别
	public static final String getTieclass="getTieclass";//得到所有的主贴类别
	public static final String getTieclassMaxID="getTieclassMaxID";//得到主贴类别最大id
	public static final String addTieclass="addTieclass";//添加新的类别
	public static final String getTiemainMaxID="getTiemainMaxID";//得到主贴表的最大ID
	public static final String dandftiemain="dandftiemain";//删除主贴并不允许用户登录
	public static final String getTieclassNameById="getTieclassNameById";//根据id得到主贴类别的名字
	public static final String getTieclassidByName="getTieclassidByName";//根据类别名称得到id
	
	//试题
	public static final String addQuestion="addQuestion";//添加新的试题
	public static final String getQuestionMaxID="getQuestionMaxID";//最大ID
	public static final String delQuestion="delQuestion";//根据id  删除试题
	
	
	//答案表
	public static final String getAnswerMaxID="getAnswerMaxID";//得到答案的最大id
	public static final String addAnswer="addAnswer";//添加  一个新的答案

	
	//视频表
	public static final String addVideo="addVideo";//添加  一个新的视频
	public static final String getVideoMaxID="getVideoMaxID";//得到视频编号的最大id
	public static final String GET_VIEDIO="GET_VIEDIO";
	public static final String DEL_VIEDIO="DEL_VIEDIO";
	public static final String getVideoById="getVideoById";//得到视频信息 根据id
	public static final String updateVideo="updateVideo";//更改视频信息

	//车型表
		public static final String getCarType="getCarType";//得到车型的所有信息
		
		
		//考题整理表
		public static final String addQzhengli="addQzhengli";//想考题整理表  输入数据
		public static final String getQzhenglicarByque="getQzhenglicarByque";//根据试题id 找到对应的车型
		public static final String delQzhengli="delQzhengli";//根据试题编号  删除整理表内容
		//更改   安卓端    实验看
		//驾校信息
		public static final String getjiaxiaoxinxi="getjiaxiaoxinxi";//得到所有信息
		//试题
		public static final String getAnswerneirongByid="getAnswerneirongByid";//得到id试题的大难内容
		public static final String getQuestion="getQuestion";//得到所有的试题
		public static final String getAnswerAll="getAnswerAll";//得到所有的试题
		//测试连接专用
		public static final String TESTCONNECT = "TESTCONNECT";//测试连接
		public static final String PHONE_LOGIN = "PHONE_LOGIN";
		public static final String PERSON_ALL = "PERSON_ALL";
		public static final String getimage="getimage";//向服务器得到图片
		public static final String getUsertouxiangByid="getUsertouxiangByid";//根据id查询用户头像
		public static final String getTiemainIDByName="getTiemainIDByName";//根据题目内容查询题目ID
		//晴儿添加  少了再添
		public static final String UPDATE_PERSON_SEX_MAN="UPDATE_PERSON_SEX_MAN";//修改性别为男性
		public static final String UPDATE_PERSON_SEX_WOMAN="UPDATE_PERSON_SEX_WOMAN";//修改性别为女性
		public static final String UPDATE_PERSON_NICHENG="UPDATE_PERSON_NICHENG";//更新昵称
		public static final String GET_MAXUSER_ID_FORPHOEN="GET_MAXUSER_ID_FORPHOEN";//得到用户最大ID
		public static final String FORPHONE="FORPHONE";//添加手机用户
		public static final String UPDATE_PERSON_PHONE="UPDATE_PERSON_PHONE";//更新电话
		public static final String GET_USER_ZHANGHAO_FORPHONE="GET_USER_ZHANGHAO_FORPHONE";//得到手机用户的账号
		public static final String GET_USER_PASSWORD_FORPHONE="GET_USER_PASSWORD_FORPHONE";//找回密码
		
		//二次添加
		public static final String GET_SUBJECT_ONE_FOR_XIAOCHE="GET_SUBJECT_ONE_FOR_XIAOCHE";//获取小车科目一的题目总数
		public static final String GET_SUBJECT_FOUR_FOR_XIAOCHE="GET_SUBJECT_FOUR_FOR_XIAOCHE";//获取小车科目四的题目总数
		//以上为  晴儿添加
		
		public static final String addTiemain="addTiemain";//添加一个新的帖子
		public static final String getReplyMaxID="getReplyMaxID";//得到回复的最大ID
		public static final String addReply="addReply";//添加一个新的回复
		public static final String updateUserTouxiang="updateUserTouxiang";//更改用户头像图片
		public static final String GETID_BY_ZHANGHAO="GETID_BY_ZHANGHAO";//由用户账号 得到ID
		public static final String getQzhengli="getQzhengli";//得到考题整理表信息
		//5-5
		public static final String getErrorMaxID="getErrorMaxID";//得到错题表最大ID
		public static final String addError="addError";//添加错题表
		public static final String delErrorByu="delErrorByu";//根据用户删除错题表
		public static final String gete_que_idByu="gete_que_idByu";//根据用户得到错误试题的编号
		public static final String getErrorCount="getErrorCount";//根据用户得到错误试题的编号
		public static final String getUserzhanghaoByid="getUserzhanghaoByid";//根据用户ID得到一户账号
		public static final String getDrivescCity="getDrivescCity";//查询驾校中城市
		public static final String getDrivescCityName="getDrivescCityName";//查询城市中驾校
	//数据库的常量
	   public static final String MySQL_Connection_URL="jdbc:mysql://localhost/driving_test?useSSL=false&serverTimezone=UTC";
	   public static final String MySQL_Root_User="root";
	   public static final String MySQL_Root_Password="123456";
	   public static final String MySQL_Driver_Version_8="com.mysql.cj.jdbc.Driver";
}

