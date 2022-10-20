package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalRegistration extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_reg;
    private JLabel pnl_uname;
    private JTextField fld_uname;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel fld_name;
    private JLabel fld_pass;
    private JLabel fld_type;
    private JButton btn_reg;

    public FinalRegistration(){
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        String name=fld_name.getText();
        String uname=fld_uname.getText();
        String pass= fld_pass.getText();
        String type=fld_pass.getText();

        btn_reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(User.add(name,uname,pass,type)){

                    Helper.showMsg("done");
                }
            }
        });
    }
}
