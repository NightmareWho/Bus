package ru.university.rogachev.service;

import ru.university.rogachev.dao.TrainDao;
import ru.university.rogachev.model.RoutePoint;
import ru.university.rogachev.model.Train;

import java.time.LocalTime;
import java.util.List;

public class TrainService {
        private final TrainDao trainDao;
    public TrainService(TrainDao trainDao){
        this.trainDao = trainDao;
    }

    public void createTrain(String number, String route){
        Train train = new Train(number, route);
        trainDao.create(train);
    }

    public void addRoute(String trainNumber, String station, LocalTime arrivalTime, LocalTime departureTime){
        Train train = trainDao.findByNumber(trainNumber);

        if (train == null){
            throw new IllegalArgumentException("Поезд не найден.");
        }

        RoutePoint point = new RoutePoint(station, arrivalTime, departureTime);
            train.addPoint(point);
            trainDao.delete(trainNumber);
        trainDao.create(train);
    }

    public void remove(String trainNumber){
        if (trainDao.findByNumber(trainNumber) == null) {
            throw new IllegalArgumentException("Поезд не найден.");
        }
        trainDao.delete(trainNumber);
    }

    public String allStations() {
        List<Train> trains = trainDao.findAll();
        StringBuilder sb = new StringBuilder();
        for (Train t : trains){
            sb.append("Поезд ").append(t.getNumber()).append(" ").append(t.getRouteName()).append("\n");
            for (RoutePoint rt : t.getPoints()){
                sb.append(" ").append(rt.getStation()).append(" ").append(rt.getArrivalTime()).append(" ").append(rt.getDepartureTime()).append("\n");
            }

        }
        return sb.toString();
    }
}
