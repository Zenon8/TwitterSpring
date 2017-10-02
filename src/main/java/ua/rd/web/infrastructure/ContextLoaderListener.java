package ua.rd.web.infrastructure;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String contextConfigLocation
                = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
        String[] contextConfigLocations = contextConfigLocation.split(" ");

        AnnotationConfigApplicationContext rootContext
                = createRootContexts(contextConfigLocations);
        servletContextEvent.getServletContext().setAttribute("rootContext", rootContext);
    }

    private AnnotationConfigApplicationContext createRootContexts(String[] contextConfigLocation) {
        AnnotationConfigApplicationContext baseContext =
                new AnnotationConfigApplicationContext(
                        getContextClass(contextConfigLocation[0])
                );

        if (contextConfigLocation.length == 1) {
            return baseContext;
        } else {
            for (int i = 0; i < contextConfigLocation.length; i++) {
                AnnotationConfigApplicationContext context =
                        new AnnotationConfigApplicationContext(
                                getContextClass(contextConfigLocation[i])
                        );
                context.setParent(baseContext);
                context.register(getContextClass(contextConfigLocation[i]));
                context.refresh();
                baseContext = context;

            }
        }


        return baseContext;

    }

    private Class<?> getContextClass(String s) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aClass;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
