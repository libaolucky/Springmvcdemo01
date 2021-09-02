package com.xiexin.bean;

public class Dog {
    private int dogId;
    private String dogsex;

    @Override
    public String toString() {
        return "Dog{" +
                "dogId=" + dogId +
                ", dogsex='" + dogsex + '\'' +
                '}';
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getDogsex() {
        return dogsex;
    }

    public void setDogsex(String dogsex) {
        this.dogsex = dogsex;
    }
}
