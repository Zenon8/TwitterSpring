package ua.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.rd.domain.Tweet;
import ua.rd.repository.TweetRepository;

import java.util.List;

@Controller
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        System.out.println("[LOGGER] TweetController:welcome()");
        return "Hello from TweetController";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        System.out.println("[LOGGER] TweetController:hello()");
        return "hello";
    }

    @GetMapping("/tweet")
    public String tweet(Model model) {
        System.out.println("[LOGGER] TweetController:tweet()");

        Tweet tweet = new Tweet("Some text", null);
        model.addAttribute(tweet);
        return "tweet";
    }

    @PostMapping("/tweetList")
    public String tweetList(Model model) {
        System.out.println("[LOGGER] TweetController::tweetList()");
        model.addAttribute("tweetList", tweetRepository.allTweets());
        return "tweet";
    }

    @PostMapping("/newtweet")
    public String newtweet(@RequestParam("tweetText") String tweetText, Model model) {
        System.out.println("[LOGGER] TweetController::newtweet()");
        System.out.println("[LOGGER] " + tweetText);

        Tweet tweet = new Tweet(tweetText, null);
        tweetRepository.addTweet(tweet);
        model.addAttribute("newtweet", tweet);
        return "tweet";
    }

    @GetMapping("/editTweet")
    public String editTweet(@RequestParam("id") int id, Model model) {
        System.out.println("[LOGGER] TweetController:editTweet()");


        Tweet tweet = new Tweet("Some text", null);
        model.addAttribute(tweet);
        return "tweet";
    }
}
