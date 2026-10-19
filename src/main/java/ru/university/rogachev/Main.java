package ru.university.rogachev;

import ru.university.rogachev.dao.FileTrainDao;
import ru.university.rogachev.service.TrainService;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TrainService trainService = new TrainService(new FileTrainDao("trains.txt"));
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("""
        1 - Станции
        2 - Добавить маршрут
        3 - Добавить пункт
        4 - Удалить маршрут
        0 - Выход""");

            switch (in.nextLine().trim()) {
                case "1" -> {
                    System.out.print(trainService.allStations());
                }
                case "2" -> {
                    System.out.print("Номер поезда: ");
                    String id = in.nextLine().trim();
                    System.out.print("Название маршрута: ");
                    trainService.createTrain(id, in.nextLine().trim());
                }
                case "3" -> {
                    System.out.print("Номер поезда: ");
                    String id = in.nextLine().trim();
                    System.out.print("Станция: ");
                    String station = in.nextLine().trim();
                    System.out.print("Прибытие (ЧЧ:ММ, пусто для первой): ");
                    LocalTime arr = parseTime(in.nextLine().trim());
                    System.out.print("Отправление (ЧЧ:ММ): ");
                    LocalTime dep = parseTime(in.nextLine().trim());
                    trainService.addRoute(id, station, arr, dep);
                }
                case "4" -> {
                    System.out.println("Введите номер поезда который хотите удалить");
                    trainService.remove(in.nextLine());
                }
                case "0" -> { in.close(); return; }
                default -> System.out.println("Неизвестный пункт");
            }
        }
    }

    private static LocalTime parseTime(String s) {
        return s.isEmpty() ? null : LocalTime.parse(s);
    }
}