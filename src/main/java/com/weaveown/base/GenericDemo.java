package com.weaveown.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author wangwei
 * @date 2021/1/29
 */
public class GenericDemo<T, R> {

    public R reduce(List<T> items, R init, Func<T, R> function) {
        R result = init;
        for (T item : items) {
            result = function.apply(item, result);
        }
        return result;
    }

    public Integer countIf(List<T> items, Function<T, Boolean> function) {
        int count = 0;
        for (T item : items) {
            if (function.apply(item)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        GenericDemo<Student, Integer> demo = new GenericDemo<>();
        List<Student> students = new ArrayList<>();
        students.add(new Student(10, "10"));
        students.add(new Student(30, "30"));
        Integer reduce = demo.reduce(students, 0, (a, b) -> b + a.age);
        System.out.println(reduce);
        Integer integer = demo.countIf(students, student -> student.age <= 20);
        System.out.println(integer);
    }

    @Data
    @AllArgsConstructor
    static class Student {
        private int age;
        private String name;
    }

    static interface Func<T, R> {
        R apply(T a, R b);
    }
}
