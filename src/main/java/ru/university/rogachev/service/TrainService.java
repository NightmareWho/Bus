package ru.university.rogachev.service;

import ru.university.rogachev.dao.TrainDao;
import ru.university.rogachev.model.RoutePoint;
import ru.university.rogachev.model.Train;

import java.time.LocalTime;

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
}
