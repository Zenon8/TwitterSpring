package ua.rd.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import ua.rd.RepositoryConfig;
import ua.rd.ServiceConfig;
import ua.rd.repository.TweetRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextHierarchy({
        @ContextConfiguration(locations = "classpath:repoContext.xml"),
        @ContextConfiguration(classes = ServiceConfig.class)
        })
@ActiveProfiles({"dev", "test"})
public class SimpleTweetServiceTest {

    @Autowired
    private TweetService tweetService;

    public SimpleTweetServiceTest() {
        System.out.println("test created");
        //throw new RuntimeException();
    }

    @Test
    public void newTweetIsNotNull() throws Exception {
        //TweetRepository tweetRepository = mock(TweetRepository.class);
        //TweetService tweetService = new SimpleTweetService(tweetRepository);
        System.out.println(tweetService.newTweet());
        assertNotNull(tweetService.newTweet());
    }

    @Test
    public void newTweetTweetsAreNotEqual() throws Exception {
        //TweetRepository tweetRepository = mock(TweetRepository.class);
        //TweetService tweetService = new SimpleTweetService(tweetRepository);
        assertNotSame(tweetService.newTweet(), tweetService.newTweet());
    }

}