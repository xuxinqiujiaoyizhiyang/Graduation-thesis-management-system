package DataAccess;

import Entity.Paper;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectSql_paper {
    String url;
    String username;
    String password;
    Statement sta;
    public ConnectSql_paper()throws ClassNotFoundException, SQLException {
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
    //新建论文信息
    public void insert(Paper paper) throws SQLException{
        String id=paper.getId();
        String name=paper.getName();
        String author=paper.getAuthor();
        String major=paper.getMajor();
        String a_class=paper.getA_class();
        int year=paper.getYear();
        sta.execute("INSERT INTO paper values ('"+id+"','"+name+"','"+author+"','"+major+"','"+a_class+"')");
    }
    //更新论文信息
    public void update(Paper paper,String id) throws SQLException{
        String name=paper.getName();
        String author=paper.getAuthor();
        String major=paper.getMajor();
        String a_class=paper.getA_class();
        int year=paper.getYear();
        sta.execute("UPDATE paper SET name='"+name+"',author='"+author+"',major='"+major+"',classnum='"+a_class+"',year='"+year+"' WHERE id='"+id+"'");
    }

    //查询学生信息
    public Paper[] select() throws SQLException{
        int i=0;
        ResultSet resultSet=sta.executeQuery("SELECT * from paper");
        //ResultSet resultSet1=sta.executeQuery("SELECT count(*)  from paper");
        resultSet.last();
        Paper[] papers=new Paper[resultSet.getRow()];
        List list=new ArrayList();
        resultSet.beforeFirst();
        while(resultSet.next()){

            list.add(resultSet.getString("id"));
            list.add(resultSet.getString("name"));
            list.add(resultSet.getString("author"));
            list.add(resultSet.getString("major"));
            list.add(resultSet.getString("classnum"));
            papers[i]=new Paper(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("author"),resultSet.getString("major"),resultSet.getString("classnum"),resultSet.getInt("year"));
            i++;
        }
        return papers;
    }

    //删除学生信息
    public void delete(String tx_id) throws SQLException{
        sta.execute("DELETE FROM paper WHERE id='"+tx_id+"'");
    }

}
