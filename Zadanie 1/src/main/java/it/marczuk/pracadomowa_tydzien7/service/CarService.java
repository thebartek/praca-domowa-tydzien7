package it.marczuk.pracadomowa_tydzien7.service;

import it.marczuk.pracadomowa_tydzien7.model.Car;

import java.util.List;

public interface CarService {

    void addCar(Car car);

    List<Car> getAllCars();

    List<Car> getCarsByDate(int sDate, int eDate);

    void removeCar(Long id);
}
