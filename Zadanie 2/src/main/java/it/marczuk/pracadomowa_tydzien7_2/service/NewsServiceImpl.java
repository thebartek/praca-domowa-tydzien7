package it.marczuk.pracadomowa_tydzien7_2.service;

import it.marczuk.pracadomowa_tydzien7_2.dto.NewsDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<NewsDto> getAllNews() {
        String sql = "SELECT * FROM news";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return getNewsList(maps);
    }

    @Override
    public List<NewsDto> getNewsById(long id) {
        String sql = "SELECT * FROM news WHERE news_id=?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, id);
        return getNewsList(maps);
    }

    @Override
    public void updateNews(NewsDto newNews) {
        String sql = "UPDATE news SET news.title=?, news.url_image=?, news.description=?, news.published_art=? WHERE news.news_id = ?";
        jdbcTemplate.update(sql, newNews.getTitle(), newNews.getUrlImage(), newNews.getDescription(), newNews.getPublishedArt(), newNews.getNewsId());
    }

    private List<NewsDto> getNewsList(List<Map<String, Object>> maps) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        maps.forEach(element -> newsDtoList.add(new NewsDto(
                Long.parseLong(String.valueOf(element.get("news_id"))),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("url_image")),
                String.valueOf(element.get("description")),
                String.valueOf(element.get("published_art"))
        )));
        return newsDtoList;
    }
}
