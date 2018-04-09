package com.seniorproject.patrick.ksugo;

import java.util.Date;

/**
 * Created by patri on 3/12/2018.
 */

public class Discussion {
    private String discussionID;
    private String creatorName;
    private String title;
    private String responseID;//ID of the discussion creator is responding to
    private String discussion;
    private Date timePosted;
    private int responseCount=0;

    public Discussion() {
    }
    public Discussion(String discussionID) {
        this.discussionID = discussionID;
    }
    public Discussion(String discussionID, String creatorName, String title, String discussion, Date timePosted) {
        this.discussionID = discussionID;
        this.creatorName = creatorName;
        this.title = title;
        this.discussion = discussion;
        this.timePosted=timePosted;
    }
    public Discussion(String discussionID, String creatorName, String title, String responseID, String discussion,Date timePosted) {

        this.discussionID = discussionID;
        this.creatorName = creatorName;
        this.title = title;
        this.responseID = responseID;
        this.discussion = discussion;
        this.timePosted=timePosted;
    }

    public String getDiscussion() {
        return discussion;
    }
    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }
    public String getDiscussionID() {
        return discussionID;
    }
    public void setDiscussionID(String discussionID) {
        this.discussionID = discussionID;
    }
    public String getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getResponseID() {
        return responseID;
    }
    public void setResponseID(String responseID) {
        this.responseID = responseID;
        responseCount++;

    }
    public boolean isResponse(){
        if(responseID!=null){
            return true;
        }
        return false;
    }
    public int getResponseCount(){
        return responseCount;}
    public String toString(){
        return title+":\n"+"Created by "+creatorName+"\n"+discussion;
    }
    public String responseToString(){
        return discussion+"\n"+"Response by: "+creatorName;
    }

}
