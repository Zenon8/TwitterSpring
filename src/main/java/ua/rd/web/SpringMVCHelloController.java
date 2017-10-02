package ua.rd.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ua.rd.domain.Tweet;
import ua.rd.services.TweetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class SpringMVCHelloController implements Controller {

    private TweetService tweetService;

    public SpringMVCHelloController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    public SpringMVCHelloController() {
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setContentType("text/html");
        try (PrintWriter writer = httpServletResponse.getWriter()) {
            writer.print("Hello from Spring MVC app <br />");
            writer.print(Thread.currentThread());
        }

        for (Tweet tweet : tweetService.allTweets()) {

        }
        return null;
    }
}
