package com.nothing.configuration.properties;

import com.nothing.annotations.ConfigProperty;
import lombok.Data;

@ConfigProperty(value = "connection", sourceFile = "classpath:connection.properties")
@Data //LOMBOK PLZ (https://github.com/projectlombok/lombok/issues/557) :(
public class ConnectionProperties {
    public String url;
}
