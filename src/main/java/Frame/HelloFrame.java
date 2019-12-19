package Frame;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelloFrame extends JFrame {
    JPanel panel;
    JLabel lb_welcome;
    JButton bt_in;
    JLabel lb_picture;
    String filename="D:\\idea\\work\\src\\main\\resources\\hello.png";
    ImageIcon image;

    public HelloFrame(){
        panel=new JPanel();
        panel.setLayout(new FlowLayout());
        image=new ImageIcon(filename);
        Image img=image.getImage();
        img=img.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        image.setImage(img);
        lb_picture=new JLabel(image);
        lb_welcome=new JLabel("欢迎来到北京化工大学论文管理系统");
        lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
        lb_welcome.setFont(new Font("等线",Font.BOLD,40));
        bt_in=new JButton("进入论文库");
        bt_in.setBackground(new Color(135,206,235));
        bt_in.setFont(new Font("等线",Font.BOLD,15));
        Dimension preferredSize = new Dimension(300,50);
        bt_in.setPreferredSize(preferredSize );
        setLayout(new GridLayout(3,1));
        setVisible(true);
        setSize(1000,600);
        setTitle("欢迎");
        setResizable(false);
        this.setIconImage(new ImageIcon("D:\\idea\\work\\src\\main\\resources\\icon1.png").getImage());
        add(lb_picture);
        add(lb_welcome);
        add(panel);
        panel.add(bt_in);
        bt_in.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainFrame_admin();
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


    public static void main(String[] args) {
        new HelloFrame();
    }
}
