package com.depp.common.swagger;

import lombok.Data;

/**
 * request parameter options
 * @author Depp
 */
@Data
public class ParameterProperties {
    /**
     * parameter name
     */
    private String name;
    /**
     * the description of the parameter
     */
    private String description;
    /**
     *  Represents the convenience method to infer the model reference
     *  Consolidate or figure out whats can be rolled into the other.
     *  default {@code string}
     */
    private String modelRef = "string";
    /**
     * the type of parameter
     * Could be header, cookie, body, query etc.
     */
    private String parameterType;
    /**
     * if the parameter is required or optional.Defaults to {@code false}
     */
    private boolean required;

}
