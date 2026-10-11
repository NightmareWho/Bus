package ru.university.rogachev.service;

import ru.university.rogachev.model.Train;

public class TrainService {

    public void createTrain(String name, String number) {
        Train train = new Train(name, number);

    }
}
