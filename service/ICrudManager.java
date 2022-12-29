package service;

import java.util.List;
import java.util.Scanner;

public interface ICrudManager<E> {
    E create(Scanner scanner);
    void save(E e);

    void displayAll(List<E> elements);
}
