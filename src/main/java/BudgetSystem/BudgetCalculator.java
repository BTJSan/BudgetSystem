package BudgetSystem;

import java.util.List;

public class BudgetCalculator {
    public double totalBalance(List<Transaction> list) {
        double result = 0.0;

        for (int i = 0; i < list.size(); i++) {
            Transaction t = list.get(i);

            if (t.type() == TransactionType.INCOME)
                result += t.amount();
            if (t.type() == TransactionType.EXPENSE)
                result -= t.amount();
        }
        return result;
    }

    //Todo: skapa metoder

    //totalBalance

    //filterByDate
    //filterByType
    //sortIncomeByDate
    //sortExpenseByDate

}
