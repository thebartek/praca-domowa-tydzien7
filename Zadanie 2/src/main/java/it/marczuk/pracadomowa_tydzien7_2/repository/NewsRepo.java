package it.marczuk.pracadomowa_tydzien7_2.repository;

import it.marczuk.pracadomowa_tydzien7_2.model.News;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class NewsRepo {

    @Value("e18a7f169bc94632937b69fe6529a153")
    private String apiTokien;

    public Optional<News> getDataFromApi() {
        try {
            RestTemplate restTemplateArtical = new RestTemplate();
            return Optional.ofNullable(restTemplateArtical.getForObject("https://newsapi.org/v2/top-headlines?country=pl&apiKey=" + apiTokien, News.class));
        } catch (RestClientException ex) {
            throw new RuntimeException(ex);
        }
    }
}
