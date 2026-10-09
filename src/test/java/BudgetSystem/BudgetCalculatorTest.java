package BudgetSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BudgetCalculatorTest {

    BudgetCalculator calculator = new BudgetCalculator();

    @Test
    @DisplayName("totalBalance will return total balance of transactions")
    void totalBalance() {
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

}