package com.fan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    /**自身属性**/
    private Integer comment_id;  // 评论id
    private String content;     // 评论内容
    private Integer from_id;
    private Integer status;    // 1 为正常 0 为删除
    private Date time;
    private int like; // 点赞量

    /**实体关系**/
    private Integer article_id;  // blog本身
    private Integer father_id;  // 父评论的id
    private String father_name; // 父评论name
    private String father_content; // 父评论内容
    private Integer root_id;    // 根评论的id
    private List<Comment> child;   // 评论下的子评论
    private int isYouLike;

    /**连接查询**/
    private String img;
    private String from_name; // 发起人名字
    private String position; // 职位


    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Integer from_id) {
        this.from_id = from_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getFather_id() {
        return father_id;
    }

    public void setFather_id(Integer father_id) {
        this.father_id = father_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getFather_content() {
        return father_content;
    }

    public void setFather_content(String father_content) {
        this.father_content = father_content;
    }

    public Integer getRoot_id() {
        return root_id;
    }

    public void setRoot_id(Integer root_id) {
        this.root_id = root_id;
    }

    public List<Comment> getChild() {
        return child;
    }

    public void setChild(List<Comment> child) {
        this.child = child;
    }

    public int getIsYouLike() {
        return isYouLike;
    }

    public void setIsYouLike(int isYouLike) {
        this.isYouLike = isYouLike;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", content='" + content + '\'' +
                ", from_id=" + from_id +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", article_id=" + article_id +
                ", father_id=" + father_id +
                ", father_content='" + father_content + '\'' +
                ", root_id=" + root_id +
                ", child=" + child +
                ", img='" + img + '\'' +
                ", from_name='" + from_name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

}
