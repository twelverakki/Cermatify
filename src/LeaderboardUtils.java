import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LeaderboardUtils {

    // Fungsi untuk membaca leaderboard dari file CSV
    public static Map<String, Peserta> bacaLeaderboard() {
        Map<String, Peserta> leaderboardMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/leaderboard.csv"))) { // Path ke file CSV
            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String nama = data[0];
                int poin = Integer.parseInt(data[1]);
                int jumlahPengujian = Integer.parseInt(data[2]);

                Peserta peserta = new Peserta(nama, poin, jumlahPengujian);
                leaderboardMap.put(nama, peserta);
            }

            System.out.println("Leaderboard berhasil dibaca dari data/leaderboard.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return leaderboardMap;
    }


    // Fungsi untuk menyimpan data leaderboard
    public static void simpanLeaderboard(Map<String, Peserta> pesertaMap) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/leaderboard.csv"))) { // Path ke file CSV
        writer.write("nama,poin,jumlahPengujian\n");

        for (Peserta peserta : pesertaMap.values()) {
            writer.write(peserta.getNama() + "," + peserta.getPoin() + "," + peserta.getJumlahPengujian() + "\n");
        }

        System.out.println("Leaderboard berhasil disimpan ke data/leaderboard.csv");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
