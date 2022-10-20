package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpOPAndEdu extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_sign;
    private JLabel lbl_username;
    private JTextField fld_pass;
    private JTextField fld_uname;
    private JLabel lbl_pass;
    private JButton btn_signupeduop;
    private String type;

    public SignUpOPAndEdu(String type){
        this.type = type;
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        String uname=fld_uname.getText();
        String pass=fld_pass.getText();

        btn_signupeduop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(User.getFetch(uname,pass,type)!=null){

                    FinalRegistration reg=new FinalRegistration();


                }



            }
        });
    }


}
