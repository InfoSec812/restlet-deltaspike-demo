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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.raml.emitter.RamlEmitter;
import org.restlet.data.Reference;
import org.restlet.ext.apispark.internal.conversion.raml.RamlTranslator;
import org.restlet.ext.apispark.internal.introspection.application.ApplicationIntrospector;
import org.restlet.ext.apispark.internal.model.Definition;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Path("/raml")
public class RamlResource {

  private Definition definition;

  @GET
  public String getRaml() {
    return new RamlEmitter().dump(RamlTranslator
        .getRaml(getDefinition()));
  }

  private Definition getDefinition() {
    if (definition == null) {
      synchronized (RamlResource.class) {
        definition = ApplicationIntrospector.getDefinition(
            org.restlet.Application.getCurrent(),
            new Reference("/"), null, false);
      }
    }

    return definition;
  }
}
