package com.rasse.mongodemoservice.domen;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Attribute {
    private String param;
    private String param1;
    private String param2;

    public Attribute(String param) {
        this.param = param;
    }

    public Attribute(String param, String param1) {
        this.param = param;
        this.param1 = param1;
    }

    public Attribute(String param, String param1, String param2) {
        this.param = param;
        this.param1 = param1;
        this.param2 = param2;
    }
}
