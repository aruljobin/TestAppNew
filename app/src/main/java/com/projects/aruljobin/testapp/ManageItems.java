package com.projects.aruljobin.testapp;

/**
 * Created by aruljobin on 13/07/18.
 */

class ManageItems {

    private String userName;
    private String userCity;
    private String createdOn;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    private String commentCount;

    public ManageItems(){

    }


}
