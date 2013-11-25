package com.logicify.htb.configurator.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <p/>
 * User: Dmitry Berezovsky (LOGICIFY\corvis)
 * Date: 11/20/13
 * Time: 6:48 PM
 */
public class JettyServer {
    public static final String SERVLET_NAME_API = "api";
    private static final String WEB_APP_ROOT = "webapp";

    private static final Logger LOGGER = LoggerFactory.getLogger(JettyServer.class);
    private int port = 8080;
    private Server server;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public JettyServer(int port) {
        this.port = port;
    }

    public JettyServer() {
    }

    public void start(){
        server = new Server(port);
        server.setHandler( getServletHandler() );
        try {
            server.start();
        } catch (Exception e) {
            LOGGER.error("Failed to start server", e);
            throw new RuntimeException("Failed to start server", e);
        }
        LOGGER.info("Server started");

    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            LOGGER.error("Unable to stop the server", e);
        }
    }

    public void join() throws InterruptedException {
        server.join();
    }


    private ServletContextHandler getServletHandler() {
        ServletHolder mvcServletHolder = new ServletHolder(SERVLET_NAME_API, new DispatcherServlet());
        mvcServletHolder.setInitParameter("contextConfigLocation", "classpath:web-context.xml");

        // session has to be set, otherwise Jasper won't work
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.setAttribute("javax.servlet.context.tempdir", new File("../tmp/webapp"));
        // that classloader is requres to set JSP classpath. Without it you will just get NPE
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.addServlet(mvcServletHolder, "/*");
        //context.setResourceBase( getBaseUrl() );

        return context;
    }
}
