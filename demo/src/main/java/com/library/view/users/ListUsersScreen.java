
package com.library.view.users;

import com.library.controller.UserController;
import com.library.exceptions.UnableToDeleteUser;
import com.library.exceptions.UserNotFoundException;
import com.library.model.entities.User;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ListUsersScreen extends javax.swing.JInternalFrame {

    private UserController userController;
    private UserOptions userOptions;
    private javax.swing.JDesktopPane desktop;
    private List<User> users;

    
    public ListUsersScreen(UserController userController, UserOptions userOptions, javax.swing.JDesktopPane desktop) {
        this.userController = userController;
        this.userOptions = userOptions;
        this.desktop = desktop;
        this.users = userController.getUsers();
        initComponents();
        initTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuarios");
        setPreferredSize(new java.awt.Dimension(469, 469));

        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email", "Phone", "Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableUsers.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableUsers);
        if (tableUsers.getColumnModel().getColumnCount() > 0) {
            tableUsers.getColumnModel().getColumn(0).setResizable(false);
            tableUsers.getColumnModel().getColumn(1).setResizable(false);
            tableUsers.getColumnModel().getColumn(2).setResizable(false);
            tableUsers.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Selecione um Usuario :");

        btnSelect.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSelect.setText("SELECT");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSelect)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnSelect)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tableUsers.getModel();
        try {
            users.forEach((User user) -> {
            tableModel.addRow(new Object[] {user.getName(), 
                                            user.getEmail(),
                                            user.getPhone(),
                                            user.getAddress()});});
            tableUsers.setModel(tableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private int getRow() throws UserNotFoundException {
        int row = tableUsers.getSelectedRow();
 
        if (row < 0) {
            throw new IllegalArgumentException("Select a row.");
        }
        return row;
    }
    
    private void selectOption(User user, UserController userController, UserOptions options, javax.swing.JDesktopPane desktop) {
        if (options == UserOptions.UPDATE) {
            UpdateUser updateUser = new UpdateUser(user, userController, desktop);
            updateUser.setVisible(true);
            desktop.add(updateUser);
        } else {
            try {
                userController.deleteUser(user.getId());
                JOptionPane.showMessageDialog(this, "Usuario foi removido com sucesso!");
                this.dispose();
            } catch (UserNotFoundException | UnableToDeleteUser e) {
                JOptionPane.showMessageDialog(this, e.getMessage());

            }
        }
    }
    
    public void btnSelectActionPerformed() { 
        try {
            User user = userController.getUser(users.get(getRow()).getId());
            selectOption(user, userController, userOptions, desktop);
            this.dispose();
        } catch (UserNotFoundException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUsers;
    // End of variables declaration//GEN-END:variables
}
