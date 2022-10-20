package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {

    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_user_list;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton çıkışYapButton;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_ıd;
    private JButton btn_user_delete;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_uname;
    private JComboBox cmb_sh_user_type;
    private JButton btn_user_sh;
    private JPanel pnl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_course_from;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;
    private JPanel pnl_content;
    private JPanel pnl_deletion;
    private JTextField fld_content_deletion;
    private JButton btn_content_deletion;
    private JLabel lbl_content_deletion;
    private JScrollPane scrl_content;
    private JTable tbl_content;
    private JPanel pnl_quiz;
    private JTextField fld_quiz_delete;
    private JButton btn_quiz_deletion;
    private JLabel lbl_quiz_id;
    private JScrollPane scrl_quiz;
    private JTable tbl_quiz;
    private JPanel pnl_user_top;
    private JScrollPane scrl_patika_list;
    private JButton btn_patika_deletion;
    private JTextField fld_deletion_id;
    private JButton btn_course_delete;
    private JLabel lbl_course_id;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    private JPopupMenu courseMenu;
    private DefaultTableModel  mdl_content_list;
    private Object[] row_list;
    private DefaultTableModel  mdl_Quiz;
    private Object[] roww_list;
    private Operator operator;


    public OperatorGUI(Operator operator) {
        this.operator = operator;

        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldiniz : " + operator.getName());

        //ModelUserList
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        row_user_list = new Object[col_user_list.length];
        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_user_ıd.setText(select_user_id);
            } catch (Exception exception) {
            }
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
                String user_uname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
                String user_pass = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
                String user_type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString();
                if (User.update(user_id, user_name, user_uname, user_pass, user_type)) {
                    Helper.showMsg("done");
                }
                loadUserModel();
                loadEcuatorCombo();
                loadCourseModel();
            }
        });
        // ## UserList

        //PatikaList
        patikaMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadUserModel();
                    loadPatikaCombo();
                    loadCourseModel();
                }
            });
        });
        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
                if (Patika.delete(select_id)) {
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadUserModel();
                    loadCourseModel();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        DefaultTableModel mdl_table_Quiz=new DefaultTableModel(){

            @Override

            public boolean isCellEditable(int row, int column) {
                if(column==0){
                    return  false;
                }
                return super.isCellEditable(row, column);
            }
        };
        //
        tbl_quiz.getTableHeader().setReorderingAllowed(false);
        Object[] columnList={"id","title","Quiz"};
        mdl_Quiz.setColumnIdentifiers(columnList);
        Object[] rowwList=new Object[columnList.length];
        loadQuizModel();
        tbl_quiz.setModel(mdl_Quiz);

//

        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID", "Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();
        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_patika_list.getColumnModel().getColumn(0).setMinWidth(75);
//
        Object[]column_list={"id","başlık","Açıklama","Link","Course"};
        mdl_content_list=new DefaultTableModel();
        mdl_content_list.setColumnIdentifiers(column_list);
        row_list=new Object[column_list.length];
        loadModelContent();
        tbl_content.setModel(mdl_content_list);

        tbl_content.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType()==TableModelEvent.UPDATE){

                    int id=Integer.parseInt(tbl_content.getValueAt(tbl_content.getSelectedRow(),0).toString());
                    String title=tbl_content.getValueAt(tbl_content.getSelectedRow(),1).toString();
                    String Explanation=tbl_content.getValueAt(tbl_content.getSelectedRow(),2).toString();
                    String link=tbl_content.getValueAt(tbl_content.getSelectedRow(),3).toString();
                    String course=tbl_content.getValueAt(tbl_content.getSelectedRow(),4).toString();

                    if(Content.Update(id,title,Explanation,link,course)){
                        Helper.showMsg("fill");
                        loadModelContent();
                    }
                }
            }
        });
//
        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
        // ## PatikaList

        // CourseList
        mdl_course_list = new DefaultTableModel();
        Object[] col_courseList = {"ID", "Ders Adı", "Programlama Dili", "Patika", "Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_courseList);
        row_course_list = new Object[col_courseList.length];
        tbl_course_list.setModel(mdl_course_list);
        loadCourseModel();
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadEcuatorCombo();
        loadPatikaCombo();
        // ##CourseList

//---------
        tbl_course_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType()== TableModelEvent.UPDATE){

                    int id=Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString());
                    int user_id=Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),1).toString());
                    int patika_id=Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),2).toString());
                    String name=tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),3).toString();
                    String lang=tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),4).toString();

                    if(Course.update(id,user_id,patika_id,name,lang)){

                        Helper.showMsg("done");
                    }

                    loadCourseModel();
                    loadPatikaModel();
                    loadUserModel();

                }
            }
        });





        //-------------------COUSE----------------------



        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {


            try{
                String select_user_id=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString();
                fld_user_ıd.setText(select_user_id);
            }

            catch (Exception exception){

                exception.getMessage();
            }


        });
        //---------

        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)) {
                Helper.showMsg("fill");
            } else {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name, uname, pass, type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    loadEcuatorCombo();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                }
            }
        });
//------
        tbl_quiz.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                if(e.getType()==TableModelEvent.UPDATE){

                    int id=Integer.parseInt(tbl_quiz.getValueAt(tbl_quiz.getSelectedRow(),0).toString());
                    String title=tbl_quiz.getValueAt(tbl_quiz.getSelectedRow(),1).toString();
                    String quiz=tbl_quiz.getValueAt(tbl_quiz.getSelectedRow(),2).toString();

                    if(Quiz.Update(id,title,quiz)){

                        Helper.showMsg("done");
                    }
                }
            }
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if(e.getType()==TableModelEvent.UPDATE){

                int user_id= Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                String user_name= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
                String user_uname= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
                String user_pass= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
                String user_type= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();


                if(User.update(user_id, user_name, user_uname, user_pass, user_type)){

                    Helper.showMsg("done");

                }

                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }
        });

        //-------
        btn_user_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_ıd)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int id = Integer.parseInt(fld_user_ıd.getText());
                    if (User.delete(id)) {
                        Helper.showMsg("done");
                        loadUserModel();
                        loadEcuatorCombo();
                        loadCourseModel();
                        fld_user_ıd.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }

                }
            }
        });
        btn_user_sh.addActionListener(e -> {
            String name = fld_sh_user_name.getText();
            String uname = fld_sh_user_uname.getText();
            String type = cmb_sh_user_type.getSelectedItem().toString();
            String query = User.searchQuery(name, uname, type);
            loadUserModel(User.searchUserList(query));
        });
        çıkışYapButton.addActionListener(e -> {
            dispose();
            LoginGUI login=new LoginGUI();
        });

        btn_patika_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_patika_name)) {
                Helper.showMsg("fill");
            } else {
                if (Patika.add(fld_patika_name.getText())) {
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadUserModel();
                    loadPatikaCombo();
                    fld_patika_name.setText(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        btn_course_add.addActionListener(e -> {
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();

            if (Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)) {
                Helper.showMsg("fill");
            } else {
                if (Course.add(userItem.getKey(), patikaItem.getKey(), fld_course_name.getText(), fld_course_lang.getText())) {
                    Helper.showMsg("done");
                    loadCourseModel();
                    fld_course_lang.setText(null);
                    fld_course_name.setText(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        btn_patika_deletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name =fld_patika_name.getText();
                Patika.delete(0);
            }
        });
        btn_course_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(fld_deletion_id.getText());

                Course.delete(id);
            }
        });
        btn_content_deletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(fld_content_deletion.getText());
                Content.delete(id);
            }
        });
        btn_quiz_deletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(fld_quiz_delete.getText());

                if (Quiz.delete(id)) {

                    Helper.showMsg("done");
                }
            }



            //--------


        });
    }
    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Course obj : Course.getList()) {
            i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLang();
            row_course_list[i++] = obj.getPatika().getName();
            row_course_list[i++] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }

    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Patika obj : Patika.getList()) {
            i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }


    private void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (User obj : User.getList()) {
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    private void loadUserModel(ArrayList<User> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for (User obj : list) {
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    private void loadPatikaCombo() {

        cmb_course_patika.removeAllItems();
        for (Patika obj : Patika.getList()) {
            cmb_course_patika.addItem(new Item(obj.getId(), obj.getName()));

        }
    }

    private void loadEcuatorCombo() {
        cmb_course_user.removeAllItems();

        for (User obj : User.getListOnlyEducator()) {
            cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));

        }
    }

    //--------
    private void loadModelContent() {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_content.getModel();
        clearModel.setRowCount(0);

        int i;
        for (Content obj : Content.getListCont()) {
            i = 0;
            row_list[i++] = obj.getId();
            row_list[i++] = obj.getTitle();
            row_list[i++] = obj.getExplanation();
            row_list[i++] = obj.getLink();
            row_list[i++] = obj.getCourse();
            mdl_content_list.addRow(row_list);


        }
    }
    private void loadEducatorCombo() {

        cmb_course_user.removeAllItems();

        for(User obj:User.getList()){

            if(obj.getType().equals("educator")){

                cmb_course_user.addItem(new Item(obj.getId(),obj.getName()));
            }
        }


    }

    private void loadQuizModel() {

        DefaultTableModel clearTable = (DefaultTableModel) tbl_quiz.getModel();
        clearTable.setRowCount(0);


        int i;

        for (Quiz obj : Quiz.getList()) {
            i = 0;
            roww_list[i++] = obj.getId();
            roww_list[i++] = obj.getTitle();
            roww_list[i++] = obj.getQuiz();
            mdl_Quiz.addRow(roww_list);


        }


    } public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("1");
        op.setUname("2");
        op.setPass("3");
        op.setType("4");

        OperatorGUI opGUI = new OperatorGUI(op);
    }
}
