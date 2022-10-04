package com.fan.entity;

public class Classify {

    int articleId;
    int labelId;



    public Classify() {
    }

    public Classify(int articleId, int labelId) {
        this.articleId = articleId;
        this.labelId = labelId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }
}

