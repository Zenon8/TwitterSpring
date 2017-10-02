package ua.rd.repository;

import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;

public interface TweetRepository {

    Iterable<Tweet> allTweets();

}
