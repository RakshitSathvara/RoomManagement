package com.fusionbit.roommanagement.model;

public class Child {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalArgumentException {
        if (age > 18) {
            throw new IllegalArgumentException("Age should be below 18");
        }
        this.age = age;
    }
}
