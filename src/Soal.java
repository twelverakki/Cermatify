import java.util.*;

public class Soal {
    private List<Character> grandSoal;  // Grand soal yang terdiri dari 5 huruf tetap
    private String soalDenganHilang;
    private int posisiHilang;

    // Konstruktor yang menghasilkan soal dengan 5 huruf acak dan satu huruf hilang
    public Soal(List<Character> grandSoal) {
        this.grandSoal = new ArrayList<>(grandSoal);  // Salin grand soal ke dalam list
        this.posisiHilang = new Random().nextInt(5);  // Pilih posisi huruf yang hilang secara acak
        this.soalDenganHilang = createSoalDenganHilang();
    }

    // Membuat soal dengan satu huruf yang hilang
    private String createSoalDenganHilang() {
        Collections.shuffle(grandSoal);  // Acak urutan huruf
        StringBuilder soalBuilder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            if (i == posisiHilang) {
                soalBuilder.append("_");  // Menyembunyikan huruf yang hilang
            } else {
                soalBuilder.append(grandSoal.get(i));
            }
        }
        return soalBuilder.toString();
    }

    // Mendapatkan soal asli (5 huruf acak dari grand soal)
    public String getSoal() {
        StringBuilder soalBuilder = new StringBuilder();

        for (char huruf : grandSoal) {
            soalBuilder.append(huruf);
        }
        return soalBuilder.toString();
    }

    // Mendapatkan soal dengan huruf yang hilang
    public String getSoalDenganHilang() {
        return soalDenganHilang;
    }

    // // Mendapatkan posisi huruf yang hilang
    // public int getPosisiHilang() {
    //     return posisiHilang;
    // }

    // Mengecek apakah jawaban yang diberikan benar
    public boolean cekJawaban(String jawaban) {
        // Jawaban yang benar adalah huruf yang hilang berdasarkan posisi yang benar
        switch (posisiHilang) {
            case 0: return "A".equals(jawaban);  // Grand soal: U
            case 1: return "B".equals(jawaban);  // Grand soal: K
            case 2: return "C".equals(jawaban);  // Grand soal: W
            case 3: return "D".equals(jawaban);  // Grand soal: E
            case 4: return "E".equals(jawaban);  // Grand soal: L
            default: return false;
        }
    }
}
