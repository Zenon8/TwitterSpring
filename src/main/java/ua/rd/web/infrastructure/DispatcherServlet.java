package ua.rd.web.infrastructure;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DispatcherServlet extends HttpServlet {

    private AnnotationConfigApplicationContext webContext;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext rootContext = getRootContext();

        String contextConfigLocation = getInitParameter("contextConfigLocation");

        try {
            webContext = new AnnotationConfigApplicationContext(Class.forName(contextConfigLocation));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        webContext.setParent(rootContext);
        rootContext.register(webContext.getClass());
        webContext.refresh();


    }

    private AnnotationConfigApplicationContext getRootContext() {
        return (AnnotationConfigApplicationContext) getServletContext().getAttribute("rootContext");
    }

    @Override
    public void destroy() {
        webContext.close();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        MyController controller = null;
        res.setContentType("text/html");

        String beanName = beanNameByRequest(req);
        controller = (MyController) webContext.getBean(beanName);
        controller.handleRequest(req, res);
    }

    private String beanNameByRequest(HttpServletRequest req) {
        HandlerMapping handlerMapping =
                webContext.getBean(HandlerMapping.class);
        return handlerMapping.beanNameFromRequest(req);
    }
}
