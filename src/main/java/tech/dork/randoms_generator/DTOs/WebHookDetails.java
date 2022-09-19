package tech.dork.randoms_generator.DTOs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WebHookDetails {
    private String icon_emoji;
    private String text;
    private ArrayList<Attachment> attachments;
}
