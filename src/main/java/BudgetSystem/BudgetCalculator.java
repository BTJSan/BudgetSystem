package BudgetSystem;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BudgetCalculator {

    public double totalBalance(Collection<Transaction> list) {

        return list.stream()
                .mapToDouble(t -> t.type() == TransactionType.INCOME
                ? t.amount() : -t.amount()).sum();

    }

    public Map<TransactionType, Double> sumPerType(Collection<Transaction> list) {
        return list.stream()
                .collect(Collectors.groupingBy(t -> t.type(),
                Collectors.summingDouble(t -> t.amount())));
    }

    public List<Transaction> filterByDate(Collection<Transaction> list, LocalDate filterDate) {
        List<Transaction> result = list.stream()
                .filter(t -> t.date().toLocalDate().equals(filterDate))
                .collect(Collectors.toList());
        return result;
    }

    public List<Transaction> filterByType(Collection<Transaction> list, TransactionType filterType) {
        List<Transaction> result = list.stream()
                .filter(t -> t.type() == filterType)
                .collect(Collectors.toList());
        return result;
    }


}
