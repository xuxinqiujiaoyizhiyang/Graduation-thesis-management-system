package Frame;

import DataAccess.ConnectSql_student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class Login extends Frame {
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JLabel lb_id;
    JLabel lb_password;
    JLabel lb_title;

    JTextField tx_id;
    JPasswordField tx_password;

    JButton bt_Button;
    JButton bt_register;
    public static String id;
    public Login(){
        panel1=new JPanel();
        panel1.setLayout(new FlowLayout());
        panel2=new JPanel();
        panel2.setLayout(new GridLayout(1,2));
        panel3=new JPanel();
        panel3.setLayout(new GridLayout(1,2));
        panel4=new JPanel();
        panel4.setLayout(new GridLayout(1,2));

        lb_title=new JLabel("学生论文管理系统登陆");
        lb_title.setHorizontalAlignment(SwingConstants.CENTER);
        lb_title.setVerticalAlignment(SwingConstants.CENTER);
        lb_title.setFont(new Font("等线",Font.BOLD,25));
        lb_id=new JLabel("学号：");
        lb_id.setFont(new Font("等线",Font.BOLD,20));
        lb_id.setHorizontalAlignment(JLabel.CENTER);
        lb_password=new JLabel("密码：");
        lb_password.setFont(new Font("等线",Font.BOLD,20));
        lb_password.setHorizontalAlignment(JLabel.CENTER);

        tx_id=new JTextField(10);
        tx_id.setFont(new Font("宋体",Font.BOLD,18));
        tx_password=new JPasswordField(10);
        tx_password.setFont(new Font("宋体",Font.BOLD,18));

        bt_Button=new JButton("登陆");
        bt_Button.setBackground(new Color(245,245,245));
        bt_register=new JButton("注册");
        bt_register.setBackground(new Color(245,245,245));

        setLayout(new GridLayout(4,1,0,30));
        setSize(600,400);
        setVisible(true);
        setResizable(false);
        this.setIconImage(new ImageIcon("D:\\idea\\work\\src\\main\\resources\\login.png").getImage());
        setTitle("登陆");
        panel1.add(lb_title);
        panel2.add(lb_id);
        panel2.add(tx_id);
        panel3.add(lb_password);
        panel3.add(tx_password);
        panel4.add(bt_Button);
        panel4.add(bt_register);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        bt_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    ConnectSql_student connectSql=new ConnectSql_student();
                    if(connectSql.select(tx_id.getText(),tx_password.getText())){
                        id=tx_id.getText();
                        setVisible(false);
                        new HelloFrame();
                    }
                }catch (ClassNotFoundException e1){
                    e1.printStackTrace();
                }
                catch (SQLException e1){
                    e1.printStackTrace();
                }

            }
        });

        bt_register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                (e.getWindow()).dispose();
                System.exit(0);
            }
        });
    }
}
