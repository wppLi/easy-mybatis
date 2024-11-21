package io.github.wppli.builder;

import io.github.wppli.session.Configuration;
import io.github.wppli.type.TypeAliasRegistry;

/**
 * @author li--jiaqiang 2024−11−20
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}