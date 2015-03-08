package com.zanclus.test;

import java.util.Set;
import javax.ws.rs.Path;
import org.reflections.Reflections;

public class Application extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        // Use the reflections library to scan the current package tree for
        // classes annotated with javax.ws.rs.Path and add them to the JAX-RS
        // application
        Reflections reflections = new Reflections(this.getClass().getPackage().getName());
        return reflections.getTypesAnnotatedWith(Path.class);
    }
}
