import com.sun.net.httpserver.*;
import java.io.*;

public class SoalHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String nama = exchange.getRequestURI().getQuery().split("=")[1];
        Peserta peserta = Main.pesertaMap.get(nama);
        Soal soal = peserta.getSoal();  // Mengambil soal terbaru setiap kali

        // Mengecek apakah sudah selesai 20 pengujian
        if (peserta.sudahSelesai()) {
            String result = "<html><body><h1>Hasil Tes</h1>"
                    + "<p>Nama: " + peserta.getNama() + "</p>"
                    + "<p>Poin: " + peserta.getPoin() + "</p></body></html>";
            exchange.sendResponseHeaders(200, result.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();
            return;
        }

        // Mengambil soal dengan huruf yang sudah hilang
        String soalDenganHilang = soal.getSoalDenganHilang();

        // Menyajikan soal dan pilihan jawaban.
        String response = "<html><body>"
                + "<h1>Soal Pengujian " + (peserta.getJumlahPengujian() + 1) + "</h1>"
                + "<p>Soal: " + soal.getSoal() + "</p>"
                + "<p>Isi soal: " + soalDenganHilang + "</p>"
                + "<button type='button' onclick=\"location.href='/jawab_soal?nama=" + nama + "&jawaban=A'\">A</button>"
                + "<button type='button' onclick=\"location.href='/jawab_soal?nama=" + nama + "&jawaban=B'\">B</button>"
                + "<button type='button' onclick=\"location.href='/jawab_soal?nama=" + nama + "&jawaban=C'\">C</button>"
                + "<button type='button' onclick=\"location.href='/jawab_soal?nama=" + nama + "&jawaban=D'\">D</button>"
                + "<button type='button' onclick=\"location.href='/jawab_soal?nama=" + nama + "&jawaban=E'\">E</button>"
                + "</body></html>";

        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
