package ru.university.rogachev.model;

import java.time.LocalTime;

public class RoutePoint {

    private String station;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public RoutePoint(String station, LocalTime arrivalTime, LocalTime departureTime){
        if (station == null || station.trim().isEmpty()){
            throw new IllegalArgumentException("Название станции не может быть пустым");
        }
        this.station = station.trim();

        if (arrivalTime == null && departureTime == null){
            throw new IllegalArgumentException("arrivalTime и departureTime не могут быть одновременно null");
        }
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public String getStation(){
        return station;
    }

    public LocalTime getArrivalTime(){
        return  arrivalTime;
    }

    public LocalTime getDepartureTime(){
        return  departureTime;
    }
}



