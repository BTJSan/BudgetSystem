package BudgetSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BudgetCalculatorTest {

    BudgetCalculator calculator = new BudgetCalculator();

    @Test
    @DisplayName("totalBalance will return total balance of transactions")
    void totalBalanceWillReturnTotalBalanceOfTransactions() {
        Transaction t1 = new Transaction(LocalDateTime.now(),
                "Lön",
                20000,
                TransactionType.INCOME);
        Transaction t2 = new Transaction(LocalDateTime.now(),
                "Mat",
                300,
                TransactionType.EXPENSE);

        List<Transaction> list = List.of(t1, t2);

        double result = calculator.totalBalance(list);
        assertEquals(19700, result);

    }

    @Test
    @DisplayName("filterByDate returns result for given date")
    void filterByDateReturnsResultForGivenDate() {
        Transaction t1 = new Transaction(LocalDateTime.of(2025, Month.MARCH, 25, 13, 00),
                "Lön",
                20000,
                TransactionType.INCOME);
        Transaction t2 = new Transaction(LocalDateTime.of(2026, Month.JANUARY, 23, 13, 00),
                "Lön",
                20000,
                TransactionType.INCOME);

        List<Transaction> list = List.of(t1, t2);
        LocalDateTime filterDate = t1.date();

        List<Transaction> result = calculator.filterByDate(list, filterDate);
        assertEquals(1, result.size());
        assertTrue(result.contains(t1));
    }

    @Test
    @DisplayName("filterByType should return result for a transaction type")
    void filterByTypeShouldReturnResultForATransactionType() {
        Transaction t1 = new Transaction(LocalDateTime.now(),
                "Lön",
                20000,
                TransactionType.INCOME);
        Transaction t2 = new Transaction(LocalDateTime.now(),
                "Mat",
                300,
                TransactionType.EXPENSE);

        List<Transaction> list = List.of(t1, t2);
        TransactionType filterType = t1.type();

        List<Transaction> result = calculator.filterByType(list, filterType);
        assertEquals(1, result.size());
        assertTrue(result.contains(t1));
    }

}