package org.john.implementation1.Service;

import org.john.implementation1.Model.Book;

import org.john.implementation1.Model.Person;

public interface LibraryService {

    void addBook(Book book);

    void addPersonToQueue(Person person);

    void borrowBook();

    boolean returnBook(Book book);
}
