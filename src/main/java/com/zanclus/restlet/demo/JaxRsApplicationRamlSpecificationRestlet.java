package com.zanclus.restlet.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.raml.model.Raml;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.ext.apispark.internal.conversion.raml.RamlTranslator;
import org.restlet.ext.apispark.internal.introspection.jaxrs.JaxRsIntrospector;
import org.restlet.ext.apispark.internal.model.Definition;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.routing.Router;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class JaxRsApplicationRamlSpecificationRestlet extends Restlet {
    private Application application;
    private String basePath;
    private Reference baseRef;
    private Definition definition;

    public JaxRsApplicationRamlSpecificationRestlet(Application application) {
        this(null, application);
    }

    public JaxRsApplicationRamlSpecificationRestlet(Context context, Application application) {
        super(context);
        this.application = application;
    }

    public void attach(Router router) {
        attach(router, "/api-docs");
    }

    public void attach(Router router, String path) {
        router.attach(path, this);
        router.attach(path + "/{resource}", this);
    }

    public Representation getApiDeclaration() {
        Raml raml = RamlTranslator.getRaml(
            getDefinition());
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            return new StringRepresentation(
                      mapper.writeValueAsString(raml), 
                      MediaType.APPLICATION_YAML);
        } catch (Exception ex) {
            return new StringRepresentation("error");
        }
    }

    public String getBasePath() {
        return basePath;
    }

    private synchronized Definition getDefinition() {
        if (definition == null) {
            synchronized (JaxRsApplicationRamlSpecificationRestlet.class) {
                definition = JaxRsIntrospector.getDefinition(application,
                    baseRef, false);
            }
        }

        return definition;
    }

    @Override
    public void handle(Request request, Response response) {
        super.handle(request, response);

        if (Method.GET.equals(request.getMethod())) {
            response.setEntity(getApiDeclaration());
        } else {
                response.setStatus(Status.CLIENT_ERROR_METHOD_NOT_ALLOWED);
        }
    }

    public void setApiInboundRoot(Application application) {
        this.application = application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
        // Process basepath and check validity
        this.baseRef = basePath != null ? new Reference(basePath) :  null;
    }
}
