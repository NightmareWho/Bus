package ru.university.rogachev.dao;

import ru.university.rogachev.model.RoutePoint;
import ru.university.rogachev.model.Train;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileTrainDao implements ru.university.rogachev.dao.TrainDao {
    private final String filePath;

    public FileTrainDao(String filePath){
        this.filePath = filePath;
    }

    private List<Train> readAll(){
        Path path = Path.of(filePath);
        List<Train> trains = new ArrayList<>();
        if (!Files.exists(path)) {
            return trains;
        }

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            Train current = null;
            String line;

            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()){
                    continue;
                }
                String[] parts = line.split(";", -1);
                if (parts[0].equals("TRAIN")){
                    Train train = new Train(parts[1], parts[2]);
                    trains.add(train);
                    current = train;
                }
                else if(parts[0].equals("POINT")){
                    if(current == null){
                        throw new RuntimeException("Файл повреждён: точка без поезда");
                    }
                    RoutePoint point = new RoutePoint(parts[1], parseTime(parts[2]), parseTime(parts[3]));
                    current.addPoint(point);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл", e);
        }
        return trains;
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
                    writer.write("POINT;" + point.getStation() +";"+formatTime(point.getArrivalTime())+";"+formatTime(point.getDepartureTime()));
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

    private LocalTime parseTime(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }
        return LocalTime.parse(text.trim());
    }
}
