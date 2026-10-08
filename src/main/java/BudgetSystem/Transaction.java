package BudgetSystem;

import java.time.LocalDateTime;

public record Transaction(LocalDateTime date,
                          String category,
                          double amount,
                          TransactionType type) {
}
