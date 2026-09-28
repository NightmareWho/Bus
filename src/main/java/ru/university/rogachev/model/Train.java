package ru.university.rogachev.model;

public class Train {

    private int id;
    private String TrainName;
    private String TrainWay;

    public Train(int id, String trainName, String trainWay) {
        this.id = id;
        TrainName = trainName;
        TrainWay = trainWay;
    }

    public int getId() {
        return id;
    }

    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String trainName) {
        TrainName = trainName;
    }

    public String getTrainWay() {
        return TrainWay;
    }

    public void setTrainWay(String trainName) {
        TrainName = trainName;
    }
}
