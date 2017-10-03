package ua.rd.repository;

import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {

    private List<Tweet> tweets = new ArrayList<>();

    @PostConstruct
    public void init() {
        addTweet(new Tweet(1L, "First Mesg", null));
        addTweet(new Tweet(2L, "Second Mesg", null));

        System.out.println("[LOGGER] InMemTweetRepository :: init()");
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweets;
    }

    @Override
    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }
}
