package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Patika;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JScrollPane scrl_student;
    private JTable tbl_pat_stud;
    private JLabel lbl_student;
    private User user;
    private DefaultTableModel mdl_student;
    private Object[] rowList;

    public StudentGUI(User user) {
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        lbl_student.setText("Öğrenci ekranına Hoşgeldiniz " + user.getName());
        mdl_student = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] columnList = {"id", "Patika Listesi"};
        rowList = new Object[columnList.length];
        mdl_student.setColumnIdentifiers(columnList);
        loadModelList();
        tbl_pat_stud.setModel(mdl_student);

        JPopupMenu courseMenu = new JPopupMenu();
        JMenuItem courseList = new JMenuItem("Ders Listesi");
        courseMenu.add(courseList);

        courseList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patika = tbl_pat_stud.getValueAt(tbl_pat_stud.getSelectedRow(), 1).toString();
                Patika ptk = null;
                for (Patika obj : Patika.getList()) {
                    if (obj.getName().equals(patika)) {
                        ptk.setName(obj.getName());
                        ptk.setId(obj.getId());
                        break;
                    }
                }

                CourseGUI courseGUI = new CourseGUI(ptk);
            }
        });
    }

    public void loadModelList() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_pat_stud.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Patika obj : Patika.getList()) {
            i = 0;
            rowList[i++] = obj.getId();
            rowList[i++] = obj.getName();
            mdl_student.addRow(rowList);
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        User user=new User();
        user.setName("Melik Boyu");
        user.setId(2);
        user.setPass("1");
        user.setUname("u");
        user.setType("educator");

        StudentGUI st=new StudentGUI(user);
    }
}

