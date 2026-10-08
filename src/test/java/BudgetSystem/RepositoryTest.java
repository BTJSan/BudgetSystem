package BudgetSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    Repository<Transaction> repo = new Repository<Transaction>();

    @Test
    @DisplayName("add-method will add a transaction")
    void addMethodWillAddATransaction() {
        repo.add(new Transaction(LocalDate.now().atStartOfDay(),
                "Birthday",
                200,
                TransactionType.INCOME));
        assertEquals(1, repo.findAll().size());
    }

}