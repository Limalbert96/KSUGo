package com.seniorproject.patrick.ksugo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by patri on 3/12/2018.
 */

public class DiscussionBoard {
    private ArrayList<Discussion> discussionBoard=new ArrayList<>();
    private int discussionBoardID;
    private Date dueDate;
    private String topic;
    private String title;
    private int discussionCount;

    public DiscussionBoard() {
        discussionCount=0;
    }
    public DiscussionBoard(int discussionBoardID, String topic, String title) {
        this.discussionBoardID = discussionBoardID;
        this.topic = topic;
        this.title = title;
        discussionCount=0;

    }
    public DiscussionBoard(int discussionBoardID, Date dueDate, String topic, String title) {
        this.discussionBoardID = discussionBoardID;
        this.dueDate = dueDate;
        this.topic = topic;
        this.title = title;
        discussionCount=0;

    }
    public ArrayList<Discussion> getDiscussionBoard() {
        return discussionBoard;
    }
    public void setDiscussionBoard(ArrayList<Discussion> discussionBoard) {
        this.discussionBoard = discussionBoard;
    }
    public int getDiscussionBoardID() {
        return discussionBoardID;
    }
    public void setDiscussionBoardID(int discussionBoardID) {
        this.discussionBoardID = discussionBoardID;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void addDiscussion(String creatorName, String title, String discussion,Date timePosted){
        Discussion discussion1=new Discussion(discussionCount,creatorName,title,discussion,timePosted);
        discussionBoard.add(discussion1);
        discussionCount++;
    }
    public void addDiscussion(Discussion discussion){
        discussion.setDiscussionID(discussionCount);
        discussionBoard.add(discussion);
        discussionCount++;
    }

    public void respond(String creatorName, String title, String responseID, String discussion,Date timePosted){
        Discussion response=new Discussion(discussionCount, creatorName, title, responseID, discussion,timePosted);
        discussionBoard.add(response);
        discussionCount++;
    }
    public void editDiscussion(int discussionID, String text){
        Discussion discussion=discussionBoard.get(discussionID);
        discussion.setDiscussion("Edited:"+text);
    }
    public void removeDiscussion(int discussionID){
        for (Discussion discussion: discussionBoard){
            if(discussionID==discussion.getDiscussionID()){
                discussionBoard.remove(discussion);
            }
        }

    }

    public int getDiscussionCount() {
        return discussionCount;
    }

    public void setDiscussionCount(int discussionCount) {
        this.discussionCount = discussionCount;
    }

}
