package BudgetSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repository<T> {

    private final List<T> elements = new ArrayList<>();

    public void add(T element) {
        elements.add(element);
    }

    public Collection<T> findAll() {
        return elements;
    }
}
