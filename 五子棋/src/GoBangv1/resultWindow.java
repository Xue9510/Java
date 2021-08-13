package GoBangv1;

import javax.swing.*;

/**
 * 这个类用来设定弹出结果框，结果会弹出一个窗体显示谁赢了
 *
 */

public class resultWindow {
    public static void showresult(String win){
        JFrame resultwindow=new JFrame("比赛结果");
        resultwindow.setLocationRelativeTo(null);
        resultwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        resultwindow.setSize(300, 150);
        JLabel label=new JLabel(win,SwingConstants.CENTER);
        resultwindow.add(label);

        resultwindow.setVisible(true);
    }


}
