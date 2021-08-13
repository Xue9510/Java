package GoBangv1;

import javax.swing.*;
import java.awt.*;

/**
 * 创建五子棋UI界面
 * 属性：1.bgimage 界面的背景照片
 *      2.gl 五子棋的棋盘操作监听器，是一个GoBangLister对象（GoBangLister对象是自己定义的一个类）
 *      3.butname 一个用于存放五子棋界面按钮名字的数组，存放界面功能按钮名字
 *
 * 方法：1.initGoBangWindow() 初始化创建UI界面，创建功能按钮，获取界面画笔传给监听器
 *      2. getState
 *
 * 重写方法：1.paint（） 重写paint方法保证刷新的时候不会下消失
 */
public class GongbangUI extends JFrame implements GoabngConfig {

    private static final Image bgimage=new ImageIcon("Image/bg.jpg").getImage();
    private final GoBangLister gl=new GoBangLister();
    private final String[] butname={"双人对战","人机对战","重新开始","悔棋"};

    /**
     * initGoBangWindow() 初始化创建UI界面
     * 创建功能按钮
     * 获取界面画笔传给监听器
     */

    public  void initGoBangWindow(){
        //初始化创建UI界面
        this.setTitle("五子棋v1.0");
        this.setLocationRelativeTo(null);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 创建一个Panel放置按钮，放在界面的右边
        BorderLayout border=new BorderLayout();
        this.setLayout(border);
        JPanel butPanel=new JPanel();
        JPanel windowPanel=new JPanel();
        windowPanel.setBackground(Color.WHITE);
        this.add(windowPanel);
        butPanel.setBackground(Color.WHITE);
        Dimension butPanelSize=new Dimension(150,0); // 设置放置按钮的Panel的大小
        butPanel.setPreferredSize(butPanelSize);
        // 创建功能按钮并添加action监听器
        for (String s : butname) {
            JButton but = new JButton();
            but.setText(s);
            but.setPreferredSize(new Dimension(100, 50));
            but.addActionListener(gl);
            butPanel.add(but);
        }
        this.add(butPanel,BorderLayout.EAST);
        this.setVisible(true);
        // 获取界面画笔传给监听器，当前窗体添加监听器
        this.addMouseListener(gl);
        Graphics g  =this.getGraphics();
//        System.out.println(g);
        gl.setGraphics(g);
        gl.setUIwindow(this);

    }


    /**
     * paint（） 重写paint方法保证刷新的时候棋子和棋盘不会消失
     * @param g Graphics 当前窗体的画笔
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制背景图
        g.drawImage(bgimage,X-SIZE,Y-SIZE,ROW*SIZE+2*SIZE,COLUM*SIZE+2*SIZE,null);
        // 绘制棋盘
        for (int i=0;i<=15;i++){
            g.drawLine(X,Y+i*SIZE,X+ROW*SIZE,Y+i*SIZE);
            g.drawLine(X+i*SIZE,Y,X+i*SIZE,Y+COLUM*SIZE);
        }
        //新建一个QIZI类，通过GoBangLister的getchessinfor方法得到当前已经下了棋的棋子位置信息然后重新绘画
        QIZI[] location=gl.getchessinfor();
        for (int i=0;i<location.length;i++){
            if(location[i]!=null){
                g.setColor(location[i].c);
                g.fillOval(location[i].x,location[i].y,SIZE,SIZE);

            }
        }

    }
}
