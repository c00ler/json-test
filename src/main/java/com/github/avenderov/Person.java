package com.github.avenderov;

import java.beans.ConstructorProperties;

public final class Person {

    private final String firstName;

    private final String lastName;

    private final int age;

    private transient boolean constructorCalled;

    @ConstructorProperties({"firstName", "lastName", "age"})
    public Person(final String firstName, final String lastName, final int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        this.constructorCalled = true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isConstructorCalled() {
        return constructorCalled;
    }

}
