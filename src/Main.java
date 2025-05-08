import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import java.nio.file.*;

public class Main {
    public static Map<String, Peserta> pesertaMap = new HashMap<>();  // Menyimpan peserta
    public static Map<String, Soal> soalMap = new HashMap<>();        // Menyimpan soal untuk tiap peserta
    public static Map<String, Peserta> hasilTesMap = new TreeMap<>(); // Menyimpan hasil tes peserta

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Menyajikan halaman utama (index.html)
        server.createContext("/", new StaticHandler("public"));
        // Menangani pengiriman nama peserta
        server.createContext("/submit_nama", new FormHandler());
        // Menangani soal dinamis yang ditampilkan
        server.createContext("/soal", new SoalHandler());
        // Menangani jawaban peserta
        server.createContext("/jawab_soal", new JawabSoalHandler());

        // Menjalankan server
        server.setExecutor(null);
        server.start();
        System.out.println("Server aktif di http://localhost:8080");
    }

    // Menyajikan file statis seperti index.html dan lainnya
    static class StaticHandler implements HttpHandler {
        private final String baseDir;

        public StaticHandler(String baseDir) {
            this.baseDir = baseDir;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/")) path = "/index.html";  // default ke index.html
            File file = new File(baseDir + path);
            if (!file.exists() || file.isDirectory()) {
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
            return "application/octet-stream";
        }
    }
}
