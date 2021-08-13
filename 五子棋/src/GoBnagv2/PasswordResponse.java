package GoBnagv2;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PasswordResponse implements FocusListener {

    JPasswordField password;
        public void setPassword(JPasswordField password){
            this.password=password;
        }
        public void focusGained(FocusEvent a) {
            if ((String.valueOf(password.getPassword()).equals("")) |(String.valueOf(password.getPassword()).equals("请输入密码"))){
//                password=("");
                password.setText("请输入密码");

            }

        }

        public void focusLost(FocusEvent a) {
            if (String.valueOf(password.getPassword()).equals("")){
//                password=("请输入密码");
                password.setText("");

            }

        }

}
