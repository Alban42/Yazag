package com.alban42.yazag.common.utils.classmanager.test;

import com.alban42.yazag.common.utils.classmanager.ClassManager;

public class Run {

    public static void main(final String[] args) {
        final ITest test = ClassManager.getInstance(ITest.class);
        System.out.println(test.testMethode("Alban"));
    }
}
