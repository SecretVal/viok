package com.secretval.systems;

public abstract class System<T, M> {
    private final String name;

    public System(String name) {
        this.name = name;
    } 

    public void init() {};
    public abstract M getManager();
}
