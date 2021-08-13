package GoBnagv2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;

public class LoginLister extends GoBangLister {
    //创建用户名和密码属性
    private JTextField username;
    private JPasswordField password;
    private static final String default_name="admin";
    private static final String default_password="123456";

    public LoginUI loginUI;

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }
    //创建set方法
    public void setUsername(JTextField username){
        this.username=username;
    }
    public void setPassword(JPasswordField password){
        this.password = password;
    }
    //处理方法
    public void actionPerformed(ActionEvent e){
        String name=username.getText();//获取用户输入得信息
        String password1 = String.valueOf(password.getPassword());
        resultWindow result=new resultWindow();//创建一个显示结果的窗体
        if (default_name.equals(name) && default_password.equals(password1)){//登陆成功
            System.out.println("登陆成功");
            loginUI.setVisible(false);//登录界面隐藏
            StartUI startUI=new StartUI();//创建一个开始界面的窗体并初始化
            startUI.initStratUI();

        }else if(default_name.equals(name)){
            result.loginresult("密码不正确");//密码不正确
        }else{
            result.loginresult("用户名不存在");//用户名不存在
        }
    }
}
