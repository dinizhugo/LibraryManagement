import static org.junit.jupiter.api.Assertions.*;

import com.library.controller.BookController;
import com.library.controller.LoanController;
import com.library.controller.UserController;
import com.library.exceptions.LoanNotFoundException;
import com.library.model.entities.Book;
import com.library.model.entities.Loan;
import com.library.model.entities.LoanStatus;
import com.library.model.entities.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


public class LoanTests {
    private final LoanController loanController = new LoanController();
    private final UserController userController = new UserController();
    private final BookController bookController = new BookController();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    void createNewLoan() throws ParseException {
        User user = assertDoesNotThrow(() -> userController.getUser(1));
        Book book = assertDoesNotThrow(() -> bookController.getBook(1));
        int bookAmount = book.getAmount();
        LocalDate loanDate = sdf.parse("06/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate estimatedDate = sdf.parse("16/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        assertDoesNotThrow(() -> loanController.createNewLoan(user, book, loanDate, estimatedDate));

        List<Loan> loanByUser = assertDoesNotThrow(() -> loanController.getLoansByUser(user));

        if (loanByUser.isEmpty()) {
            assertThrows(LoanNotFoundException.class, () -> loanController.getLoan(loanByUser.get(1).getId()));
        }else {
            Loan loan = assertDoesNotThrow(() -> loanController.getLoan(loanByUser.get(1).getId()));
            assertEquals(user, loan.getUser());
            assertEquals(book, loan.getBook());
            assertEquals(loanDate, loan.getLoanDate());
            assertEquals(estimatedDate, loan.getEstimatedDate());
            assertEquals(LoanStatus.NOT_RETURNED, loan.getLoanStatus());
            assertEquals(bookAmount - 1, book.getAmount());
        }
    }

    @Test
    void updateLoan() throws ParseException {
        createNewLoan();

        Book book = assertDoesNotThrow(() -> bookController.getBook(1));
        List<Loan> loansByBook = assertDoesNotThrow(() -> loanController.getLoansByBook(book));
        int size = loansByBook.size();

        User newUser = assertDoesNotThrow(() -> userController.getUser(44));
        Book newBook = assertDoesNotThrow(() -> bookController.getBook(11));
        LocalDate newLoanDate = sdf.parse("26/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newEstimatedDate = sdf.parse("10/07/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LoanStatus loanStatus = LoanStatus.RETURNED;

        if (loansByBook.isEmpty()) {
            assertThrows(LoanNotFoundException.class, () -> loanController.getLoan(loansByBook.get(size - 1).getId()));
        } else {
            Loan loan = assertDoesNotThrow(() -> loanController.getLoan(loansByBook.get(size - 1).getId()));
            assertDoesNotThrow(() -> loanController.updateLoan(loan, newUser, newBook, newLoanDate, newEstimatedDate, loanStatus));
            assertEquals(newUser, loan.getUser());
            assertEquals(newBook, loan.getBook());
            assertEquals(newLoanDate, loan.getLoanDate());
            assertEquals(newEstimatedDate, loan.getEstimatedDate());
            assertEquals(LoanStatus.RETURNED, loan.getLoanStatus());}
    }

    @Test
    void deleteLoan() throws ParseException {
        createNewLoan();

        List<Loan> loans = loanController.getLoans();
        int sizeBeforeDelete = loans.size();
        Loan lastLoan = assertDoesNotThrow(() -> loanController.getLoan(loans.get(sizeBeforeDelete - 1).getId()));

        loanController.deleteLoan(lastLoan.getId());

        List<Loan> newLoans = loanController.getLoans();
        Integer sizeAfterDelete = newLoans.size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }
}
