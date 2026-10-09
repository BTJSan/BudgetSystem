package BudgetSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<Transaction> filterByDate(List<Transaction> list, LocalDateTime filterDate) {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Transaction t = list.get(i);
            if (t.date().equals(filterDate))
                result.add(t);
        }
        return result;
    }

    //Todo: skapa metoder

    //filterByType
    //sortIncomeByDate
    //sortExpenseByDate

}
