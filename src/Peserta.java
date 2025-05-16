import java.util.*;

public class Peserta {
    private String nama;
    private int poin;
    private int jumlahPengujian;
    private List<Character> grandSoal;

    private Soal soal;

    public Peserta(String nama, List<Character> grandSoal) {
        this.nama = nama;
        this.poin = 0;
        this.jumlahPengujian = 0;
        this.grandSoal = new ArrayList<>(grandSoal);
        this.soal = new Soal(grandSoal);
    }

    public Peserta(String nama, int poin, int jumlahPengujian) {
        this.nama = nama;
        this.poin = poin;
        this.jumlahPengujian = jumlahPengujian;
    }

    public List<Character> getGrandSoal() {
        return grandSoal;
    }

    public void tambahPoin() {
        this.poin += 25;
    }

    public void tambahJumlahPengujian() {
        this.jumlahPengujian++;
        this.soal = new Soal(new ArrayList<>(this.grandSoal));
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
