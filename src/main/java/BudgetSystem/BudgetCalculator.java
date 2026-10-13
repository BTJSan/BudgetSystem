package BudgetSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BudgetCalculator {

    public double totalBalance(Collection<Transaction> list) {

        return list.stream()
                .mapToDouble(t -> t.type() == TransactionType.INCOME
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

    public Map<TransactionType, Double> sumPerType(Collection<Transaction> list) {
        return list.stream()
                .collect(Collectors.groupingBy(t -> t.type(),
                Collectors.summingDouble(t -> t.amount())));
    }

    public List<Transaction> filterByDate(List<Transaction> list, LocalDateTime filterDate) {
        List<Transaction> result = list.stream()
                .filter(t -> t.date().equals(filterDate))
                .collect(Collectors.toList());
        return result;
    }

    public List<Transaction> filterByType(List<Transaction> list, TransactionType filterType) {
        List<Transaction> result = list.stream()
                .filter(t -> t.type() == filterType)
                .collect(Collectors.toList());
        return result;
    }


}
