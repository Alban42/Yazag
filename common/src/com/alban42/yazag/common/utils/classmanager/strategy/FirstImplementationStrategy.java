package com.alban42.yazag.common.utils.classmanager.strategy;

import java.util.Set;

public class FirstImplementationStrategy implements ClassImplementationStrategy {

    @Override
    public Class<?> getImplementation(Set<?> implementations) {
        return (Class<?>) implementations.stream().findFirst().orElse(null);
    }
}
