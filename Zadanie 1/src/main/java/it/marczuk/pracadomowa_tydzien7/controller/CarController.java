package it.marczuk.pracadomowa_tydzien7.controller;

import it.marczuk.pracadomowa_tydzien7.model.Car;
import it.marczuk.pracadomowa_tydzien7.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("newCar", new Car());
        return "index";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car newCar) {
        carService.addCar(newCar);
        return "redirect:/cars";
    }

    @GetMapping("/filter")
    public String searchByDate(@RequestParam(value = "sDate") int sDate,
                                @RequestParam(value = "eDate") int eDate,
                                Model model) {
        model.addAttribute("cars", carService.getCarsByDate(sDate, eDate));
        return "filterCars";
    }

    @GetMapping("/remove/{id}")
    public String removeCar(@PathVariable Long id) {
        carService.removeCar(id);
        return "redirect:/cars";
    }
}
