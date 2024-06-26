/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.library.view;

import com.library.controller.BookController;
import com.library.controller.DevolutionController;
import com.library.controller.LoanController;
import com.library.controller.UserController;
import com.library.view.books.AddBookScreen;
import com.library.view.books.BookOptions;
import com.library.view.books.GetBooksScreen;
import com.library.view.books.ListBooksScreen;
import com.library.view.devolution.DevolutionOptions;
import com.library.view.devolution.GetDevolutionsScreen;
import com.library.view.devolution.ListDevolutionScreen;
import com.library.view.devolution.ReturnBookScreen;
import com.library.view.loans.GetLoansScreen;
import com.library.view.loans.ListLoansScreen;
import com.library.view.loans.LoanOptions;
import com.library.view.loans.NewLoanScreen;
import com.library.view.users.CreateNewUser;
import com.library.view.users.GetUsersScreen;
import com.library.view.users.ListUsersScreen;
import com.library.view.users.UserOptions;

/**
 *
 * @author Hugo Diniz
 */
public class MenuScreen extends javax.swing.JFrame {
    private final LoanController loanController = new LoanController();
    private final UserController userController = new UserController();
    private final BookController bookController = new BookController();
    private final DevolutionController devolutionController = new DevolutionController();
    
    public MenuScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        User = new javax.swing.JMenu();
        createUser = new javax.swing.JMenuItem();
        updateUser = new javax.swing.JMenuItem();
        deleteUser = new javax.swing.JMenuItem();
        getUsers = new javax.swing.JMenuItem();
        Book = new javax.swing.JMenu();
        addBook = new javax.swing.JMenuItem();
        updateBook = new javax.swing.JMenuItem();
        deleteBook = new javax.swing.JMenuItem();
        getBooks = new javax.swing.JMenuItem();
        Loan = new javax.swing.JMenu();
        newLoan = new javax.swing.JMenuItem();
        updateLoan = new javax.swing.JMenuItem();
        deleteLoan = new javax.swing.JMenuItem();
        getLoans = new javax.swing.JMenuItem();
        Devolution = new javax.swing.JMenu();
        newDevolution = new javax.swing.JMenuItem();
        updateDevolution = new javax.swing.JMenuItem();
        deleteDevolution = new javax.swing.JMenuItem();
        getDevolutions = new javax.swing.JMenuItem();
        moreDetail = new javax.swing.JMenu();
        menuCredits = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Biblioteca");
        setResizable(false);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        User.setText("Usuarios");

        createUser.setText("Criar novo usuario");
        createUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserActionPerformed(evt);
            }
        });
        User.add(createUser);

        updateUser.setText("Atualizar usuario");
        updateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserActionPerformed(evt);
            }
        });
        User.add(updateUser);

        deleteUser.setText("Remover usuario");
        deleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserActionPerformed(evt);
            }
        });
        User.add(deleteUser);

        getUsers.setText("Listar usuarios");
        getUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getUsersActionPerformed(evt);
            }
        });
        User.add(getUsers);

        jMenuBar1.add(User);

        Book.setText("Livros");

        addBook.setText("Adcionar livro");
        addBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookActionPerformed(evt);
            }
        });
        Book.add(addBook);

        updateBook.setText("Atualizar livro");
        updateBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookActionPerformed(evt);
            }
        });
        Book.add(updateBook);

        deleteBook.setText("Remover livro");
        deleteBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBookActionPerformed(evt);
            }
        });
        Book.add(deleteBook);

        getBooks.setText("Listar livros");
        getBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getBooksActionPerformed(evt);
            }
        });
        Book.add(getBooks);

        jMenuBar1.add(Book);

        Loan.setText("Emprestimos");

        newLoan.setText("Novo emprestimo");
        newLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLoanActionPerformed(evt);
            }
        });
        Loan.add(newLoan);

        updateLoan.setText("Atualizar emprestimo");
        updateLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLoanActionPerformed(evt);
            }
        });
        Loan.add(updateLoan);

        deleteLoan.setText("Excluir emprestimo");
        deleteLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteLoanActionPerformed(evt);
            }
        });
        Loan.add(deleteLoan);

        getLoans.setText("Listar emprestimo");
        getLoans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLoansActionPerformed(evt);
            }
        });
        Loan.add(getLoans);

        jMenuBar1.add(Loan);

        Devolution.setText("Devolu�oes");

        newDevolution.setText("Devolver livro");
        newDevolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newDevolutionActionPerformed(evt);
            }
        });
        Devolution.add(newDevolution);

        updateDevolution.setText("Atualizar devolu�ao");
        updateDevolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDevolutionActionPerformed(evt);
            }
        });
        Devolution.add(updateDevolution);

        deleteDevolution.setText("Deletar devolu�ao");
        deleteDevolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDevolutionActionPerformed(evt);
            }
        });
        Devolution.add(deleteDevolution);

        getDevolutions.setText("Listar devolu�oes");
        getDevolutions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getDevolutionsActionPerformed(evt);
            }
        });
        Devolution.add(getDevolutions);

        jMenuBar1.add(Devolution);

        moreDetail.setText("Mais");

        menuCredits.setText("Creditos");
        menuCredits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCreditsActionPerformed(evt);
            }
        });
        moreDetail.add(menuCredits);

        jMenuBar1.add(moreDetail);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 226, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void createUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserActionPerformed
        CreateNewUser createNewUser = new CreateNewUser(userController);
        createNewUser.setVisible(true);
        this.desktop.add(createNewUser);
    }//GEN-LAST:event_createUserActionPerformed

    private void deleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserActionPerformed
        ListUsersScreen listUsersScreen = new ListUsersScreen(userController, UserOptions.DELETE, desktop);
        listUsersScreen.setVisible(true);
        this.desktop.add(listUsersScreen);
    }//GEN-LAST:event_deleteUserActionPerformed

    private void updateLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLoanActionPerformed
        ListLoansScreen listLoansScreen = new ListLoansScreen(loanController, userController, bookController, desktop, LoanOptions.UPDATE);
        listLoansScreen.setVisible(true);
        desktop.add(listLoansScreen);
    }//GEN-LAST:event_updateLoanActionPerformed

    private void updateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserActionPerformed
        ListUsersScreen listUsersScreen = new ListUsersScreen(userController, UserOptions.UPDATE, desktop);
        listUsersScreen.setVisible(true);
        this.desktop.add(listUsersScreen);
    }//GEN-LAST:event_updateUserActionPerformed

    private void getUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getUsersActionPerformed
        GetUsersScreen getUsersScreen = new GetUsersScreen(userController);
        getUsersScreen.setVisible(true);
    }//GEN-LAST:event_getUsersActionPerformed

    private void deleteLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteLoanActionPerformed
         ListLoansScreen listLoansScreen = new ListLoansScreen(loanController, userController, bookController, desktop, LoanOptions.DELETE);
        listLoansScreen.setVisible(true);
        desktop.add(listLoansScreen);
    }//GEN-LAST:event_deleteLoanActionPerformed

    private void addBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookActionPerformed
        AddBookScreen addBookScreen = new AddBookScreen(bookController);
        addBookScreen.setVisible(true);
        desktop.add(addBookScreen);
    }//GEN-LAST:event_addBookActionPerformed

    private void updateBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBookActionPerformed
        ListBooksScreen listBooksScreen = new ListBooksScreen(bookController, desktop, BookOptions.UPDATE);
        listBooksScreen.setVisible(true);
        desktop.add(listBooksScreen);
    }//GEN-LAST:event_updateBookActionPerformed

    private void deleteBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBookActionPerformed
        ListBooksScreen listBooksScreen = new ListBooksScreen(bookController, desktop, BookOptions.DELETE);
        listBooksScreen.setVisible(true);
        desktop.add(listBooksScreen);
    }//GEN-LAST:event_deleteBookActionPerformed

    private void getBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getBooksActionPerformed
        GetBooksScreen getBooksScreen = new GetBooksScreen(bookController);
        getBooksScreen.setVisible(true);
    }//GEN-LAST:event_getBooksActionPerformed

    private void newLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLoanActionPerformed
        NewLoanScreen newLoanScreen = new NewLoanScreen(loanController, userController, bookController);
        newLoanScreen.setVisible(true);
        desktop.add(newLoanScreen);
    }//GEN-LAST:event_newLoanActionPerformed

    private void getLoansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLoansActionPerformed
        GetLoansScreen getLoansScreen = new GetLoansScreen(loanController);
        getLoansScreen.setVisible(true);
    }//GEN-LAST:event_getLoansActionPerformed

    private void newDevolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDevolutionActionPerformed
        ReturnBookScreen returnBookScreen = new ReturnBookScreen(loanController, devolutionController);
        returnBookScreen.setVisible(true);
        desktop.add(returnBookScreen);
    }//GEN-LAST:event_newDevolutionActionPerformed

    private void updateDevolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDevolutionActionPerformed
        ListDevolutionScreen listDevolutionScreen = new ListDevolutionScreen(devolutionController, desktop, DevolutionOptions.UPDATE);
        listDevolutionScreen.setVisible(true);
        desktop.add(listDevolutionScreen);
    }//GEN-LAST:event_updateDevolutionActionPerformed

    private void deleteDevolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDevolutionActionPerformed
        ListDevolutionScreen listDevolutionScreen = new ListDevolutionScreen(devolutionController, desktop, DevolutionOptions.DELETE);
        listDevolutionScreen.setVisible(true);
        desktop.add(listDevolutionScreen);
    }//GEN-LAST:event_deleteDevolutionActionPerformed

    private void getDevolutionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDevolutionsActionPerformed
        GetDevolutionsScreen getDevolutionsScreen = new GetDevolutionsScreen(devolutionController);
        getDevolutionsScreen.setVisible(true);
    }//GEN-LAST:event_getDevolutionsActionPerformed

    private void menuCreditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCreditsActionPerformed
        Credits credits = new Credits();
        credits.setVisible(true);
    }//GEN-LAST:event_menuCreditsActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MenuScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Book;
    private javax.swing.JMenu Devolution;
    private javax.swing.JMenu Loan;
    private javax.swing.JMenu User;
    private javax.swing.JMenuItem addBook;
    private javax.swing.JMenuItem createUser;
    private javax.swing.JMenuItem deleteBook;
    private javax.swing.JMenuItem deleteDevolution;
    private javax.swing.JMenuItem deleteLoan;
    private javax.swing.JMenuItem deleteUser;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem getBooks;
    private javax.swing.JMenuItem getDevolutions;
    private javax.swing.JMenuItem getLoans;
    private javax.swing.JMenuItem getUsers;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuCredits;
    private javax.swing.JMenu moreDetail;
    private javax.swing.JMenuItem newDevolution;
    private javax.swing.JMenuItem newLoan;
    private javax.swing.JMenuItem updateBook;
    private javax.swing.JMenuItem updateDevolution;
    private javax.swing.JMenuItem updateLoan;
    private javax.swing.JMenuItem updateUser;
    // End of variables declaration//GEN-END:variables
}
