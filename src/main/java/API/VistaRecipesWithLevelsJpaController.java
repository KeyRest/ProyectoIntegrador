/*
*Keiron Garro M
*C23212
*UCR
 */
 /*
*Keiron Garro M
*C23212
*UCR
 */
package API;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class VistaRecipesWithLevelsJpaController implements Serializable {

    public VistaRecipesWithLevelsJpaController() throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/api", (HttpHandler) new MyHandler());
        server.setExecutor(null);
        server.start();

    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            if (t.getRequestMethod().equalsIgnoreCase("GET")) {
                handleGetViewIngredient(t);
            } else {
                t.sendResponseHeaders(405, -1);
                t.close();
                return;
            }

        }//

        private void handleGetViewIngredient(HttpExchange exchange) throws IOException {

            Gson gson = new Gson();
            Type type = new TypeToken<List<VistaRecipesWithLevels>>() {
            }.getType();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIntegrador_jar_1.0");
            EntityManager em = emf.createEntityManager();
            List<VistaRecipesWithLevels> listView
                    = em.createQuery("SELECT v FROM VistaRecipesWithLevels v", VistaRecipesWithLevels.class).
                            getResultList();

            String response = gson.toJson(listView);
            System.out.println(response);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }

    }
}
