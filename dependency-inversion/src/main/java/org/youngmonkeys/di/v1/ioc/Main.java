package org.youngmonkeys.di.v1.ioc;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        System.out.println(library.sum(1, 2));
    }
}