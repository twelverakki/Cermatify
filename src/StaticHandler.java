import com.sun.net.httpserver.*;
import java.io.*;
import java.nio.file.*;

public class StaticHandler implements HttpHandler {
    private final String baseDir;

    public StaticHandler(String baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if (path.equals("/")) path = "/index.html";  // Default ke index.html jika root

        // Debug: Mencetak path yang diminta
        System.out.println("Request Path: " + path);
        System.out.println("Base Directory: " + baseDir);  // Debug baseDir

        File file = new File(baseDir + path);  // Gabungkan baseDir dengan path
        if (!file.exists() || file.isDirectory()) {
            System.out.println("File not found: " + baseDir + path);  // Debug: file tidak ditemukan
            exchange.sendResponseHeaders(404, 0);
            exchange.getResponseBody().write("404 Not Found".getBytes());
            exchange.close();
            return;
        }

        byte[] content = Files.readAllBytes(file.toPath());
        exchange.getResponseHeaders().add("Content-Type", guessContentType(path));
        exchange.sendResponseHeaders(200, content.length);
        exchange.getResponseBody().write(content);
        exchange.close();
    }

    private String guessContentType(String path) {
        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".png")) return "image/png";
        return "application/octet-stream";  // Defaultnya untuk file lain
    }
}
