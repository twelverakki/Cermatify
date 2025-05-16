import com.sun.net.httpserver.*;
import java.io.*;

public class SoalHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String nama         = exchange.getRequestURI().getQuery().split("=")[1];
        Peserta peserta     = Main.pesertaMap.get(nama);
        Soal soal           = peserta.getSoal();

        if (peserta.sudahSelesai()) {
            String result = HTMLGenerator.headHTML + HTMLGenerator.hasilTes(peserta) + HTMLGenerator.closeHTML;

            exchange.sendResponseHeaders(200, result.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();

            return;
        }

        String response = HTMLGenerator.headHTML + HTMLGenerator.tampilanHalamanPengujian(peserta, soal) + HTMLGenerator.closeHTML;

        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
