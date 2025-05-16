import com.sun.net.httpserver.*;
import java.io.*;
import java.util.*;

public class FormHandler extends SoalGenerator implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        List<Character> grandSoal   = generateRandomLetters();
        String query                = exchange.getRequestURI().getQuery();
        String nama                 = query.split("=")[1];
        Peserta peserta             = new Peserta(nama, grandSoal);
        Soal soal                   = new Soal(grandSoal);

        Main.pesertaMap.put(nama, peserta);
        Main.soalMap.put(nama, soal);

        String response = HTMLGenerator.headHTML + HTMLGenerator.responForm(peserta) + HTMLGenerator.closeHTML;

        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @Override
    public List<Character> generateRandomLetters() {
        List<Character> alphabet = new ArrayList<>();

        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }

        Collections.shuffle(alphabet);

        return alphabet.subList(0, 5);
    }
}
