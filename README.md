Restlet+DeltaSpike+JPA+RAML Demo Application
============================================

Overview
--------

This is an example of using the [Restlet Framework](http://www.restlet.com/) in conjunction
with [Apache DeltaSpike](http://deltaspike.apache.org/) for dependency injection. The application
will use JPA for database persistence and will also generate it's own RAML API documentation
from the code.

Prerequisites
-------------

* Java >= 1.7
* Maven >= 3.1

Building
--------

```bash
mvn clean package
```

Running
-------

```bash
mvn exec:java
```