package com.company.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.company.Controller.Controller;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class AddStudentDialog extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller controller;

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JTextField idBox = new JTextField();
    private JTextField nameBox = new JTextField();
    private JTextField gradeBox = new JTextField();
    private JTextField supervisorBox = new JTextField();
    private JTextField thesisBox = new JTextField();
    private JTextField grade2Box = new JTextField();
    private JTextField grade3Box = new JTextField();

    private JComboBox comboBox = new JComboBox();

    private Logger logger = Logger.getLogger("Students");

    public AddStudentDialog(Controller controller) {
        logger.info("[Entering:]GUI.AddStudentDialog");
        this.controller = controller;

        this.setPreferredSize(new Dimension(400, 400));

        this.leftPanel.setLayout(new BoxLayout(this.leftPanel, BoxLayout.Y_AXIS));
        this.leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 100));
        this.leftPanel.setOpaque(false);

        this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.Y_AXIS));
        this.rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 320, 20));
        this.rightPanel.setOpaque(false);

        this.addTextFields();
        this.addComboBox();
        this.addButton();

        this.getContentPane().add(this.leftPanel, BorderLayout.LINE_START);
        this.getContentPane().add(this.rightPanel, BorderLayout.LINE_END);

        this.pack();
        this.setVisible(true);
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

        this.leftPanel.add(idPanel);
        this.leftPanel.add(namePanel);
        this.leftPanel.add(gradePanel);
        this.leftPanel.add(supervisorPanel);
        this.leftPanel.add(thesisPanel);
        this.leftPanel.add(grade2Panel);
        this.leftPanel.add(grade3Panel);
    }

    private void addComboBox() {
        comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(100, 40));
        comboBox.addItem("Student");
        comboBox.addItem("PhD");
        comboBox.addItem("Undergraduate");
        comboBox.addItem("Graduate");

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (comboBox.getSelectedItem().equals("Student")) {
                    AddStudentDialog.this.supervisorBox.setEnabled(false);
                    AddStudentDialog.this.thesisBox.setEnabled(false);
                    AddStudentDialog.this.grade2Box.setEnabled(false);
                    AddStudentDialog.this.grade3Box.setEnabled(false);
                } else if (comboBox.getSelectedItem().equals("PhD")) {
                    AddStudentDialog.this.supervisorBox.setEnabled(true);
                    AddStudentDialog.this.thesisBox.setEnabled(true);
                    AddStudentDialog.this.grade2Box.setEnabled(true);
                    AddStudentDialog.this.grade3Box.setEnabled(false);
                } else if (comboBox.getSelectedItem().equals("Undergraduate")) {
                    AddStudentDialog.this.supervisorBox.setEnabled(false);
                    AddStudentDialog.this.thesisBox.setEnabled(false);
                    AddStudentDialog.this.grade2Box.setEnabled(true);
                    AddStudentDialog.this.grade3Box.setEnabled(false);
                } else if (comboBox.getSelectedItem().equals("Graduate")) {
                    AddStudentDialog.this.supervisorBox.setEnabled(true);
                    AddStudentDialog.this.thesisBox.setEnabled(false);
                    AddStudentDialog.this.grade2Box.setEnabled(true);
                    AddStudentDialog.this.grade3Box.setEnabled(true);
                }
            }
        });

        this.rightPanel.add(comboBox);
    }

    private void addButton() {
        JButton addStudentBtn = new JButton();
        addStudentBtn.setText("Add Student");
        addStudentBtn.setPreferredSize(new Dimension(100, 40));

        addStudentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ArrayList<String> errors = null;
                    if (AddStudentDialog.this.comboBox.getSelectedItem().equals("Student")) {
                        errors = AddStudentDialog.this.controller.addStudent(Integer.parseInt(AddStudentDialog.this.idBox.getText()), AddStudentDialog.this.nameBox.getText(), Integer.parseInt(AddStudentDialog.this.gradeBox.getText()));
                    } else if (AddStudentDialog.this.comboBox.getSelectedItem().equals("PhD")) {
                        errors = AddStudentDialog.this.controller.addStudent(Integer.parseInt(AddStudentDialog.this.idBox.getText()), AddStudentDialog.this.nameBox.getText(), Integer.parseInt(AddStudentDialog.this.gradeBox.getText()), Integer.parseInt(AddStudentDialog.this.grade2Box.getText()), AddStudentDialog.this.supervisorBox.getText(), AddStudentDialog.this.thesisBox.getText());
                    } else if (AddStudentDialog.this.comboBox.getSelectedItem().equals("Undergraduate")) {
                        errors = AddStudentDialog.this.controller.addStudent(Integer.parseInt(AddStudentDialog.this.idBox.getText()), AddStudentDialog.this.nameBox.getText(), Integer.parseInt(AddStudentDialog.this.gradeBox.getText()), Integer.parseInt(AddStudentDialog.this.grade2Box.getText()));
                    } else if (AddStudentDialog.this.comboBox.getSelectedItem().equals("Graduate")) {
                        errors = AddStudentDialog.this.controller.addStudent(Integer.parseInt(AddStudentDialog.this.idBox.getText()), AddStudentDialog.this.nameBox.getText(), Integer.parseInt(AddStudentDialog.this.gradeBox.getText()), Integer.parseInt(AddStudentDialog.this.grade2Box.getText()), Integer.parseInt(AddStudentDialog.this.grade3Box.getText()), AddStudentDialog.this.supervisorBox.getText());
                    }

                    if (errors.size() == 0) {
                        AddStudentDialog.this.dispose();
                    } else {
                        JFrame frame = new JFrame();
                        frame.setPreferredSize(new Dimension(100, 100));
                        JOptionPane.showMessageDialog(frame, errors, "Add Student Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JFrame frame = new JFrame();
                    frame.setPreferredSize(new Dimension(100,100));
                    JOptionPane.showMessageDialog(frame, "ID and Grade fields must be integers!", "Add Student Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.rightPanel.add(addStudentBtn);
    }

}
