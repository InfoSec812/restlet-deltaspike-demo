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

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol ;
import org.restlet.ext.jaxrs.InstantiateException;
import org.restlet.ext.jaxrs.JaxRsApplication;
import org.restlet.ext.jaxrs.ObjectFactory;

/**
 * 
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class Main {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();

        // Starting the all contexts
        ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContexts();
        // You can use CDI here
        
        CdiObjectFactory objectFactory = new CdiObjectFactory();
        
        Component component = new Component();
        Server server = new Server(Protocol.HTTP, PORT);
        component.getServers().add(server);

        JaxRsApplication jaxRsApplication = new JaxRsApplication(component.getContext().createChildContext());
        jaxRsApplication.add(new Application());
        jaxRsApplication.setObjectFactory(objectFactory);
        component.getDefaultHost().attach("/rest", jaxRsApplication);

        component.start();
        System.out.println("Press any key to stop server.");
        System.in.read();
        component.stop();

        cdiContainer.shutdown();
    }

    private static class CdiObjectFactory implements ObjectFactory {

        @Override
        public <T> T getInstance(Class<T> aClass) throws InstantiateException {
            return BeanProvider.getContextualReference(aClass, false);
        }
    }
}