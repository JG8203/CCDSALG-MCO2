package com.dlsu;

public class Relationship {
    private int id;
    private int friend;

    public Relationship(Integer id, Integer friend){
        this.id = id;
        this.friend = friend;
    }

    public int getId() {
        return id;
    }

    public int getFriend() {
        return friend;
    }
}
