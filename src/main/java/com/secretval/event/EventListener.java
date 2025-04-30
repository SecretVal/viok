package com.secretval.event;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class EventListener {
    private final Consumer<Object> listenerCaller;
    private final Class<?> targetClass;

    public EventListener(Object target, Method m) {
        this(target, m, getEvent(m));
    }

    public EventListener(Object target, Method m, Class<? extends Event> event) {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            CallSite callsite = LambdaMetafactory.metafactory(lookup,
                "accept",
                MethodType.methodType(Consumer.class, target.getClass()),
                MethodType.methodType(void.class, Object.class),
                lookup.unreflect(m),
                MethodType.methodType(void.class, event));

            this.listenerCaller = (Consumer<Object>) callsite.getTarget().invokeWithArguments(target);
            this.targetClass = target.getClass();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static Class<? extends Event> getEvent(Method m) {
        Parameter[] params = m.getParameters();
        if (params.length == 0 || Event.class.isAssignableFrom(params[0].getClass())) {
            throw new RuntimeException("Tried to create EventListener with invalid params");
        }    
        return (Class<? extends Event>) params[0].getType();
    }

    public void call(Event e) {
        listenerCaller.accept(e);
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }
}
