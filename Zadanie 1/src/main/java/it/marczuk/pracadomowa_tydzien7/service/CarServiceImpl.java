package it.marczuk.pracadomowa_tydzien7.service;

import it.marczuk.pracadomowa_tydzien7.model.Car;
import it.marczuk.pracadomowa_tydzien7.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCar(Car car) {
        String sql = "INSERT INTO cars (mark, model, color, production_year) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getColor().toString(), car.getProductionYear());
    }

    @Override
    public List<Car> getAllCars() {
        String sql = "SELECT * FROM cars";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return getCarList(maps);
    }

    @Override
    public List<Car> getCarsByDate(int sDate, int eDate) {
        String sql = "SELECT * FROM cars WHERE production_year >= ? AND production_year <= ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, sDate, eDate);
        return getCarList(maps);
    }

    @Override
    public void removeCar(Long id) {
        String sql = "DELETE FROM cars WHERE cars.car_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private List<Car> getCarList(List<Map<String, Object>> maps) {
        List<Car> carList = new ArrayList<>();
        maps.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                Color.valueOf(String.valueOf(element.get("color"))),
                Integer.parseInt(String.valueOf(element.get("production_year")))
        )));
        return carList;
    }
}
