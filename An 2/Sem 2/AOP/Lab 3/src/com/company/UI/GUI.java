package com.company.UI;

import com.company.Controller.Controller;
import com.company.Model.GraduateStudent;
import com.company.Model.PhDStudent;
import com.company.Model.Student;
import com.company.Model.UndergraduateStudent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class GUI extends JFrame {
    private Controller controller;

    private JPanel rightPanel = new JPanel();

    private JTable table = new JTable();
    private DefaultTableModel model;

    private JTextField idBox = new JTextField();
    private JTextField nameBox = new JTextField();
    private JTextField gradeBox = new JTextField();
    private JTextField supervisorBox = new JTextField();
    private JTextField thesisBox = new JTextField();
    private JTextField grade2Box = new JTextField();
    private JTextField grade3Box = new JTextField();

    private Logger logger = Logger.getLogger("Students");

    private JComboBox comboBox = new JComboBox();

    public GUI(Controller controller) {
        logger.info("[Entering:]GUI.init");
        this.controller = controller;
        this.setPreferredSize(new Dimension(600, 600));

        this.addList();

        this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.Y_AXIS));
        this.rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 390, 20, 20));
        this.rightPanel.setOpaque(false);

        this.addTextFields();
        this.addNewStudentButton();
        this.addComboBox();
        this.addReloadButton();

        this.getContentPane().add(this.rightPanel, BorderLayout.LINE_END);

        this.pack();
        this.setVisible(true);
    }

    private void addList() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 230));

        this.fillTable(null);

        this.table.setPreferredSize(new Dimension(350, 400));
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table.setVisible(true);

        ListSelectionModel listSelectionModel = this.table.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                Student selectedStudent = null;
                int selectedRow = GUI.this.table.getSelectedRow();
                if (selectedRow != -1) {
                    Integer id = (Integer)GUI.this.table.getModel().getValueAt(selectedRow, 0);
                    for (Student student : GUI.this.controller.allStudentObjects()) {
                        if (student.id == id) {
                            selectedStudent = student;
                            break;
                        }
                    }

                    if (selectedStudent != null) {
                        GUI.this.clearTextFields();
                        GUI.this.idBox.setText(String.valueOf(selectedStudent.id));
                        GUI.this.nameBox.setText(selectedStudent.name);
                        GUI.this.gradeBox.setText(String.valueOf(selectedStudent.grade));
                        if (selectedStudent instanceof UndergraduateStudent) {
                            UndergraduateStudent student = (UndergraduateStudent)selectedStudent;
                            GUI.this.grade2Box.setText(String.valueOf(student.grade2));
                        } else if (selectedStudent instanceof PhDStudent) {
                            PhDStudent student = (PhDStudent)selectedStudent;
                            GUI.this.supervisorBox.setText(student.supervisor);
                            GUI.this.thesisBox.setText(student.thesis);
                            GUI.this.grade2Box.setText(String.valueOf(student.grade2));
                        } else if (selectedStudent instanceof GraduateStudent) {
                            GraduateStudent student = (GraduateStudent)selectedStudent;
                            GUI.this.supervisorBox.setText(student.supervisor);
                            GUI.this.grade2Box.setText(String.valueOf(student.grade2));
                            GUI.this.grade3Box.setText(String.valueOf(student.grade3));
                        }
                    }
                }
            }
        });

        leftPanel.add(this.table);
        leftPanel.setOpaque(false);
        this.getContentPane().add(leftPanel, BorderLayout.LINE_START);
    }

    private void addTextFields() {
        JLabel idLabel = new JLabel();
        idLabel.setText("ID:");
        idBox.setPreferredSize(new Dimension(100, 20));
        idBox.setVisible(true);
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        idPanel.add(idLabel);
        idPanel.add(idBox);
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name:");
        nameBox.setPreferredSize(new Dimension(100, 20));
        nameBox.setVisible(true);
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        namePanel.add(nameLabel);
        namePanel.add(nameBox);
        JLabel gradeLabel = new JLabel();
        gradeLabel.setText("Grade:");
        gradeBox.setPreferredSize(new Dimension(100, 20));
        gradeBox.setVisible(true);
        JPanel gradePanel = new JPanel();
        gradePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        gradePanel.add(gradeLabel);
        gradePanel.add(gradeBox);
        JLabel supervisorLabel = new JLabel();
        supervisorLabel.setText("Supervisor:");
        supervisorBox.setPreferredSize(new Dimension(100, 20));
        supervisorBox.setVisible(true);
        JPanel supervisorPanel = new JPanel();
        supervisorPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        supervisorPanel.add(supervisorLabel);
        supervisorPanel.add(supervisorBox);
        JLabel thesisLabel = new JLabel();
        thesisLabel.setText("Thesis:");
        thesisBox.setPreferredSize(new Dimension(100, 20));
        thesisBox.setVisible(true);
        JPanel thesisPanel = new JPanel();
        thesisPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        thesisPanel.add(thesisLabel);
        thesisPanel.add(thesisBox);
        JLabel grade2Label = new JLabel();
        grade2Label.setText("Grade 2:");
        grade2Box.setPreferredSize(new Dimension(100, 20));
        grade2Box.setVisible(true);
        JPanel grade2Panel = new JPanel();
        grade2Panel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        grade2Panel.add(grade2Label);
        grade2Panel.add(grade2Box);
        JLabel grade3Label = new JLabel();
        grade3Label.setText("Grade 3:");
        grade3Box.setPreferredSize(new Dimension(100, 20));
        grade3Box.setVisible(true);
        JPanel grade3Panel = new JPanel();
        grade3Panel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        grade3Panel.add(grade3Label);
        grade3Panel.add(grade3Box);

        this.rightPanel.add(idPanel);
        this.rightPanel.add(namePanel);
        this.rightPanel.add(gradePanel);
        this.rightPanel.add(supervisorPanel);
        this.rightPanel.add(thesisPanel);
        this.rightPanel.add(grade2Panel);
        this.rightPanel.add(grade3Panel);
    }

    private void addNewStudentButton() {
        JButton newStudentBtn = new JButton();
        newStudentBtn.setText("Add Student");
        newStudentBtn.setPreferredSize(new Dimension(100, 40));
        newStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddStudentDialog addStudentDialog = new AddStudentDialog(controller);
                addStudentDialog.setVisible(true);
            }
        });

        this.rightPanel.add(newStudentBtn);
    }

    private void addComboBox() {
        comboBox.setPreferredSize(new Dimension(100, 20));
        comboBox.addItem("avg >= 5");
        comboBox.addItem("avg < 5");
        comboBox.addItem("avg = 10");

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<Student> filteredList = new ArrayList<Student>();

                if (comboBox.getSelectedItem().equals("avg >= 5")) {
                    for (Student student : GUI.this.controller.allStudentObjects()) {
                        if (student.average() >= 5) {
                            filteredList.add(student);
                        }
                    }
                } else if (comboBox.getSelectedItem().equals("avg < 5")) {
                    for (Student student : GUI.this.controller.allStudentObjects()) {
                        if (student.average() < 5) {
                            filteredList.add(student);
                        }
                    }
                } else if (comboBox.getSelectedItem().equals("avg = 10")) {
                    for (Student student : GUI.this.controller.allStudentObjects()) {
                        if (student.average() == 10) {
                            filteredList.add(student);
                        }
                    }
                }

                GUI.this.fillTable(filteredList);
            }
        });

        this.rightPanel.add(this.comboBox);
    }

    private void addReloadButton() {
        JButton reloadBtn = new JButton();
        reloadBtn.setPreferredSize(new Dimension(100, 40));
        reloadBtn.setText("Reload");
        reloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUI.this.fillTable(null);
            }
        });

        this.rightPanel.add(reloadBtn);
    }

    protected void fillTable(List<Student> list) {
        this.table.clearSelection();
        if (list == null) {
            list = this.controller.allStudentObjects();
        }

        this.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Vector vector = new Vector();
        for (Student student : list) {
            Vector row = new Vector();
            row.add(student.id);
            row.add(student.name);
            vector.add(row);
        }

        Vector columnNames = new Vector();
        columnNames.add("ID");
        columnNames.add("Name");

        this.model.setDataVector(vector, columnNames);

        this.table.setModel(this.model);
    }

    private void clearTextFields() {
        idBox.setText("");
        nameBox.setText("");
        gradeBox.setText("");
        supervisorBox.setText("");
        thesisBox.setText("");
        grade2Box.setText("");
        grade3Box.setText("");
    }
}
