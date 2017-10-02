package ua.rd.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.rd.domain.Tweet;
import ua.rd.ioc.Benchmark;
import ua.rd.ioc.Context;
import ua.rd.repository.TweetRepository;

import java.util.function.Supplier;

class PrototypeTweetServiceProxy implements TweetService,
        InitializingBean,
        DisposableBean,
        ApplicationContextAware {

    private Context context;
    private TweetService tweetService;
    private ApplicationContext applicationContext;

    public PrototypeTweetServiceProxy(TweetService tweetService, Context context) {
        this.context = context;
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweetService.allTweets();
    }

    @Override
    public TweetRepository getRepository() {
        return tweetService.getRepository();
    }

    @Override
    public Tweet newTweet() {
        return (Tweet) applicationContext.getBean("tweet");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("prop set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {

    }
}

@Service("tweetService")
public class SimpleTweetService implements TweetService {

    private TweetRepository tweetRepository;
    private Tweet tweet;

    @Required
    @Autowired
    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Autowired
    public SimpleTweetService(TweetRepository tweetRepository) {
        System.out.println("With param");
        System.out.println(tweetRepository);
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Iterable<Tweet> allTweets() {
        System.out.println(tweetRepository);
        return tweetRepository.allTweets();
    }

    @Override
    public TweetRepository getRepository() {
        return tweetRepository;
    }

    @Override
    @Lookup
    @Benchmark
    public Tweet newTweet() {
        return null;
    }
}
