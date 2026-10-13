package BudgetSystem;

public enum TransactionType {

    INCOME,
    EXPENSE;

    public String displayName() {
        return switch (this) {
            case INCOME -> "Inkomster";
            case EXPENSE -> "Utgifter";
        };
    }

}
