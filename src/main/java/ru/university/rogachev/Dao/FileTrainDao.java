package ru.university.rogachev.Dao;

import ru.university.rogachev.model.RoutePoint;
import ru.university.rogachev.model.Train;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileTrainDao implements ru.university.rogachev.Dao.TrainDao {
    private final String filePath;

    public FileTrainDao(String filePath){
        this.filePath = filePath;
    }

    private List<Train> readAll(){
        return new ArrayList<>();
    }

    private String formatTime(LocalTime time) {
        if(time == null){
            return "";
        }
        else {
            return time.toString();
        }
    }

    private void writeAll(List<Train> trains) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardCharsets.UTF_8)) {

            for (Train train : trains) {
                writer.write("TRAIN;" + train.getNumber() + ";" + train.getRouteName());
                writer.newLine();

                for (RoutePoint point : train.getPoints()) {
                    writer.write("ROUTE;" + point.getStation() +";"+formatTime(point.getArrivalTime())+";"+formatTime(point.getDepartureTime()));
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать файл", e);
        }
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
