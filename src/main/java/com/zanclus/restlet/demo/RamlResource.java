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
