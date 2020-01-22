package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import mate.academy.internetshop.lib.Injector;

@WebListener
public class InjectorInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Dependencies wasn't injected");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
