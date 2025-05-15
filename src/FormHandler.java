import com.sun.net.httpserver.*;
import java.io.*;
import java.util.*;

public class FormHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        // Mengambil nama dari query string
        String query = exchange.getRequestURI().getQuery();
        String nama = query.split("=")[1];  // Mendapatkan nama dari query string

        // Membuat objek Peserta dan soal
        List<Character> grandSoal = SoalGenerator.generateRandomLetters();  // Contoh grand soal
        Peserta peserta = new Peserta(nama, grandSoal);
        Soal soal = new Soal(grandSoal);

        // Menyimpan peserta dan soal ke dalam map
        Main.pesertaMap.put(nama, peserta);
        Main.soalMap.put(nama, soal); 
//p
        // Menyusun HTML response
        String response = "<html><body>"
                + "<h1>Selamat datang, " + nama + "</h1>"
                + "<p>Nama telah diterima dan soal telah disiapkan. Silakan lanjut ke halaman soal.</p>"
                + "<a href='/soal?nama=" + nama + "'>Mulai Soal</a>"
                + "<br/><br/>"
                + "<a href='index.html'>Kembali ke halaman utama</a>"
                + "</body></html>";

        // Mengirim response
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

//q