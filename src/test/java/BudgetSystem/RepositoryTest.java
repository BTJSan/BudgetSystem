package BudgetSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import BudgetSystem.Transaction;
import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    Repository<Transaction> repo = new Repository<Transaction>();

    @Test
    @DisplayName("add-method will add a transaction")
    void addMethodWillAddATransaction() {
        repo.add(new Transaction(LocalDateTime.now(),
                "Birthday",
                200,
                TransactionType.INCOME));
        assertEquals(1, repo.findAll().size());
    }

    @Test
    @DisplayName("findAll initially returns empty list")
    void findAllInitiallyReturnsEmptyList() {
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    @DisplayName("findAll returns all elements")
    void findAllReturnsAllElements() {
        repo.add(new Transaction(LocalDateTime.now(),
                "Veckopeng",
                100,
                TransactionType.INCOME));

        repo.add(new Transaction(LocalDateTime.now(),
                "Mat",
                50,
                TransactionType.EXPENSE));

        assertEquals(2, repo.findAll().size());
    }

    @Test
    @DisplayName("findWhere returns specifik elements from list")
    void findWhereReturnsSpecifikElementsFromList() {
        repo.add(new Transaction(LocalDateTime.now(),
                "Veckopeng",
                100,
                TransactionType.INCOME));

        repo.add(new Transaction(LocalDateTime.now(),
                "Mat",
                50,
                TransactionType.EXPENSE));

        Collection<Transaction> income = repo.findWhere(element -> element.type() == TransactionType.INCOME);

        assertEquals(1, income.size());
    }

}