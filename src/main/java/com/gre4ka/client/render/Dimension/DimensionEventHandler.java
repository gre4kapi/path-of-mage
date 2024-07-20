package com.gre4ka.client.render.Dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DimensionEventHandler<T> {

    private final List<Consumer<T>> listeners = new ArrayList<>();

    public DimensionEventHandler() {

    }

    public void addListener(Consumer<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(Consumer<T> listener) {
        listeners.remove(listener);
    }

    public void invoke(T event) {
        for (Consumer<T> listener : listeners) {
            listener.accept(event);
        }
    }

}