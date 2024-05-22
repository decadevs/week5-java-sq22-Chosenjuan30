package org.john.implementation1.Service;

import org.john.implementation1.Model.Person;

import java.util.Comparator;

public class RoleComparator implements Comparator<Person> {


    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p2.getRole().ordinal(),p1.getRole().ordinal());
    }
}


