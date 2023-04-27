package com.artorias.ecommerce.configuration;

import com.artorias.ecommerce.entity.Country;
import com.artorias.ecommerce.entity.State;
import com.artorias.ecommerce.entity.Product;
import com.artorias.ecommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    EntityManager entityManager;
    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        // disable put, post, and delete http methods for Product

        disableHttpMethods(config, theUnsupportedActions, Product.class);
        disableHttpMethods(config, theUnsupportedActions, ProductCategory.class);
        disableHttpMethods(config, theUnsupportedActions, Country.class);
        disableHttpMethods(config, theUnsupportedActions, State.class);

        this.exposeIds(config);
    }

    private static void disableHttpMethods(RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions, Class clazz) {
        config.getExposureConfiguration()
                .forDomainType(clazz)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }


    // expose entity ids
    private void exposeIds(RepositoryRestConfiguration config) {
//        // fetch set of all entity classes from entity manager
//        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
//        List<Class> entityTypes = new ArrayList<>();
//        entities.forEach( x -> entityTypes.add(x.getJavaType()));
        // build a list of entity types
//        List<Class> entityTypes = entities
//                                    .stream()
//                                    .map(entityType -> entityType.getJavaType())
//                                    .collect(Collectors.toList());
//         expose the entity ids for the array of entity/domain types
//        Class[] domainTypes = entityTypes.toArray(new Class[0]);

        // fetch set of all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        Class[] domainTypes = entities
                                .stream()
                                .map(entityType -> entityType.getJavaType())
                                .collect(Collectors.toList())
                                .toArray(new Class[0]);

        config.exposeIdsFor(domainTypes);
    }
}
