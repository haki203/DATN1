package com.example.ttdn1.model;

public class Home {
    private String name, music;
    private int resource;
    public Home() {
    }

    public Home(String name, String music, int resource) {
        this.name = name;
        this.music = music;
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
