package it.marczuk.pracadomowa_tydzien7_2.controller;

import it.marczuk.pracadomowa_tydzien7_2.dto.NewsDto;
import it.marczuk.pracadomowa_tydzien7_2.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/news")
public class NewsController {

    private NewsService service;

    @Autowired
    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping
    public String getNews(@RequestParam(value = "id", required = false) String id, Model model) {
        if(id == null) {
            model.addAttribute("newsList", service.getAllNews());
        } else {
            model.addAttribute("newsList", service.getNewsById(Long.parseLong(id)));
        }
        model.addAttribute("newNews", new NewsDto());
        return "index";
    }

    @PostMapping("/edit/{id}")
    public String editNews(@PathVariable long id, @ModelAttribute NewsDto newNews) {
        newNews.setNewsId(id);
        service.updateNews(newNews);
        return "redirect:/news";
    }
}
