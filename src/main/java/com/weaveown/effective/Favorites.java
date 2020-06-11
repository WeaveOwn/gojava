package com.weaveown.effective;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangwei
 * @date 2020/6/4
 * @decription effective 33 键参数化；类型安全的异构容器
 */
public class Favorites {

    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        Favorites favorites = new Favorites();
        favorites.putFavorite(String.class, "string");
        favorites.putFavorite(Integer.class, 0x123);

        System.out.println(favorites.getFavorite(String.class));
        System.out.println(favorites.getFavorite(Integer.class));
    }
}
