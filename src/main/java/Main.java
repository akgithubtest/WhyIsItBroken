import Common.Broken;
import Common.Working;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        // @TODO get port from app config.
        int port = 8888;
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/broken", new BrokenHandler());
        httpServer.createContext("/working", new WorkingHandler());
        httpServer.setExecutor(null);
        httpServer.start();
    }

    static class BrokenHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange)
                throws IOException
        {
            try {
                Broken broken = new Broken();
                broken.age = 100;
                broken.id = 3;
                broken.name = "This doesn't work. Why?";

                ResponseHandler responseHandler = new ResponseHandler(broken);
                responseHandler.RespondBroken(httpExchange);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static class WorkingHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange)
                throws IOException
        {
            try {

                Working working = new Working();
                working.age = 100;
                working.id = 3;
                working.name = "This works";

                ResponseHandler responseHandler = new ResponseHandler(working);
                responseHandler.RespondWorking(httpExchange);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
