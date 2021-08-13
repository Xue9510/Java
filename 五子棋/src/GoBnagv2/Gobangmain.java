package GoBnagv2;

import java.net.MalformedURLException;

public class Gobangmain {
    /**
    *GoBngmain 启动游戏
     * 创建一个GongbangUI实体，调用初始化方法initGoBangWindow
     */

    public static void main(String[] args)  {
//        GongbangUI gobang=new GongbangUI();
//        gobang.initGoBangWindow();
        LoginUI login=new LoginUI();
        login.initLoginUI();
    }


}
