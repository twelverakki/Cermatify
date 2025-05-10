import java.util.*;

public class Peserta {
    private String nama;
    private int poin;
    private int jumlahPengujian;
    private List<Character> grandSoal;  // Grand soal yang tetap
    private Soal soal;

    // Konstruktor yang menerima nama peserta dan grand soal untuk membuat soal pertama
    public Peserta(String nama, List<Character> grandSoal) {
        this.nama = nama;
        this.poin = 0;
        this.jumlahPengujian = 0;
        this.grandSoal = new ArrayList<>(grandSoal);  // Grand soal tetap
        this.soal = new Soal(grandSoal);  // Membuat soal pertama berdasarkan grand soal
    }

    public void tambahPoin() {
        this.poin += 5;
    }

    // Setiap kali soal dijawab, soal baru di-generate
    public void tambahJumlahPengujian() {
        this.jumlahPengujian++;
        this.soal = new Soal(new ArrayList<>(this.grandSoal));  // Soal baru yang dihasilkan dari grand soal yang sama
    }

    public boolean sudahSelesai() {
        return this.jumlahPengujian >= 4;
    }

    public String getNama() {
        return nama;
    }

    public int getPoin() {
        return poin;
    }

    public Soal getSoal() {
        return soal;
    }

    public int getJumlahPengujian() {
        return jumlahPengujian;
    }
}
