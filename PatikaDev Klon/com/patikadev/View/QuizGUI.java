package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Quiz;
import com.patikadev.Model.Quizsolve;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_quiz_title;
    private JScrollPane scrl_quiz;
    private JTable tbl_quiz_list;
    private DefaultTableModel mdl_table_Quiz;
    private Object[] rowList;
    private String title;


    public QuizGUI(String title) {
        this.title = title;
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        lbl_quiz_title.setText("Quiz ekranı");
        JPopupMenu solution = new JPopupMenu();
        JMenuItem solve = new JMenuItem("çöz");
        solution.add(solve);

        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),1).toString();
                        QuizSolve quizSolve= new QuizSolve(title);
            }
        });

        DefaultTableModel mdl_table_Quiz=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                if(column==0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        tbl_quiz_list.getTableHeader().setReorderingAllowed(false);
        Object[] columnList={"id","title","Quiz"};
        Object[] rowList=new Object[columnList.length];
        mdl_table_Quiz.setColumnIdentifiers(columnList);
        loadModelList();
        tbl_quiz_list.setModel(mdl_table_Quiz);
    }
public void loadModelList(){

        DefaultTableModel clearTable=(DefaultTableModel) tbl_quiz_list.getModel();
        clearTable.setRowCount(0);

        int i;
        for (Quiz obj:Quiz.getListByTitle(title)){
            i=0;
            rowList[i++]=obj.getId();
            rowList[i++]=obj.getTitle();
            rowList[i++]=obj.getQuiz();
            mdl_table_Quiz.addRow(rowList);
        }
}




}
