package tech.dork.randoms_generator.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Field {
    private String title;
    private String value;
    @JsonProperty("short")
    private boolean myshort;
}
