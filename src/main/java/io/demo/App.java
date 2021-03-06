package io.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
 
public class App {
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.startServer();
    }

    public void startServer() throws IOException {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8081);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                EntryPoint.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
            //Thread.sleep(1000*60*30);
            jettyServer.stop();
        }
        catch(Exception e)
        {
            FileWriter fw = new FileWriter ("exception.txt", true);
            PrintWriter pw = new PrintWriter (fw);
            e.printStackTrace (pw);
        }
        finally {
            jettyServer.destroy();
        }
    }
}
