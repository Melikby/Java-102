package com.patikadev.View;

import com.patikadev.Helper.Helper;
import com.patikadev.Model.Content;


import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ContentGUI extends JFrame {
    private String course;
    private JPanel wrapper;
    private JTextField fld_search_title;
    private JTextField fld_search_course;
    private JButton btn_search;
    private JScrollPane scri_content;
    private JTable tbl_content;
    private JLabel lbl_titles;
    private JLabel lbl_courses;
    private JPanel pnl_addition;
    private JTextField fld_title;
    private JTextField fld_link;
    private JTextField fld_course;
    private JButton btn_add;
    private JTextField fld_id;
    private JButton btn_delete;
    private JLabel lbl_title;
    private JLabel lbl_explanation;
    private JLabel k;
    private JLabel lbl_course;
    private JTextField fld_explanation;
    private Object[] row_list;
    private DefaultTableModel mdl_content_list;

    public ContentGUI(String course) {
        this.course = course;
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Object[] column_list = {"id", "başlık", "Açıklama", "Link", "Course"};
        mdl_content_list = new DefaultTableModel();
        mdl_content_list.setColumnIdentifiers(column_list);

        row_list = new Object[column_list.length];
        loadModelContent();

        tbl_content.setModel(mdl_content_list);
        setVisible(true);

        JPopupMenu Quiz = new JPopupMenu();
        JMenuItem quizShow = new JMenuItem("Quiz Listesi");
        JMenuItem evaluatelist = new JMenuItem("Değerlendir ve Yorum Yap");
        Quiz.add(quizShow);
        Quiz.add(evaluatelist);

        tbl_content.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                String title = tbl_content.getModel().getValueAt(tbl_content.getSelectedRow(), 1).toString();
                QuizGUI quizGui = new QuizGUI(title);
            }
        });


        tbl_content.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int id = Integer.parseInt(tbl_content.getValueAt(tbl_content.getSelectedRow(), 0).toString());

                CommentAndEvaluateGUI comAndEv = new CommentAndEvaluateGUI(id);
            }
        });
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Helper.isFieldEmpty(fld_title) || Helper.isFieldEmpty(fld_explanation) ||
                        Helper.isFieldEmpty(fld_link) || Helper.isFieldEmpty(fld_course)) {

                    Helper.showMsg("fill");
                } else {
                    if (Content.add(fld_title.getText(), fld_explanation.getText(), fld_link.getText(), fld_course.getText())) {
                        Helper.showMsg("done");
                        loadModelContent();
                        fld_title.setText(null);
                        fld_explanation.setText(null);
                        fld_link.setText(null);
                        fld_course.setText(null);
                    }
                }
            }
        });
        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Helper.isFieldEmpty(fld_id)) {

                    Helper.showMsg("fill");
                } else {
                    if (Content.delete(Integer.parseInt(fld_id.getText()))) {
                        Helper.showMsg("done");
                        loadModelContent();
                        fld_id.setText(null);
                    }
                }
            }
        });

        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = fld_search_title.getText();
                String course = fld_search_course.getText();
                String query = Content.searchQuery(title, course);
                ArrayList<Content> contlist = Content.searchlist(query);
                loadSearchContModel(contlist);
            }
        });
        tbl_content.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                if (e.getType() == TableModelEvent.UPDATE) {
                    int id = Integer.parseInt(tbl_content.getValueAt(tbl_content.getSelectedRow(), 0).toString());
                    String title = tbl_content.getValueAt(tbl_content.getSelectedRow(), 1).toString();
                    String Explanation = tbl_content.getValueAt(tbl_content.getSelectedRow(), 2).toString();
                    String link = tbl_content.getValueAt(tbl_content.getSelectedRow(), 3).toString();
                    String Course = tbl_content.getValueAt(tbl_content.getSelectedRow(), 4).toString();
                    if (Content.Update(id, title, Explanation, link, course)) {
                        Helper.showMsg("done");
                    }
                    loadModelContent();
                }
            }
        });
    }

    public void loadModelContent() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content.getModel();
        clearModel.setRowCount(0);

        int i;
        for (Content obj : Content.getList(this.course)) {
            i = 0;
            row_list[i++] = obj.getId();
            row_list[i++] = obj.getTitle();
            row_list[i++] = obj.getExplanation();
            row_list[i++] = obj.getLink();
            row_list[i++] = obj.getCourse();
            mdl_content_list.addRow(row_list);
        }
    }

    public void loadSearchContModel(ArrayList<Content> cont) {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_content.getModel();
        clearModel.setRowCount(0);

        int i;
        for (Content content : cont) {
            i = 0;
            row_list[i++] = content.getId();
            row_list[i++] = content.getTitle();
            row_list[i++] = content.getExplanation();
            row_list[i++] = content.getLink();
            row_list[i++] = content.getCourse();
            mdl_content_list.addRow(row_list);
        }
    }
    public static void main(String[] args) {
        ContentGUI c=new ContentGUI("Java101");
    }
}

