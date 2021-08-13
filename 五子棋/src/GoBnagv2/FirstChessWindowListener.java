package GoBnagv2;

import java.awt.event.ActionEvent;

public class FirstChessWindowListener extends GoBangLister {
    private FirstChessWindow firstui;

    public void setFirstui(FirstChessWindow firstui) {
        this.firstui = firstui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operation = e.getActionCommand();//获取点击的按钮信息
        if (operation.equals("白棋")) {// 人人对战
            GongbangUI gobang=new GongbangUI();//创建一个下棋界面并初始化
            gobang.initGoBangWindow(1);
            firstui.setVisible(false);

        }else if (operation.equals("黑棋")) {// 人机器对战
            GongbangUI gobang=new GongbangUI();
            gobang.initGoBangWindow(0);
            firstui.setVisible(false);
    }
}}
