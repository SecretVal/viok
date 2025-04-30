package com.secretval.systems;

import java.util.LinkedHashMap;
import java.util.Optional;

public interface Manager<I, T> {
    public void register(I id, T item);
    public void remove(I id);

    public Optional<T> get(I id);
    public LinkedHashMap<I, T> getAll();
}
