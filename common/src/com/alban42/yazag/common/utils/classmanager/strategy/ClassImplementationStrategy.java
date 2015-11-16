package com.alban42.yazag.common.utils.classmanager.strategy;

import java.util.Set;

public interface ClassImplementationStrategy {

    Class<?> getImplementation(Set<?> implementations);
}
