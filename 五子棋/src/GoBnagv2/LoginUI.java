package GoBnagv2;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

/**
 * 这个类为login洁面时的实现，窗体的创建，背景图片添加，注册登录按钮的添加，输入账号密码文本框的添加，为按钮文本框添加监听器。
 */
public class LoginUI extends JFrame {

    private static final ImageIcon bgimage = new ImageIcon("Image/startbg.png");
    //当前界面的画笔
    private final LoginLister gl=new LoginLister();

        public void initLoginUI()  {
            this.setTitle("Welcome");//设置login界面title
            this.setLocationRelativeTo(null);
            this.setLocation(500,200);//设置login界面位置
            this.setSize(500, 500);//设置login界面大小
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击关闭按钮结束程序
            BorderLayout border=new BorderLayout();
            this.setLayout(border);//布局为边框布局
            this.setResizable(false);//不可以resize登录界面大小

            gl.setLoginUI(this);//将本UI界面传给login的监听器用于重绘


            JPanel loginbutPanel= new JPanel();
            Dimension panelDimension=new Dimension(0,this.getHeight()/6);
            loginbutPanel.setPreferredSize(panelDimension);


            JPanel ImagePanel= new JPanel();
            Dimension ImagepanelSize=new Dimension(100,this.getHeight()/2);
            ImagePanel.setPreferredSize(ImagepanelSize);

            Image img = bgimage.getImage();// 获得此图标的Image
            img = img.getScaledInstance(500, this.getHeight()/2, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
            bgimage.setImage(img);// 将图标设置为压缩后的图像
            JLabel iconLabel = new JLabel(bgimage);
            ImagePanel.add(iconLabel);

            JPanel loginTextPanel= new JPanel();
            Dimension TextpanelSize=new Dimension(100,this.getHeight()/4);
            loginTextPanel.setPreferredSize(TextpanelSize);
            //创建用户名文本框
            JTextField username = new JTextField();
            Dimension username_text = new Dimension(300, 50);
            username.setPreferredSize(username_text);
            loginTextPanel.add(username);
//
//
            //创建密码文本框
            JPasswordField password = new JPasswordField();
            Dimension password_text = new Dimension(300, 50);
            password.setPreferredSize(password_text);
//        password.setText(password.getText()+"请输入密码");
             loginTextPanel.add(password);


            //创建登录button
            JButton login_button = new JButton("登录");
            login_button.setPreferredSize(new Dimension(90, 50));
            Color fg = new Color(0, 173, 232);
            login_button.setForeground(fg);
            loginbutPanel.add(login_button);


            this.add(ImagePanel,BorderLayout.NORTH);
            this.add(loginTextPanel,BorderLayout.CENTER);
            this.add(loginbutPanel,BorderLayout.SOUTH);



            //给登录按钮添加动作监听器方法
            //接口：不能直接创建对象，重新定义类实现(继承)接口，重写接口中的抽象方法

            gl.setUsername(username);
            gl.setPassword(password);
            login_button.addActionListener(gl);

            //创建username文本框的监听器，聚焦
            TextResponse text_r = new TextResponse();
            text_r.setText(username);
            username.addFocusListener(text_r);

            PasswordResponse password_r = new PasswordResponse();
            password_r.setPassword(password);
//            username.addFocusListener(password_r);

            this.setVisible(true);
        }


    }


