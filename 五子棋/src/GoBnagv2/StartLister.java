package GoBnagv2;

import java.awt.event.ActionEvent;

public class StartLister extends GoBangLister {


    public void actionPerformed(ActionEvent e) {
        String operation = e.getActionCommand();//获取点击的按钮信息
        if (operation.equals("双人对战")) {// 人人对战
            FirstChessWindow firstchess=new FirstChessWindow();
            firstchess.initFirstChessWindow();
            this.getStartUI().setVisible(false);

        }else if (operation.equals("人机对战")) {// 人机器对战
            FirstChessWindow firstchess=new FirstChessWindow();
            firstchess.initFirstChessWindow();

            this.getStartUI().setVisible(false);
        }else if(operation.equals("关闭声音")) { //关闭当前正在播放的背景音乐声音

            System.out.println("111");

        }else if (operation.equals("退出")) {//退出游戏，将当前页面设置为不可见
            this.getStartUI().setVisible(false);

        }else if (operation.equals("退出登录")) {//退出登录，将当前页面设置为不可见，新建一个login对象并初始化
            this.getStartUI().setVisible(false);
            LoginUI login=new LoginUI();
            login.initLoginUI();
    }
}
}
