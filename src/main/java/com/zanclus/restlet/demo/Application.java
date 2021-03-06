/**
 * Copyright (C) 2015 Deven Phillips (dphillips@zanclus.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zanclus.restlet.demo;

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
