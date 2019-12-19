package Frame;

import DataAccess.ConnectSql_student;
import Entity.normalUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Register extends Frame {
    Set<String> gender=new HashSet<String>(Arrays.asList(new String[]{"男","女"}));

    JTextField tx_id;
    JTextField tx_name;
    JTextField tx_age;
    JPasswordField tx_password;
    JPasswordField tx_secondpassword;

    JLabel lb_title1;
    JLabel lb_title2;
    JLabel lb_id;
    JLabel lb_name;
    JLabel lb_gender;
    JLabel lb_age;
    JLabel lb_password;
    JLabel lb_secondpassword;

    JComboBox<String> cb_gender;

    JButton bt_register;
    JButton bt_exit;

    public Register(){
    tx_id=new JTextField(10);
        tx_id.setFont(new Font("宋体",Font.BOLD,18));
    tx_name=new JTextField(10);
        tx_name.setFont(new Font("宋体",Font.BOLD,18));
    tx_age=new JTextField(5);
        tx_age.setFont(new Font("宋体",Font.BOLD,18));
    tx_password=new JPasswordField(5);
        tx_password.setFont(new Font("宋体",Font.BOLD,18));
    tx_secondpassword=new JPasswordField(5);
        tx_secondpassword.setFont(new Font("宋体",Font.BOLD,18));


    lb_id=new JLabel("学号:");
        lb_id.setFont(new Font("等线",Font.BOLD,20));
        lb_id.setHorizontalAlignment(JLabel.CENTER);
    lb_name=new JLabel("姓名:");
        lb_name.setHorizontalAlignment(JLabel.CENTER);
        lb_name.setFont(new Font("等线",Font.BOLD,20));
    lb_gender=new JLabel("性别:");
        lb_gender.setHorizontalAlignment(JLabel.CENTER);
        lb_gender.setFont(new Font("等线",Font.BOLD,20));
    lb_age=new JLabel("年龄:");
        lb_age.setHorizontalAlignment(JLabel.CENTER);
        lb_age.setFont(new Font("等线",Font.BOLD,20));
    lb_password=new JLabel("密码:");
        lb_password.setHorizontalAlignment(JLabel.CENTER);
        lb_password.setFont(new Font("等线",Font.BOLD,20));
    lb_secondpassword=new JLabel("重新输入密码：");
        lb_secondpassword.setHorizontalAlignment(JLabel.CENTER);
        lb_secondpassword.setFont(new Font("等线",Font.BOLD,20));

    lb_title1=new JLabel("教务管理系统");
        lb_title1.setHorizontalAlignment(JLabel.RIGHT);
        lb_title1.setFont(new Font("等线",Font.BOLD,25));
    lb_title2=new JLabel("学生信息注册");
        lb_title2.setFont(new Font("等线",Font.BOLD,25));

    cb_gender=new JComboBox<String>();
        cb_gender.setFont(new Font("宋体",Font.BOLD,18));

    bt_register=new JButton("注册");
        bt_register.setFont(new Font("宋体",Font.BOLD,18));
    bt_exit=new JButton("退出");
        bt_exit.setFont(new Font("宋体",Font.BOLD,18));

        for (String initgender:gender) {
        cb_gender.addItem(initgender);
    }

    add(lb_title1);
    add(lb_title2);
    add(lb_id);
    add(tx_id);
    add(lb_name);
    add(tx_name);
    add(lb_gender);
    add(cb_gender);
    add(lb_age);
    add(tx_age);
    add(lb_password);
    add(tx_password);
    add(lb_secondpassword);
    add(tx_secondpassword);
    add(bt_register);
    add(bt_exit);
    setLayout(new GridLayout(8,2,0,10));
    setSize(800,500);
    setVisible(true);
    setResizable(false);
    setTitle("注册");
        this.setIconImage(new ImageIcon("D:\\idea\\work\\src\\main\\resources\\register.png").getImage());

    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            (e.getWindow()).dispose();
        }
    });

        bt_register.addActionListener(new ActionListener()  {
        public void actionPerformed(ActionEvent e) {
            try{
                if(tx_id.getText().equals("")||tx_name.getText().equals("")||cb_gender.getSelectedItem().toString().equals("")||tx_age.getText().equals("")||tx_password.getPassword().toString().equals("")) {
                    int res = JOptionPane.showConfirmDialog(null, "请确定全部项均以输入", "输入错误", JOptionPane.DEFAULT_OPTION);
                }
                else{
                    String password=String.valueOf(tx_password.getPassword());
                    String second=String.valueOf(tx_secondpassword.getPassword());
                    if (password.equals(second)) {
                        normalUser newstudent = new normalUser(tx_id.getText(), tx_name.getText(), cb_gender.getSelectedItem().toString(), Integer.parseInt(tx_age.getText()), String.valueOf(tx_password.getPassword()));
                        ConnectSql_student connectSql = new ConnectSql_student();
                        connectSql.insert(newstudent);
                        dispose();
                    }
                    else{
                        int res1 = JOptionPane.showConfirmDialog(null, "两次密码输入不同，请重试", "错误", JOptionPane.DEFAULT_OPTION);
                    }
                }
                }
            catch (ClassNotFoundException e1){
                e1.printStackTrace();
            }catch (SQLException e1){
                e1.printStackTrace();
            }

        }
    });
}
}
