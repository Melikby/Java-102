package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Quizsolve;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizSolve extends JFrame {
    private JPanel wrapper;
    private JList lst_Quiz_Solve;
    private JTextField txt_Quiz_solve;
    private JButton btn_Quiz_Solve;
    private DefaultListModel mdl_QuizSolve;
    private String title;

    public QuizSolve(String title) {
        this.title = title;
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        mdl_QuizSolve = new DefaultListModel<>();
        lst_Quiz_Solve.setModel(mdl_QuizSolve);

        btn_Quiz_Solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(txt_Quiz_solve)) {
                    Helper.showMsg("fill");
                }else{
                    String solve = txt_Quiz_solve.getText();
                    if (Quizsolve.add(title,solve)){
                        Helper.showMsg("done");
                        loadQuizSolveList();
                    }
                }
            }
        });
    }

    public void loadQuizSolveList(){
        mdl_QuizSolve.removeAllElements();
        for(String obj: Quizsolve.getList(title)){
            mdl_QuizSolve.addElement(obj);
        }
    }
}
