package com.logicify.htb.configurator.web;

/**
 * <p/>
 * User: Dmitry Berezovsky (LOGICIFY\corvis)
 * Date: 11/20/13
 * Time: 5:57 PM
 */
public class EmbeddedServerEntryPoint {

    public static void main(String[] args) {
        JettyServer server = new JettyServer(8080);
        server.start();
        try {
            server.join();
        } catch (InterruptedException e) {
            server.stop();
        }
    }
}
