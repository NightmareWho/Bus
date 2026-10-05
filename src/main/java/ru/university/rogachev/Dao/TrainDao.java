package ru.university.rogachev.dao;

import ru.university.rogachev.model.Train;

import java.util.List;

public interface TrainDao {
    void create(Train train);
    Train findByNumber(String number);
    List<Train> findAll();
    void delete(String number);
}
