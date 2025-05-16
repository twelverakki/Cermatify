import java.util.*;

public class Soal {
    private List<Character> grandSoal;
    private String soalDenganHilang;
    private int posisiHilang;
    private char jawaban;
    private Map<Character, Integer> pilihanMap;

    public Soal(List<Character> grandSoal) {
        this.grandSoal           = new ArrayList<>(grandSoal);
        this.posisiHilang        = new Random().nextInt(5);
        this.jawaban             = grandSoal.get(posisiHilang);
        this.soalDenganHilang    = createSoalDenganHilang();
        this.pilihanMap          = createPilihanMap();
    }

    private String createSoalDenganHilang() {
        List<Character> soalList    = new ArrayList<>(grandSoal);
        StringBuilder soalBuilder   = new StringBuilder();

        Collections.shuffle(soalList);
        for (char c : soalList) {
            if (c == jawaban) {
                soalBuilder.append("?");
            } else {
                soalBuilder.append(c);
            }
        }

        return soalBuilder.toString();
    }

    private Map<Character, Integer> createPilihanMap() {
        Map<Character, Integer> map     = new HashMap<>();
        List<Character> soalList        = new ArrayList<>(grandSoal);

        Collections.shuffle(soalList);
        for (int i = 0; i < soalList.size(); i++) {
            char pilihan = (char) ('A' + i);
            map.put(pilihan, i);
        }
        return map;
    }

    public String getSoal() {
        StringBuilder soalBuilder = new StringBuilder();
        for (char huruf : grandSoal) {
            soalBuilder.append(huruf);
        }
        return soalBuilder.toString();
    }

    public String getSoalDenganHilang() {
        return soalDenganHilang;
    }

    public boolean cekJawaban(String jawabanUser) {
        char jawabanChar        = jawabanUser.charAt(0);
        Integer posisiJawaban   = pilihanMap.get(jawabanChar);
        
        return posisiJawaban != null && grandSoal.get(posisiJawaban) == jawaban;
    }
}
