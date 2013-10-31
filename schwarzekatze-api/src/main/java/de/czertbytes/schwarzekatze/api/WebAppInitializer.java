package de.czertbytes.schwarzekatze.api;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

        applicationContext.setServletContext(servletContext);
        applicationContext.scan("de.czertbytes.schwarzekatze");

        servletContext.addListener(new ContextLoaderListener(applicationContext));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("schwarzekatze-api", new DispatcherServlet(applicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }
}