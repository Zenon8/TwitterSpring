package ua.rd.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

//@MyTweet("abc")
public class Tweet {

    private Long tweetId = Long.valueOf(super.hashCode());
    private String txt;
    private User user;

    public Tweet() {
    }

    public Tweet(String txt, User user) {
        this.txt = txt;
        this.user = user;
    }

    public Tweet(Long tweetId, String txt, User user) {
        this.tweetId = tweetId;
        this.txt = txt;
        this.user = user;
    }

    public Long getTweetId() {
        return tweetId;
    }


    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public String getTxt() {
        return txt;
    }

    //@Value("text")
    public void setTxt(String txt) {
        this.txt = txt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", txt='" + txt + '\'' +
                ", user=" + user +
                '}';
    }
}
