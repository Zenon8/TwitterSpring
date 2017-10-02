package ua.rd.repository;

import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {

    private List<Tweet> tweets;

    @PostConstruct
    public void init() {
        tweets = Arrays.asList(
                new Tweet(1L, "First Mesg", null),
                new Tweet(2L, "Second Mesg", null)
               );
        System.out.println("[CLASS] InMemTweetRepository :: [METHOD] init()");
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweets;
    }
}
