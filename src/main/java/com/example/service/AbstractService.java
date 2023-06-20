package com.example.service;

import java.util.stream.Stream;

public interface AbstractService<T, K> {

    T create(T t);

    Stream<T> getAll();

    T get(K id);

    T update(T t);

    void delete(K id);
}
