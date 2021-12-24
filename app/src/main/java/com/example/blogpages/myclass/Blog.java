package com.example.blogpages.myclass;

import java.util.Date;

public class Blog {

    private String postid;
    private String title;
    private String category;
    private String smalldesc;
    private String fulldesc;
    private String postedBy;
//    private Date postedDate;
    private String postedDate;

    public Blog(){

    }

    public String getFulldesc() {
        return fulldesc;
    }

    public void setFulldesc(String fulldesc) {
        this.fulldesc = fulldesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmalldesc() {
        return smalldesc;
    }

    public void setSmalldesc(String smalldesc) {
        this.smalldesc = smalldesc;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
}
