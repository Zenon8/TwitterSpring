package ua.rd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import ua.rd.domain.Tweet;
import ua.rd.repository.TweetRepository;
import ua.rd.services.SimpleTweetService;
import ua.rd.services.TweetService;

import java.util.Arrays;

@Configuration
public class ServiceConfig {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private Environment env;

    @Bean
    @Scope("prototype")
    @Profile("default")
    public Tweet tweet() {
        return new Tweet();
    }

    @Bean("tweet")
    @Scope("prototype")
    @Profile("dev")
    public Tweet tweetDev() {
        Tweet tweet = new Tweet();
        tweet.setTweetId(1L);
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            tweet.setTxt("Dev + Test Text");
        } else {
            tweet.setTxt("Dev Text");
        }
        return tweet;
    }

    @Bean
    public TweetService tweetService() {
        TweetService tweetService =
                new SimpleTweetService(tweetRepository){
                    @Override
                    public Tweet newTweet() {
                        return tweet();
                    }
                };

        return tweetService;
    }

}
