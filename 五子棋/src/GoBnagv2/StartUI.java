package GoBnagv2;

import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class StartUI extends JFrame implements GoabngConfig {

    private static final ImageIcon bgimage = new ImageIcon("Image/startbg.png");
    private final StartLister gl=new StartLister() ;
    private final String[] butname = {"双人对战", "人机对战", "关闭声音", "退出游戏", "退出登录"};
    public void initStratUI()  {
        gl.setStartUI(this);
        this.setTitle("五子棋v2.0");
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);
        this.setLocation(500,200);//设置login界面位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel ImagePanel= new JPanel();
        Dimension ImagepanelSize=new Dimension(100,350);
        ImagePanel.setPreferredSize(ImagepanelSize);

        Image img = bgimage.getImage();// 获得此图标的Image
        img = img.getScaledInstance(500, 350, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
        bgimage.setImage(img);// 将图标设置为压缩后的图像
        JLabel iconLabel = new JLabel(bgimage);
        ImagePanel.add(iconLabel);
        this.add(ImagePanel,BorderLayout.NORTH);

        JPanel butPanel=new JPanel();
        JPanel windowPanel=new JPanel();
        windowPanel.setBackground(Color.WHITE);
        this.add(windowPanel);
        butPanel.setBackground(Color.WHITE);
        Dimension butPanelSize=new Dimension(0,100); // 设置放置按钮的Panel的大小
        butPanel.setPreferredSize(butPanelSize);
        for (String s : butname) {
            JButton but = new JButton();
            but.setText(s);
            but.setPreferredSize(new Dimension(80, 40));
            but.addActionListener(gl);
            butPanel.add(but);
        }
        this.add(butPanel,BorderLayout.SOUTH);
        this.setVisible(true);
        // 获取界面画笔传给监听器，当前窗体添加监听器
        this.addMouseListener(gl);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }


}
