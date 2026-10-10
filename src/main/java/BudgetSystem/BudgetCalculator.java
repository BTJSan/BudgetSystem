package BudgetSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BudgetCalculator {

    public double totalBalance(List<Transaction> list) {

        return list.stream().mapToDouble(t -> t.type() == TransactionType.INCOME
                ? t.amount() : -t.amount()).sum();

//        double result = 0.0;
//
//        for (int i = 0; i < list.size(); i++) {
//            Transaction t = list.get(i);
//
//            if (t.type() == TransactionType.INCOME)
//                result += t.amount();
//            if (t.type() == TransactionType.EXPENSE)
//                result -= t.amount();
//        }
//        return result;
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

    public List<Transaction> filterByType(List<Transaction> list, TransactionType filterType) {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Transaction t = list.get(i);
            if (t.type() == filterType)
                result.add(t);
        }
        return result;
    }

    //Todo: skapa metoder

    //sumPerCategory
    //sortIncomeByDate
    //sortExpenseByDate

}
