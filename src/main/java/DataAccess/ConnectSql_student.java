package DataAccess;

import Entity.normalUser;

import java.sql.*;

public class ConnectSql_student {
    String url;
    String username;
    String password;
    Statement sta;
    public ConnectSql_student()throws ClassNotFoundException, SQLException {
        //1、注册驱动
        //告知JVM使用的是哪一个数据库的驱动
        //DriverManager.registerDriver(new Driver());
        //把com.mysql.jdbc.Driver这个实现类直接扔到内存里
        //扔到内存之后调用static静态代码块
        //静态代码块中注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");//2
        //2、获得连接对象
        //使用JDBC中的类，完成对MySQL数据库的连接
        //第一部分是jdbc，这是固定的；
        //第二部分是数据库名称，那么连接mysql数据库，第二部分当然是mysql了；
        //第三部分是由数据库厂商规定的，我们需要了解每个数据库厂商的要求，mysql的第三部分分别由数据库服务器的IP地址（localhost）、端口号（3306），以及DATABASE名称(mydb)组成。
        url="jdbc:mysql://localhost:3306/school?serverTimezone=UTC&characterEncoding=utf8&useSSL=false";//1
        username="root";
        password="123456";
        Connection conn= DriverManager.getConnection(url,username,password);
        //3、获得语句执行平台
        //4、通过连接对象获取对SQL语句的执行者对象
        sta=conn.createStatement();
    }
    //新建学生信息
    public void insert(normalUser normalUser1) throws SQLException{
        String id= normalUser1.getId();
        String name= normalUser1.getName();
        String gender= normalUser1.getGender();
        int age= normalUser1.getAge();
        String password= normalUser1.getPassword();
        sta.execute("INSERT INTO student values ('"+id+"','"+name+"','"+gender+"','"+age+"','"+password+"')");
    }
    //更新学生信息
    public void update(normalUser normalUser1, String tx_id) throws SQLException{
        String t_id= normalUser1.getId();
        String t_name= normalUser1.getName();
        String t_gender= normalUser1.getGender();
        int t_age= normalUser1.getAge();
        String t_password= normalUser1.getPassword();
        sta.execute("UPDATE student SET id='"+t_id+"',name='"+t_name+"',gender='"+t_gender+"',age='"+t_age+"',password='"+t_password+"'");
    }

    //查询学生信息
    public Boolean select(String in_id,String in_password) throws SQLException{
        ResultSet resultSet=sta.executeQuery("SELECT * from student");

        while(resultSet.next()){
            String id=resultSet.getString("id");
            String name=resultSet.getString("name");
            String gender=resultSet.getString("gender");
            int age=resultSet.getInt("age");
            String password=resultSet.getString("password");

            if(in_id.equals(id) && in_password.equals(password)){
                return true;
            }
        }
        System.out.println("error");
        return false;
    }

    //删除学生信息
    public void delete(String tx_id) throws SQLException{
        sta.execute("DELETE FROM student WHERE id='"+tx_id+"'");
    }

}
