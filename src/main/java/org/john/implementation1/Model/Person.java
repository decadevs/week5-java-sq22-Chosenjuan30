package org.john.implementation1.Model;

import org.john.enums.Role;

public class Person {

    private final String name;

    private final Role role;

    private final Book requestBook;

    public Person(String name, Role role, Book requestBook) {
        this.name = name;
        this.role = role;
        this.requestBook = requestBook;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Book getRequestBook() {
        return requestBook;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", role=" + role +
                ", requestBook=" + requestBook +
                '}';
    }
}
