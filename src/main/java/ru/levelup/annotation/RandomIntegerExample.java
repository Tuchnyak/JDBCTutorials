package ru.levelup.annotation;

public class RandomIntegerExample {

    @RandomInteger(min = 14, max = 18)
    private int value;

    @RandomInteger(min = 1)
    private int valueWithoutMax;

    @RandomInteger(max = 200)
    private int valueWithoutMin;

}