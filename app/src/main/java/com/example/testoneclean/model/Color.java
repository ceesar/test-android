package com.example.testoneclean.model;

import java.util.List;

public class Color {

    private String nameColor;
    private List<Size> size;

    public Color() {
    }

    public Color(String nameColor, List<Size> size) {
        this.nameColor = nameColor;
        this.size = size;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public List<Size> getSize() {
        return size;
    }

    public void setSize(List<Size> size) {
        this.size = size;
    }
}
