package it.marczuk.pracadomowa_tydzien7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void init() {
//        String sql = "CREATE TABLE cars(car_id int NOT NULL AUTO_INCREMENT, mark varchar(255) NOT NULL, model varchar(255) NOT NULL, color varchar(255), production_year int, PRIMARY KEY (car_id))";
//        getJdbcTemplate().update(sql);
//    }
}
