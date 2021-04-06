package com.weaveown.design.create.build;

/**
 * @author wangwei
 * @date 2021/4/1
 */
public class Student {
    private final String name;
    private final int age;

    private final String address;
    private final String phone;

    public static class Builder {
        private String name;
        private int age;

        private String address;
        private String phone;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    private Student(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.phone = builder.phone;
    }

    public static void main(String[] args) {
        Student weaveown = new Builder("weaveown", 26).address("123").build();
    }
}
