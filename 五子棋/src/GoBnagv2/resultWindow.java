package GoBnagv2;

import javax.swing.*;

/**
 * 这个类用来设定弹出结果框，结果会弹出一个窗体显示谁赢了
 *
 */

public  class resultWindow {
    public static void showresult(String win){
        JFrame resultwindow=new JFrame("比赛结果");
        resultwindow.setLocationRelativeTo(null);
        resultwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        resultwindow.setSize(300, 150);
        JLabel label=new JLabel(win,SwingConstants.CENTER);
        resultwindow.add(label);
        resultwindow.setVisible(true);
    }
    public  void loginresult(String rw) {
        JFrame rw1=new JFrame();//新的窗体
        rw1.setTitle("登录结果");//窗体title
        rw1.setVisible(true);
        rw1.setLocationRelativeTo(null);
        rw1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        rw1.setSize(300, 150);
        JLabel label=new JLabel(rw,SwingConstants.CENTER);
        rw1.add(label);
    }

}
