package com.seniorproject.patrick.ksugo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by patri on 3/12/2018.
 */

public class Discussion {
    private int discussionID;
    private String creatorName;
    private String title;
    private String responseID;//If discussion is a response, this is its main id
    private String discussion;
    private Date timePosted;
    private ArrayList<Discussion>replies=new ArrayList<>();
    private int responseCount=0;

    public Discussion() {
    }
    public Discussion(int discussionID) {
        this.discussionID = discussionID;
    }
    public Discussion(int discussionID, String creatorName, String title, String discussion, Date timePosted) {
        this.discussionID = discussionID;
        this.creatorName = creatorName;
        this.title = title;
        this.discussion = discussion;
        this.timePosted=timePosted;
    }
    public Discussion(int discussionID, String creatorName, String title, String responseID, String discussion,Date timePosted) {

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

    public int getDiscussionID() {
        return discussionID;
    }

    public void setDiscussionID(int discussionID) {
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
    public Date getTimePosted() {
        return timePosted;
    }
    public void setTimePosted(Date timePosted) {
        this.timePosted = timePosted;
    }
    public ArrayList<Discussion>getReplies(){
        return replies;
    }
    public void addReplies(Discussion discussion){
        replies.add(discussion);
    }
}
