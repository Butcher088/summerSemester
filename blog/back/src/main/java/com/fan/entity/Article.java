package com.fan.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Data
public class Article {

    private int articleId;
    private int userId;
    private Timestamp createTime;
    private String htmlContent;
    private String title;
    private int likeCount;
    private int collectCount;
    private int clickCount;
    private Timestamp editTime;
    private String mdContent;

    private String[] labels;
    private String labelString; // 用来转化
    private String summary;
    private String cover;
    private int commentCount;
    private String img;
    private String userName;

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", htmlContent='" + htmlContent + '\'' +
                ", title='" + title + '\'' +
                ", likeCount=" + likeCount +
                ", collectCount=" + collectCount +
                ", clickCount=" + clickCount +
                ", editTime=" + editTime +
                ", mdContent='" + mdContent + '\'' +
                ", labels=" + Arrays.toString(labels) +
                ", summary='" + summary + '\'' +
                ", cover='" + cover + '\'' +
                ", commentCount=" + commentCount +
                '}';
    }

    public Article() {
    }

    public Article(int articleId, int userId, Timestamp createTime, String htmlContent, String title, int likeCount, int collectCount, int clickCount, Timestamp editTime, String mdContent, String[] labels, String summary, String cover, int comment_count, String img, String userName) {
        this.articleId = articleId;
        this.userId = userId;
        this.createTime = createTime;
        this.htmlContent = htmlContent;
        this.title = title;
        this.likeCount = likeCount;
        this.collectCount = collectCount;
        this.clickCount = clickCount;
        this.editTime = editTime;
        this.mdContent = mdContent;
        this.labels = labels;
        this.summary = summary;
        this.cover = cover;
        this.commentCount = comment_count;
        this.img = img;
        this.userName = userName;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels.toArray(new String[0]);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getComment_count() {
        return commentCount;
    }

    public void setComment_count(int comment_count) {
        this.commentCount = comment_count;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
