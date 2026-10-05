package ru.university.rogachev.dao;

import ru.university.rogachev.model.Train;

import java.util.ArrayList;
import java.util.List;

public class FileTrainDao implements TrainDao{
    private final String filePath;

    public FileTrainDao(String filePath){
        this.filePath = filePath;
    }

    private List<Train> readAll(){
        return new ArrayList<>();
    }

    private void writeAll(List<Train> trains){

    }

    public void create(Train train){
        List<Train> trains = readAll();
        trains.add(train);
        writeAll(trains);
    }

    public void delete(String number){
        List<Train> trains = readAll();
        trains.removeIf(t -> t.getNumber().equals(number));
        writeAll(trains);
    }

    public Train findByNumber(String number){
        List<Train>trains = readAll();
        for(Train t : trains){
            if(t.getNumber().equals(number))
                return t;
        }
        return null;
    }

    public List<Train> findAll() {
        return readAll();
    }
}
