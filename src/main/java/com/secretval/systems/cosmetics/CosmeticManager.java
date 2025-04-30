package com.secretval.systems.cosmetics;

import java.util.HashMap;
import java.util.Optional;
import java.util.LinkedHashMap;

import com.secretval.systems.Manager;

public class CosmeticManager implements Manager<String, Cosmetic>{
    private LinkedHashMap<String, Cosmetic> cosmetics = new LinkedHashMap<>();

    @Override
    public void register(String id, Cosmetic cosmetic) {
        if (cosmetics.containsKey(id)) {
            // TODO: Error
            return;
        }

        cosmetics.put(id, cosmetic);;
    }

    @Override
    public void remove(String id) {
        if (cosmetics.containsKey(id)) {
            cosmetics.remove(id);
        } else {
            // TODO: Error
            return;
        }
    }

    @Override
    public Optional<Cosmetic> get(String id) {
        if (cosmetics.containsKey(id)) {
            return Optional.of(cosmetics.get(id));
        } else {
            return null;
        }
    }

    @Override
    public LinkedHashMap<String, Cosmetic> getAll() {
        return cosmetics;
    }
}
