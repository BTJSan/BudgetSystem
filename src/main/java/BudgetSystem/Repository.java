package BudgetSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Repository<T> {

    private final List<T> elements = new ArrayList<>();

    public void add(T element) {
        elements.add(element);
    }

    public Collection<T> findAll() {
        return elements;
    }

    public Collection<T> findWhere(Predicate<T> predicate) {
        Collection<T> result = new ArrayList<>();
        for (T element : elements) {
            if (predicate.test(element))
                result.add(element);
        }
        return result;
    }
}
