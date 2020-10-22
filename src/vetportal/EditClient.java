/**
 * File: EditClient.java
 * Date: April 18, 2020
 * @Author: Nour Debiat, Rebekah Qu
 * Purpose: This window allows the user to edit existing clients.
 */
package vetportal;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class EditClient extends JFrame {

    //Creating VetPortal object:
    VetPortal vetPortal;

    /**
    * Creates new form EditClient
    * @param vetPortal The instance of the Vet Portal
    * @param currentFirstName The current first name of the client
    * @param currentLastName The current last name of the client
    * @param currentEmail The current email of the client
    * @param currentPhoneNumber The current phone number of the client
    * @throws ParseException
    */
    public EditClient(VetPortal vetPortal, String currentFirstName, String currentLastName, String currentEmail, String currentPhoneNumber) throws ParseException {
        super("Edit Client Form");
        this.vetPortal = vetPortal;
        String userID = currentPhoneNumber;
        // Pass the current client information to the form
        initComponents(currentFirstName, currentLastName, currentEmail, currentPhoneNumber, userID);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(String currentFirstName, String currentLastName, String currentEmail, String currentPhoneNumber, String userID) throws ParseException {

        editClientPanel = new JPanel();
        cancelBtn = new JButton();
        submitBtn = new JButton();
        editClientLabel = new JLabel();
        firstNameLabel = new JLabel();
        lastNameLabel = new JLabel();
        emailLabel = new JLabel();
        phoneLabel = new JLabel();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        MaskFormatter phoneFormat = new MaskFormatter("(###) ###-####");
        phoneField = new JFormattedTextField(phoneFormat);
        cWarningMsg = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        editClientPanel.setBackground(new Color(255, 255, 255));

        cancelBtn.setBackground(new Color(255, 255, 255));
        cancelBtn.setFont(new Font("Calibri", 1, 18)); // NOI18N
	cancelBtn.setForeground(new Color(122, 188, 255));
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(event -> cancel());        

        submitBtn.setBackground(new Color(255, 255, 255));
        submitBtn.setFont(new Font("Calibri", 1, 18)); // NOI18N   
	submitBtn.setForeground(new Color(122, 188, 255));		
        submitBtn.setText("Submit");
        submitBtn.addActionListener(event -> submit(userID));
        
        editClientLabel.setBackground(new Color(255, 255, 255));
        editClientLabel.setFont(new Font("Calibri", 0, 20)); // NOI18N
        editClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editClientLabel.setText("<html>Edit the form below to edit the selected client <br>or cancel to return to the Clients Dashboard</html>");

        firstNameLabel.setFont(new Font("Calibri", 0, 18)); // NOI18N
        firstNameLabel.setText("First Name");

        lastNameLabel.setFont(new Font("Calibri", 0, 18)); // NOI18N
        lastNameLabel.setText("Last Name");

        emailLabel.setFont(new Font("Calibri", 0, 18)); // NOI18N
        emailLabel.setText("Email");

        phoneLabel.setFont(new Font("Calibri", 0, 18)); // NOI18N
        phoneLabel.setText("Phone Number");

        firstNameField.setFont(new Font("Calibri", 0, 18)); // NOI18N
        firstNameField.setText(currentFirstName);

        lastNameField.setFont(new Font("Calibri", 0, 18)); // NOI18N
        lastNameField.setText(currentLastName);

        emailField.setFont(new Font("Calibri", 0, 18)); // NOI18N
        emailField.setText(currentEmail);

        phoneField.setFont(new Font("Calibri", 0, 18)); // NOI18N
        phoneField.setText(currentPhoneNumber);

        cWarningMsg.setFont(new Font("Calibri", 0, 14)); // NOI18N
        cWarningMsg.setForeground(new Color(203, 0, 0));
        cWarningMsg.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout editClientPanelLayout = new GroupLayout(editClientPanel);
        editClientPanel.setLayout(editClientPanelLayout);
        editClientPanelLayout.setHorizontalGroup(
            editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(editClientPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lastNameLabel)
                    .addComponent(firstNameLabel)
                    .addComponent(emailLabel)
                    .addComponent(phoneLabel))
                .addGap(18, 18, 18)
                .addGroup(editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(firstNameField, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(lastNameField)
                    .addComponent(emailField)
                    .addComponent(phoneField, GroupLayout.Alignment.TRAILING))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, editClientPanelLayout.createSequentialGroup()
                .addGap(0, 36, Short.MAX_VALUE)
                .addComponent(editClientLabel, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(editClientPanelLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(submitBtn)
                .addGap(110, 110, 110)
                .addComponent(cancelBtn)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, editClientPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cWarningMsg, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editClientPanelLayout.setVerticalGroup(
            editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(editClientPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(editClientLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel))
                .addGap(18, 18, 18)
                .addGroup(editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cWarningMsg, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(editClientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(editClientPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(editClientPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Event for Submit button click
    private void submit(String userID) {
        // Attempt to edit the client using the original phone number as the identifier
        editSelectedClient(userID);
    }

    // Event for Cancel button click
    private void cancel() {
	// Close the Add Client window and do nothing
        dispose();
    }

    private void editSelectedClient(String userID) {
        Boolean editTF = vetPortal.editClient(cWarningMsg, userID, firstNameField.getText(), lastNameField.getText(), phoneField.getText(), emailField.getText());

        // If edit was successful
        if(editTF) {
            // If success close the form
            dispose();
        } 
        // If edit was not successful, cWarningMsg will convey any errors to the user

        // Refresh the Clients Table in the Dashboard
        DashboardsGui dashboard = vetPortal.getDashboard();
        DashboardsGui.MyClientTableModel clientModel = (DashboardsGui.MyClientTableModel) dashboard.getClientTable().getModel();
        DashboardsGui.MyPetTableModel petModel = (DashboardsGui.MyPetTableModel) dashboard.getPetTable().getModel();
        clientModel.refetchClients(); 
        petModel.refetchPets();
    } //end of editSelectedClient()

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel editClientLabel;
    private JPanel editClientPanel;
    private JLabel cWarningMsg;
    private JButton cancelBtn;
    private JTextField emailField;
    private JLabel emailLabel;
    private JTextField firstNameField;
    private JLabel firstNameLabel;
    private JTextField lastNameField;
    private JLabel lastNameLabel;
    private JTextField phoneField;
    private JLabel phoneLabel;
    private JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
