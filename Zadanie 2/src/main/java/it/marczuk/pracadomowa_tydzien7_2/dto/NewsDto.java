package it.marczuk.pracadomowa_tydzien7_2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewsDto {

    private long newsId;
    private String title;
    private String urlImage;
    private String description;
    private String publishedArt;
}
