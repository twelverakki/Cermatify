import com.sun.net.httpserver.*;
import java.io.*;
import java.util.*;

public class JawabSoalHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        // Mendapatkan parameter nama dan jawaban
        String query = exchange.getRequestURI().getQuery();
        String[] params = query.split("&");
        String nama = params[0].split("=")[1]; // nama peserta
        String jawaban = params[1].split("=")[1]; // jawaban yang dipilih peserta

        // Mendapatkan peserta dan soal dari map
        Peserta peserta = Main.pesertaMap.get(nama);
        Soal soal = peserta.getSoal();

        // Cek jawaban dan tambah poin jika benar
        if (soal.cekJawaban(jawaban)) {
            peserta.tambahPoin(); // Tambah poin jika jawaban benar
        }

        // Menambah jumlah pengujian
        peserta.tambahJumlahPengujian();

        // Jika sudah selesai 20 pengujian, tampilkan hasil tes
        if (peserta.sudahSelesai()) {
            // Menyimpan hasil tes ke database atau file, atau simpan ke map
            String result = "<html><body><h1>Hasil Tes</h1>"
                    + "<p>Nama: " + peserta.getNama() + "</p>"
                    + "<p>Poin: " + peserta.getPoin() + "</p>"
                    + "<p>Jumlah Pengujian: " + peserta.getJumlahPengujian() + "</p>";

            // Menyimpan hasil tes ke map atau database
            Main.hasilTesMap.put(nama, peserta);

            // Tampilkan leaderboard (5 peserta teratas)
            result += "<h2>Leaderboard</h2><ol>";
            int count = 0;
            for (Map.Entry<String, Peserta> entry : Main.hasilTesMap.entrySet()) {
                if (count >= 5)
                    break; // Tampilkan hanya 5 besar
                result += "<li>" + entry.getKey() + " - " + entry.getValue().getPoin() + " Poin</li>";
                count++;
            }
            result += "</ol>";
            result += "<p><button onclick=\"location.href='/submit_nama?nama=" + nama
                    + "'\">Kembali ke Halaman Awal</button></p>";
            result += "</body></html>";

            // Kirimkan response dengan hasil test
            exchange.getResponseHeaders().set("Location", "/soal?nama=" + nama);
            exchange.sendResponseHeaders(200, result.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();
        } else {
            // Jika belum selesai, lanjutkan ke soal berikutnya
            exchange.getResponseHeaders().set("Location", "/soal?nama=" + nama);
            exchange.sendResponseHeaders(302, -1); // HTTP redirect ke soal berikutnya
        }
    }
}
