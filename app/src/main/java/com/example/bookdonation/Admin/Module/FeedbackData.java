package com.example.bookdonation.Admin.Module;

public class FeedbackData {

    private String UniqueKey,Feedback;
    public FeedbackData(){

    }

    public FeedbackData(String uniqueKey, String feedback) {
        UniqueKey = uniqueKey;
        Feedback = feedback;
    }

    public String getUniqueKey() {
        return UniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        UniqueKey = uniqueKey;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }
}
