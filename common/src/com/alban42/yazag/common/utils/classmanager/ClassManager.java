package com.alban42.yazag.common.utils.classmanager;

import com.alban42.yazag.common.utils.classmanager.strategy.ClassImplementationStrategy;
import com.alban42.yazag.common.utils.classmanager.strategy.FirstImplementationStrategy;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class allow to instantiate the related class given an interface. Different strategies {@link ClassImplementationStrategy} can be applied in
 * order to select the class to instantiate.
 *
 * @author Alban
 */
public class ClassManager {

    public final static Reflections reflections = getNewReflectionsInstance();
    final static ClassImplementationStrategy classImplementationStrategy = new FirstImplementationStrategy();

    /**
     * Get a new instance of the interface given in parameter. The {@link MyInvocationHandler} will be the proxy used to execute the methods of the
     * implementation.
     *
     * @param clazz the interface to instanciate.
     * @return a new instance of the clazz.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(final Class<? extends T> clazz) {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{clazz},
                                          new MyInvocationHandler<T>(getNewInstanceOf(clazz)));
    }

    /**
     * Get a new instance of the interface given in parameter.
     *
     * @param clazz the interface to instanciate.
     * @return a new instance of the clazz.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getNewInstanceOf(final Class<T> clazz) {
        final Set<?> implementations = reflections.getSubTypesOf(clazz);
        final Class<T> clz = (Class<T>) classImplementationStrategy.getImplementation(implementations);
        return newInstanceOrNull(clz);
    }

    /**
     * This methods create a new {@link Reflections} instance.
     *
     * @return a new instance of {@link Reflections}.
     */
    private static Reflections getNewReflectionsInstance() {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);
        final ClassLoader classLoader = ClassManager.class.getClassLoader();
        final Set<URL> forClassLoader = new HashSet<URL>(ClasspathHelper.forClassLoader(classLoader));
        final Reflections reflections = new Reflections(new ConfigurationBuilder().addUrls(forClassLoader)
                                                                                  .setScanners(new SubTypesScanner(),
                                                                                               new TypeAnnotationsScanner())
                                                                                  .setExecutorService(executorService));
        executorService.shutdown();
        return reflections;
    }

    /**
     * Return a new instance of given class in parameter.
     *
     * @param clazz the class to instantiate.
     * @return the instance.
     */
    public static <T extends Object> T newInstanceOrNull(final Class<T> clazz) {
        T newInstance = null;
        if (clazz != null) {
            try {
                newInstance = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                // Nothing to do.
            }
        }
        return newInstance;
    }

}
