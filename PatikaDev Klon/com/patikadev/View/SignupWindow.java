package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SignupWindow extends JFrame{
    private JPanel wrapper;
    private JTextField fld_UserName;
    private JTextField fld_pass;
    private JTextField fld_name;
    private JTextField fld_type;
    private JButton btn_registration;
    private JLabel lbl_userName;
    private JLabel lbl_pass;
    private JLabel lbl_name;
    private JLabel lbl_type;

    public SignupWindow(){
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String uname=fld_UserName.getText();
                String pass=fld_pass.getText();
                String name=fld_name.getText();
                String type=fld_type.getText();
                if(fld_type.getText().equals("student")) {

                    if (User.add(name, uname, pass, type)) {
                        Helper.showMsg("done");
                    }

                }

                else if(fld_type.getText().equals("educator")){


                    Helper.showMsg("Eğitmenleri sadece operatörler ekleyebilir");

                    addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                            if(Helper.signOp()){
                                SignUpOPAndEdu op=new SignUpOPAndEdu("educator");
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {


                        }
                    });





                }

                else if(fld_type.getText().equals("operator")){

                    Helper.showMsg("Operatorler sadece yazılımcı tarafından eklenebilir");

                    addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                            if(Helper.signOp()){
                                SignUpOPAndEdu op=new SignUpOPAndEdu("developer");
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {



                        }
                    });


                }

            }

        });

    }
}
