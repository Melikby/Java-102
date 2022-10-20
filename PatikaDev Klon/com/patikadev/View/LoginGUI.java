package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Operator;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_pass;
    private JButton btn_login;
    private JButton btn_signUp;
    private JLabel lbl_yokMu;
    private JLabel lbl_üye_ol;
    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    public LoginGUI(){
        add(wrapper);
        setSize(400,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_user_uname)||Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }else{
                User u= User.getFetch(fld_user_uname.getText(),fld_user_pass.getText());
                if(u==null){
                    Helper.showMsg("Kullanıcı Bulunamadı !");
                }else{
                    switch (u.getType()){
                        case "operator" :
                            OperatorGUI opGUI = new OperatorGUI((Operator) u);
                            break;
                        case "educator":
                            uname = fld_user_uname.getText();
                            EducatorGUI edGUI = new EducatorGUI();
                            break;
                        case "student":
                            uname = fld_user_uname.getText();
                            User user = null;
                            for (User obj : User.getList()) {
                                if (obj.getUname().equals(uname)) {
                                    user = obj;
                                    break;
                                }
                            }
                            StudentGUI std = new StudentGUI(user);
                            break;
                    }
                    dispose();
                }
            }
        });
        btn_signUp.addActionListener(e -> {

            SignupWindow signupWindow=new SignupWindow();


        });

    }


    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI login=new LoginGUI();


    }
}
