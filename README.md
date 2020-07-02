# DrivingTest_Android_Server
服务器程序，IDEA运行即可，相当于你的本机是一个服务器

IDEA直接导入项目即可运行，服务器程序是执行增删改查的等信息，
在Constant 下修改对应数据库的数据库名，账户和密码，特别注意com.mysql.cj.jdbc.Driver这个新版本的驱动
    1. public static final String MySQL_Connection_URL="jdbc:mysql://localhost/driving_test?useSSL=false&serverTimezone=UTC";
    2. public static final String MySQL_Root_User="root";
    3.public static final String MySQL_Root_Password="123456";
    4.public static final String MySQL_Driver_Version_8="com.mysql.cj.jdbc.Driver";
我的数据库名称：driving_test

数据库文件详见.sql
