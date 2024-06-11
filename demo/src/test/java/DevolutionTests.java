import static org.junit.jupiter.api.Assertions.*;

import com.library.controller.BookController;
import com.library.controller.DevolutionController;
import com.library.controller.LoanController;
import com.library.controller.UserController;
import com.library.model.entities.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


public class DevolutionTests {
    private final DevolutionController devolutionController = new DevolutionController();
    private final UserController userController = new UserController();
    private final BookController bookController = new BookController();
    private final LoanController loanController = new LoanController();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    void addDevolution() throws ParseException {
        int idUser = 45; //Mude para o id do usuario que você queira testar
        int idBook = 1; // Mude para o id do Livro que você queira testar.
        User user = assertDoesNotThrow(() -> userController.getUser(idUser));
        Book book = assertDoesNotThrow(() -> bookController.getBook(idBook));
        LocalDate loanDate = sdf.parse("07/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate estimatedDate = sdf.parse("17/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        assertDoesNotThrow(() -> loanController.createNewLoan(user, book, loanDate, estimatedDate));
        List<Loan> loans = loanController.getLoans();
        Loan loan = assertDoesNotThrow(() -> loanController.getLoan(loans.get(loans.size() - 1).getId()));

        LocalDate returnedDate = sdf.parse("07/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        assertDoesNotThrow(() -> devolutionController.addDevolution(loan, returnedDate));
        Devolution devolution = assertDoesNotThrow(() -> devolutionController.getDevolutionByLoan(loan));

        assertEquals(loan.getId(), devolution.getLoan().getId());
        assertEquals(loan.getUser(), devolution.getLoan().getUser());
        assertEquals(loan.getBook(), devolution.getLoan().getBook());
        assertEquals(0.0, devolution.getTrafficTicket());
        assertEquals(loan.getBook().getAmount(), devolution.getLoan().getBook().getAmount());
        assertEquals(LoanStatus.RETURNED, devolution.getLoan().getLoanStatus());
    }

    @Disabled
    @Test
    void updateDevolution() throws ParseException {
        addDevolution();

        List<Devolution> devolutions = devolutionController.getDevolutions();
        int idDevolution = devolutions.get(devolutions.size() - 1).getId();
        Devolution devolution = assertDoesNotThrow(() -> devolutionController.getDevolutionById(idDevolution));
        List<Loan> loans = loanController.getLoans();
        LocalDate returnedDate = sdf.parse("19/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Loan loan = assertDoesNotThrow(() -> loanController.getLoan(loans.get(loans.size() - 1).getId()));
        LocalDate newEstimatedDate = sdf.parse("18/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int idUser = 45; //Mude para o id do usuario que você queira testar
        int idBook = 1; // Mude para o id do Livro que você queira testar.
        User user = assertDoesNotThrow(() -> userController.getUser(idUser));
        Book book = assertDoesNotThrow(() -> bookController.getBook(idBook));
        LocalDate newReturnDate = sdf.parse("25/06/2024").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        double traffictTicket = 0.0;

        assertDoesNotThrow(() -> loanController.updateLoan(loan, user, book,loan.getLoanDate(), newEstimatedDate, LoanStatus.RETURNED));
        assertDoesNotThrow(() -> devolutionController.updateDevolution(devolution, newReturnDate, traffictTicket));

        assertEquals(user, devolution.getLoan().getUser());
        assertEquals(book, devolution.getLoan().getBook());
        assertEquals(0.0, devolution.getTrafficTicket());
        assertEquals(returnedDate, devolution.getReturnDate());
        assertEquals(LoanStatus.RETURNED, devolution.getLoan().getLoanStatus());
    }

    @Test
    void deleteDevolution() throws ParseException {
        addDevolution();

        List<Devolution> devolutions = devolutionController.getDevolutions();
        int sizeBeforeDelete = devolutions.size();
        Devolution lastDevolution = assertDoesNotThrow(() -> devolutionController.getDevolutionById(devolutions.get(sizeBeforeDelete - 1).getId()));

        assertDoesNotThrow(() -> devolutionController.deleteDevolution(lastDevolution.getId()));

        List<Devolution> newDevolutions = devolutionController.getDevolutions();
        int sizeAfterDelete = newDevolutions.size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }
}
