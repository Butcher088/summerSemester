package com.fan.entity;

public class Label {

        int labelId;
        String name;



    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", name='" + name + '\'' +
                '}';
    }

    public Label() {
    }

    public Label(int labelId, String name) {
        this.labelId = labelId;
        this.name = name;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
