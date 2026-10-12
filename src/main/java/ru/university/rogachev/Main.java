package ru.university.rogachev;

import ru.university.rogachev.dao.FileTrainDao;
import ru.university.rogachev.dao.TrainDao;
import ru.university.rogachev.model.RoutePoint;
import ru.university.rogachev.model.Train;
import ru.university.rogachev.service.TrainService;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        TrainDao trainDao = new FileTrainDao("trains.txt");
        TrainService trainService = new TrainService(trainDao);
        trainService.createTrain("001A", "Москва - Питер");
        trainService.addRoute("001A", "Москва",null, LocalTime.of(10, 0));

    }
}