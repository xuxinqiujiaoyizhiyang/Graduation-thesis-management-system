package Frame;

import DataAccess.ConnectSql_paper;
import Entity.Paper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class MainFrame_admin extends JFrame{
    JButton cg_Button;
    JButton de_register;

    JMenu menu1;
    JMenu menu2;

    JLabel  lb_title;
    JLabel  lb_name;
    JLabel  lb_author;
    JLabel  lb_id;
    JLabel  lb_major;
    JLabel  lb_class;
    JLabel  lb_year;
    JLabel lb_padding1;
    JLabel lb_padding2;
    JTable tb_paper;
    JScrollPane jsp2;

    ConnectSql_paper connectSql_paper;
    String[] title={"论文编号","论文名字","作者姓名","专业","班级","毕业年份"};
    String[][] data;
    String user="管理员";

    public MainFrame_admin(){

        try{
            connectSql_paper=new ConnectSql_paper();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        create();
        setLayout(new GridBagLayout());
        setSize(1000,600);
        setVisible(true);
        setResizable(false);
        setTitle("学生论文管理系统（"+user+"身份)");

        //上侧的具体工具面板
        JPanel toolSelectPanel = new JPanel();
        toolSelectPanel.setLayout(new GridLayout(1,1));
        toolSelectPanel.add(lb_title);
        this.add(toolSelectPanel, new GBC(0,0).
                setFill(GBC.BOTH).setIpad(800, 40).setWeight(0, 0));

        //中间的具体工具面板
        JPanel toolConcretePanel = new JPanel();
        toolConcretePanel.setLayout(new GridLayout(1,1));
        toolConcretePanel.add(jsp2);
        this.add(toolConcretePanel,new GBC(0,1).
                setFill(GBC.BOTH).setIpad(900, 90).setWeight(0, 100));

        //下侧的颜色选择面板
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new FlowLayout());
        colorPanel.add(new JLabel("Copyright © 2019   BUCT. All rights reserved."));
        this.add(colorPanel,new GBC(0,2).
                setFill(GBC.BOTH).setIpad(0,5).setWeight(0, 0));
        //setBackground(Color.BLACK);
        //getContentPane().setVisible(false);
        //setBackground(Color.GRAY);

        //add(panel2);
        //add(panel1);
        //add(jsp2);

        /*panel1.add(lb_padding2);
        panel1.add(lb_name);
        panel1.add(lb_author);
        panel1.add(lb_id);
        panel1.add(lb_class);
        panel1.add(lb_major);
        panel1.add(lb_year);
        panel1.add(lb_padding1);*/



        cg_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Change_record();
            }
        });

        de_register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    ConnectSql_paper connectSql=new ConnectSql_paper();
                    connectSql.delete(Login.id);
                }
                catch (ClassNotFoundException e1){
                    e1.printStackTrace();
                }catch (SQLException e1){
                    e1.printStackTrace();
                }

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
    //创建控件对象
    private void create() {
        //创建菜单
        menu1 = new JMenu("操作");
        menu2 = new JMenu("设置");
        menu1.add("增加论文");
        menu1.add("删除论文");
        menu2.add("个人信息");
        menu2.add("切换用户");
        JMenuBar jmb = new JMenuBar();
        this.setJMenuBar(jmb);
        jmb.add(menu1);
        jmb.add(menu2);

        // Panel panel1=new Panel();
        //panel1.setLayout(new GridLayout(1,6));
        Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 3));


        lb_title = new JLabel("学生论文管理系统");
        lb_title.setHorizontalAlignment(JLabel.CENTER);
        lb_title.setFont(new Font("等线", Font.BOLD, 20));
        lb_name = new JLabel("论文名字");
        lb_name.setHorizontalAlignment(JLabel.CENTER);
        lb_name.setBorder(BorderFactory.createLineBorder(Color.black));
        //lb_name.setOpaque(true);
        //lb_name.setBackground(Color.red);
        lb_author = new JLabel("作者名字");
        lb_author.setHorizontalAlignment(JLabel.CENTER);
        lb_author.setBorder(BorderFactory.createLineBorder(Color.black));
        //lb_author.setOpaque(true);
        //lb_author.setBackground(Color.red);
        lb_id = new JLabel("论文编号");
        lb_id.setHorizontalAlignment(JLabel.CENTER);
        lb_id.setBorder(BorderFactory.createLineBorder(Color.black));
        lb_major = new JLabel("专业");
        lb_major.setHorizontalAlignment(JLabel.CENTER);
        lb_major.setBorder(BorderFactory.createLineBorder(Color.black));
        lb_class = new JLabel("班级");
        lb_class.setHorizontalAlignment(JLabel.CENTER);
        lb_class.setBorder(BorderFactory.createLineBorder(Color.black));
        lb_year = new JLabel("毕业年份");
        lb_year.setHorizontalAlignment(JLabel.CENTER);
        lb_year.setBorder(BorderFactory.createLineBorder(Color.black));
        try {
            data=new String[connectSql_paper.select().length][6];
            for (int i = 0; i < connectSql_paper.select().length; i++) {
                Paper[] papers;
                papers = connectSql_paper.select();
                data[i][0] = papers[i].getId();
                data[i][1] = papers[i].getName();
                data[i][2] = papers[i].getAuthor();
                data[i][3] = papers[i].getMajor();
                data[i][4] = papers[i].getA_class();
                data[i][5] = papers[i].getYear() + "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            tb_paper = new JTable(data, title) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        tb_paper.getColumn("论文编号").setMaxWidth(60);
        tb_paper.getColumn("作者姓名").setMaxWidth(100);
        tb_paper.getColumn("毕业年份").setMaxWidth(100);
        tb_paper.getColumn("专业").setMaxWidth(400);
        tb_paper.getColumn("专业").setMinWidth(200);
        tb_paper.getColumn("班级").setMaxWidth(100);

        tb_paper.getTableHeader().setReorderingAllowed(false);

            tb_paper.getTableHeader().setResizingAllowed(false);
            //tb_paper.setBorder(BorderFactory.createEtchedBorder());
            //tb_paper.getTableHeader().setVisible(false);
            //DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            //renderer.setPreferredSize(new Dimension(0, 0));
            //tb_paper.getTableHeader().setDefaultRenderer(renderer);

            cg_Button = new JButton("修改");
            cg_Button.setSize(20, 20);
            de_register = new JButton("删除");
            jsp2 = new JScrollPane(tb_paper, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }
    public static void main(String[] args) {
        new MainFrame_admin();
    }
}
