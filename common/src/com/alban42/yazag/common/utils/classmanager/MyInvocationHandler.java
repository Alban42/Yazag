package com.alban42.yazag.common.utils.classmanager;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler<T> implements InvocationHandler {

    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();

    private final T instance;

    public MyInvocationHandler(final T instance) {
        this.instance = instance;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        // validator.validate(method.getParameters())
        return method.invoke(instance, args);
    }

}
