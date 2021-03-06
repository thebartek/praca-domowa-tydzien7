package it.marczuk.pracadomowa_tydzien7_2.service;

import it.marczuk.pracadomowa_tydzien7_2.dto.NewsDto;

import java.util.List;

public interface NewsService {

    List<NewsDto> getAllNews();

    List<NewsDto> getNewsById(long id);

    void updateNews(NewsDto newNews);
}
