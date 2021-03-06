package it.marczuk.pracadomowa_tydzien7_2.config;

import it.marczuk.pracadomowa_tydzien7_2.model.News;
import it.marczuk.pracadomowa_tydzien7_2.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
public class DbConfig {

    private DataSource dataSource;
    private NewsRepo repo;

    @Autowired
    public DbConfig(DataSource dataSource, NewsRepo repo) {
        this.dataSource = dataSource;
        this.repo = repo;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String sqlDrop =  "DROP TABLE IF EXISTS `news`";
        getJdbcTemplate().update(sqlDrop);

        String sqlCreate = "CREATE TABLE news (news_id int AUTO_INCREMENT PRIMARY KEY, title varchar (1000), url_image varchar (1000), description varchar (10000), published_art varchar (150))";
        getJdbcTemplate().update(sqlCreate);

        String sql = "INSERT INTO news(title, url_image, description, published_art ) VALUES (?, ?, ?, ?)";
        Optional<News> newsOptional = repo.getDataFromApi();
        newsOptional.ifPresent(news -> news.getArticles()
                .forEach(article -> getJdbcTemplate().update(sql, article.getTitle(),
                        article.getUrlToImage(), article.getDescription(), article.getPublishedAt())));
    }
}
