package tech.dork.randoms_generator.DTOs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Attachment {
    private String fallback;
    private String pretext;
    private String color;
    private ArrayList<Field> fields;
}
