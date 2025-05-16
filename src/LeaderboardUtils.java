import com.sun.net.httpserver.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeaderboardUtils implements HttpHandler {
    public static void simpanLeaderboard(Peserta peserta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/leaderboard.csv", true))) {
            writer.write(peserta.getNama() + "," + peserta.getPoin() + "," + peserta.getJumlahPengujian());
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Peserta> getTop10Peserta(String fileName) {
        List<Peserta> pesertaList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String nama = data[0].toLowerCase();
                int poin = Integer.parseInt(data[1]);
                int jumlahPengujian = Integer.parseInt(data[2]);

                pesertaList.add(new Peserta(nama, poin, jumlahPengujian));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Peserta> pesertaMap = new HashMap<>();

        for (Peserta peserta : pesertaList) {
            if (!pesertaMap.containsKey(peserta.getNama()) || pesertaMap.get(peserta.getNama()).getPoin() < peserta.getPoin()) {
                pesertaMap.put(peserta.getNama(), peserta);
            }
        }

        List<Peserta> topPeserta = pesertaMap.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getPoin(), p1.getPoin()))
                .limit(10)
                .collect(Collectors.toList());

        while (topPeserta.size() < 10) {
            topPeserta.add(new Peserta("unknown", 0, 0));
        }

        return topPeserta;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<Peserta> top10Peserta   = LeaderboardUtils.getTop10Peserta("data/leaderboard.csv");
        String query                 = exchange.getRequestURI().getQuery();
        String[] params              = query.split("&");
        String nama                  = params[0].split("=")[1];
        StringBuilder result         = new StringBuilder();

        result.append(HTMLGenerator.headHTML);
        result.append(HTMLGenerator.top10Peserta(top10Peserta, nama));
        result.append(HTMLGenerator.closeHTML);

        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, result.toString().getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(result.toString().getBytes());
        os.close();
    }
}
