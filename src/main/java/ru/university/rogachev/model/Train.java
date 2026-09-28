package ru.university.rogachev.model;

public class Train {

    private int id;
    private String TrainName;
    private String TrainWay;

    public Train(String trainName, String trainWay) {
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
        this.TrainName = trainName;
    }

    public String getTrainWay() {
        return TrainWay;
    }

    public void setTrainWay(String trainName) {
        this.TrainName = trainName;
    }
}
