package com.stosik.infrastructure.config;

public class Profiles
{
    /**
     * Profile used for any environment to run microservice in production mode.
     */
    public static final String PROD = "prod";
    
    /**
     * Profile used for development (local, demo, etc.)
     */
    public static final String DEV = "dev";
    
    /**
     * Profile used for integration tests
     */
    public static final String TEST = "test";
    
}
