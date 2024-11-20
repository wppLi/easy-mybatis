package io.github.wppli.builder;

import io.github.wppli.session.Configuration;

/**
 * @author li--jiaqiang 2024−11−20
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}