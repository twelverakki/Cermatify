import com.sun.net.httpserver.*;
import java.io.*;

public class JawabSoalHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String query        = exchange.getRequestURI().getQuery();
        String[] params     = query.split("&");
        String nama         = params[0].split("=")[1];
        String jawaban      = params[1].split("=")[1];
        Peserta peserta     = Main.pesertaMap.get(nama);
        Soal soal           = peserta.getSoal();

        if (soal.cekJawaban(jawaban)) {
            peserta.tambahPoin();
        }

        peserta.tambahJumlahPengujian();

        if (peserta.sudahSelesai()) {
            LeaderboardUtils.simpanLeaderboard(peserta);

            String result = HTMLGenerator.headHTML + HTMLGenerator.hasilTes(peserta) + HTMLGenerator.closeHTML;

            exchange.getResponseHeaders().set("Location", "/soal?nama=" + nama);
            exchange.sendResponseHeaders(200, result.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();
        } else {
            exchange.getResponseHeaders().set("Location", "/soal?nama=" + nama);
            exchange.sendResponseHeaders(302, -1);
        }
    }
}
