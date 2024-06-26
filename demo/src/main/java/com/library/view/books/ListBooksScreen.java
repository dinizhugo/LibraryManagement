package com.library.view.books;

import com.library.controller.BookController;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.UnableToDeleteBook;
import com.library.model.entities.Book;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ListBooksScreen extends javax.swing.JInternalFrame {
    private BookController bookController;
    private javax.swing.JDesktopPane desktop;
    private BookOptions bookOptions;
    private List<Book> books;
    
    public ListBooksScreen(BookController bookController, javax.swing.JDesktopPane desktop, BookOptions bookOptions) {
        this.bookController = bookController;
        this.desktop = desktop;
        this.bookOptions = bookOptions;
        this.books = bookController.getBooks();
        initComponents();
        initTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableBooks = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Selecione um Livro");
        setPreferredSize(new java.awt.Dimension(469, 469));

        tableBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Author", "Publishing Company", "Category", "Publication Date", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        tableBooks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableBooks.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableBooks);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Selecione um Livro :");

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(270, 270, 270))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSelect)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnSelect)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int getRow() {
        int row = tableBooks.getSelectedRow();
 
        if (row < 0) {
            throw new IllegalArgumentException("Select a row.");
        }
        return row;
    }
    
    private void selectOption(Book book, BookController bookController, BookOptions options, javax.swing.JDesktopPane desktop) {
        if (options == BookOptions.UPDATE) {
            UpdateBook updateBook = new UpdateBook(book, bookController, desktop);
            updateBook.setVisible(true);
            desktop.add(updateBook);
        } else {
            try {
                bookController.deleteBook(book.getId());
                JOptionPane.showMessageDialog(this, "Livro foi removido com sucesso!");
                this.dispose();
            } catch (BookNotFoundException | UnableToDeleteBook e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }    
        }
    }
    
    public void btnSelectActionPerformed() {
        try {
            Book book = bookController.getBook(books.get(getRow()).getId());
            selectOption(book, bookController, bookOptions, desktop);
            this.dispose();
        } catch (BookNotFoundException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tableBooks.getModel();
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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBooks;
    // End of variables declaration//GEN-END:variables
}
