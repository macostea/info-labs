package view;

import com.sun.istack.internal.logging.Logger;
import controller.Controller;
import model.Participant;
import model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class UserForm implements Observer {
    private Controller controller;
    private JLabel checkpointNumber;
    private JTable table1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton markParticipantButton;
    private JTable table2;
    private ArrayList<Participant> participants;
    private Participant selectedParticipant;
    private User currentUser;

    public UserForm(Controller controller) {
        this.controller = controller;
        this.controller.addObserver(this);

        JFrame frame = new JFrame("Checkpoint");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        this.controller.getParticipants();
        markParticipantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedParticipant.getLastCheckpoint() == currentUser.getCheckpoint() - 1) {
                    selectedParticipant.setLastCheckpoint(currentUser.getCheckpoint());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        java.util.Date date = dateFormat.parse(textField1.getText());
                        selectedParticipant.setTimeReached(new Time(date.getTime()));
                        controller.updateParticipant(selectedParticipant);
                    } catch (ParseException ex) {
                        Logger.getLogger(UserForm.class).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        this.addTableComponents((ArrayList<Participant>) arg);
    }

    private void createUIComponents() {
        this.table1 = new JTable(new DefaultTableModel(new Object[]{"Name", "Last checkpoint", "Time reached"}, 0));
        this.table1.setShowGrid(true);
        this.table1.setAutoCreateColumnsFromModel(true);
        this.table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table1.getSelectedRow() >= 0 && table1.getSelectedRow() < participants.size()) {
                    Participant currentParticipant = participants.get(table1.getSelectedRow());
                    selectedParticipant = currentParticipant;
                }
            }
        });

        this.table2 = new JTable(new DefaultTableModel(new Object[]{"Name", "Total time"}, 0));
        this.table2.setShowGrid(true);
        this.table2.setAutoCreateColumnsFromModel(true);
        this.table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void addTableComponents(ArrayList<Participant> participants) {
        this.table1.removeAll();
        this.table1.clearSelection();
        this.participants = participants;

        DefaultTableModel model = (DefaultTableModel)this.table1.getModel();
        model.setRowCount(0);

        for (Participant p : participants) {
            model.addRow(new Object[]{p.getName(), p.getLastCheckpoint(), p.getTimeReached()});
        }

        this.table2.removeAll();
        this.table2.clearSelection();

        DefaultTableModel table2Model = (DefaultTableModel)this.table2.getModel();
        table2Model.setRowCount(0);

        for (Participant p : participants) {
            table2Model.addRow(new Object[]{p.getName(), new Time(p.getTimeReached().getTime() - p.getStartingTime().getTime())});
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        this.checkpointNumber.setText(String.valueOf(currentUser.getCheckpoint()));
    }
}
