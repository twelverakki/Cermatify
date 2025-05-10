import java.util.*;

public class SoalGenerator {
    public static List<Character> generateRandomLetters() {
        List<Character> alphabet = new ArrayList<>();

        // Menambahkan semua huruf A-Z (kapital) ke dalam list
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }

        // Mengacak huruf-huruf di dalam list
        Collections.shuffle(alphabet);

        // Mengambil 5 huruf pertama setelah diacak
        return alphabet.subList(0, 5);
    }

    public static void main(String[] args) {
        // Menghasilkan 5 huruf acak tanpa duplikat
        List<Character> grandSoal = generateRandomLetters();

        System.out.println(grandSoal);  // Menampilkan grand soal yang dihasilkan
    }
}
