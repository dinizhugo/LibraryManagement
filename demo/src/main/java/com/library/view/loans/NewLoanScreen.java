/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.library.view.loans;
    
import com.library.controller.BookController;
import com.library.controller.LoanController;
import com.library.controller.UserController;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.InsufficientBooksExceptions;
import com.library.exceptions.UninformedParameterException;
import com.library.exceptions.UserNotFoundException;
import com.library.model.entities.Book;
import com.library.model.entities.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NewLoanScreen extends javax.swing.JInternalFrame {

    private final LoanController loanController;
    private final UserController userController;
    private BookController bookController = new BookController();
    private final List<User> users;
    private final List<Book> books;
    
    public NewLoanScreen(LoanController loanController, UserController userController, BookController bookController) {
        this.loanController = loanController;
        this.userController = userController;
        this.bookController = bookController;
        this.users = userController.getUsers();
        this.books = this.bookController.getBooks();
        initComponents();
        initTableUsers();
        initTableBooks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBooks = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtReturnedDate = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Criar novo emprestimo");
        setPreferredSize(new java.awt.Dimension(469, 469));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loan_12692352.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Selecione um Usuario:");

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
        tableUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableUsers.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableUsers);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Selecione um Livro:");

        tableBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Author", "Publishing Company", "Category", "Publication Date", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableBooks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableBooks.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableBooks);
        if (tableBooks.getColumnModel().getColumnCount() > 0) {
            tableBooks.getColumnModel().getColumn(0).setResizable(false);
            tableBooks.getColumnModel().getColumn(1).setResizable(false);
            tableBooks.getColumnModel().getColumn(2).setResizable(false);
            tableBooks.getColumnModel().getColumn(3).setResizable(false);
            tableBooks.getColumnModel().getColumn(4).setResizable(false);
            tableBooks.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Data de devolu��o:");

        txtReturnedDate.setToolTipText("(Example: dd/MM/yyyy)");

        btnCreate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCreate.setText("CREATE");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtReturnedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtReturnedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int getRow(javax.swing.JTable table) {
        int row = table.getSelectedRow();
 
        if (row < 0) {
            throw new IllegalArgumentException("Select a row.");
        }
        return row;
    }
    
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        String txtReturnedDate = this.txtReturnedDate.getText();
        LocalDate currentDate = LocalDate.now();
        try {
            User user = userController.getUser(users.get(getRow(tableUsers)).getId());
            Book book = bookController.getBook(books.get(getRow(tableBooks)).getId());
            
            if (user == null || book == null || txtReturnedDate.isEmpty()) {
                loanController.createNewLoan(null, null, null, null);
            } else {
                LocalDate returnedDate = LocalDate.parse(txtReturnedDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (returnedDate.isBefore(currentDate)) {
                    JOptionPane.showMessageDialog(this, "A data de devolu��o n�o pode ser inferior a data atual.");
                } else {
                    loanController.createNewLoan(user, book, currentDate, returnedDate);
                    JOptionPane.showMessageDialog(this, "Novo emprestimo feito com sucesso!");
                    this.dispose();
                }
            }
        } catch (BookNotFoundException | InsufficientBooksExceptions | UninformedParameterException | UserNotFoundException | DateTimeParseException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_btnCreateActionPerformed

    private void initTableUsers() {
        DefaultTableModel tableModel = (DefaultTableModel) tableUsers.getModel();
        if (!users.isEmpty()) {
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
        } else {
            JOptionPane.showMessageDialog(this, "Lista de usuarios vazia.");
        }
    }
    
    private void initTableBooks() {
        DefaultTableModel tableModel = (DefaultTableModel) tableBooks.getModel();
        if (!books.isEmpty()) {
            try {
                books.forEach((Book book) -> {
                tableModel.addRow(new Object[] {book.getTitle(), 
                                                book.getAuthor(),
                                                book.getPublishingCompany(),
                                                book.getCategory(),
                                                book.getPublicationDate().toString(),
                                                book.getAmount()});});
                tableBooks.setModel(tableModel);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lista de Livros vazia.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableBooks;
    private javax.swing.JTable tableUsers;
    private javax.swing.JTextField txtReturnedDate;
    // End of variables declaration//GEN-END:variables
}
