package com.weaveown;

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

    public static void main(String[] args) {
        GenericDemo<Student, Integer> demo = new GenericDemo<>();
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "1"));
        students.add(new Student(2, "2"));
        Integer reduce = demo.reduce(students, 0, (a, b) -> b + a.age);
        System.out.println(reduce);
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
