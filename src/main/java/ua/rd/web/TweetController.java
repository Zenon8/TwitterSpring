package ua.rd.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.rd.domain.Tweet;

@Controller
public class TweetController {

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello from TweetController";
    }

    @GetMapping("/tweet")
    public String tweet(Model model) {
        Tweet tweet = new Tweet("Some text", null);
        model.addAttribute(tweet);
        return "tweet";
    }

    @PostMapping("/newtweet")
    public String tweet(@RequestParam("tweetText") String tweetText, Model model) {
        Tweet tweet = new Tweet("Some text", null);
        model.addAttribute(tweet);
        return "tweet";
    }
}
