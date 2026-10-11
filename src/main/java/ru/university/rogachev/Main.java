package ru.university.rogachev;

import ru.university.rogachev.Dao.FileTrainDao;
import ru.university.rogachev.model.RoutePoint;
import ru.university.rogachev.model.Train;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Train train = new Train("001А", "Москва - Питер");
        train.addPoint(new RoutePoint("Москва", null, LocalTime.of(10, 0)));
        train.addPoint(new RoutePoint("Тверь", LocalTime.of(12, 0), LocalTime.of(12, 5)));
        train.addPoint(new RoutePoint("Питер", LocalTime.of(15, 0), null));
        new FileTrainDao("trains.txt").create(train);
    }
}