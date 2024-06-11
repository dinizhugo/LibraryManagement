package com.library.view.devolution;

import com.library.controller.DevolutionController;
import com.library.model.entities.Devolution;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class GetDevolutionsScreen extends javax.swing.JFrame {
     private final DevolutionController devolutionController;
     private final List<Devolution> devolutions;
   
    public GetDevolutionsScreen(DevolutionController devolutionController) {
        this.devolutionController = devolutionController;
        this.devolutions = devolutionController.getDevolutions();
        initComponents();
        iniTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableDevolution = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Devoluções");

        tableDevolution.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User (name)", "Book (Title)", "Loan Date", "Estimated Date", "Returned Date", "Traffic Ticket"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDevolution.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableDevolution);
        if (tableDevolution.getColumnModel().getColumnCount() > 0) {
            tableDevolution.getColumnModel().getColumn(0).setResizable(false);
            tableDevolution.getColumnModel().getColumn(1).setResizable(false);
            tableDevolution.getColumnModel().getColumn(2).setResizable(false);
            tableDevolution.getColumnModel().getColumn(3).setResizable(false);
            tableDevolution.getColumnModel().getColumn(4).setResizable(false);
            tableDevolution.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/return_5597043.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

 
    private void iniTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tableDevolution.getModel();
        if (!devolutions.isEmpty()) {
            try {
                devolutions.forEach((Devolution devolution) -> {
                tableModel.addRow(new Object[] {devolution.getLoan().getUser().getName(), 
                                                devolution.getLoan().getBook().getTitle(),
                                                devolution.getLoan().getLoanDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                                devolution.getLoan().getEstimatedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                                devolution.getReturnDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                                devolution.getTrafficTicket()});});
                tableDevolution.setModel(tableModel);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lista de devoluções vazia.");
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GetDevolutionsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetDevolutionsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetDevolutionsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetDevolutionsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetDevolutionsScreen().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDevolution;
    // End of variables declaration//GEN-END:variables
}
