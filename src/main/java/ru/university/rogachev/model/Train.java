package ru.university.rogachev.model;

import java.util.ArrayList;
import java.util.List;

public class Train {

    private String number;
    private String routeName;
    private final List<RoutePoint> points = new ArrayList<>();

    public Train(String number, String routeName) {
        if (number == null || number.trim().isEmpty()){
            throw new IllegalArgumentException("Номер поезда не может быть пустым");
        }
        this.number = number.trim();
        this.setRouteName(routeName);
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        if (routeName == null || routeName.trim().isEmpty()){
            throw new IllegalArgumentException("Название маршрута не может быть null");
        }
        this.routeName = routeName;
    }

    public String getNumber(){
        return number;
    }

    public void addPoint(RoutePoint point){
        if (point == null){
            throw new IllegalArgumentException("Пункт маршрута не может быть null");
        }
        points.add(point);
    }

    public void removePoint(int index){
        points.remove(index);
    }

    public void updatePoint(int index, RoutePoint point){
        if (point == null){
            throw new IllegalArgumentException("Пункт маршрута не может быть null");
        }
        points.set(index, point);
    }

    public List<RoutePoint> getPoints() {
        return new ArrayList<>(points);
    }
}
