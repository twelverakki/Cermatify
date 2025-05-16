import java.util.ArrayList;
import java.util.List;

public class HTMLGenerator {
    public static String headHTML = "<!DOCTYPE html>\n" 
                                    + "<html lang=\"id\">\n" 
                                    + "<head>\n" 
                                    + "    <meta charset=\"UTF-8\">\n" 
                                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" 
                                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" 
                                    + "    <title>Cermatify</title>\n" 
                                    + "    <link rel=\"stylesheet\" href=\"style.css\">\n" 
                                    + "    <meta name=\"description\" content=\"Platform belajar dan ujian online Cermatify\">\n" 
                                    + "    <meta name=\"keywords\" content=\"belajar, ujian, online, cermatify\">\n" 
                                    + "    <meta name=\"author\" content=\"Cermatify Team\">\n" 
                                    + "</head>\n<body class=\"gradient-background index-page\">\n";

    public static String closeHTML = "</body></html>";

    public static String responForm(Peserta peserta) {
        String result = ""
            + " <h1 class=\"title tracking-in-expand\">Selamat datang, "+ peserta.getNama() +"</h1>"
            + " <img src=\"wave.gif\" alt=\"Animasi Selamat Datang\" class=\"gif\" width=\"150\" height=\"150\"  loading=\"lazy\">"
            + " <p class=\"text\">Nama telah diterima dan soal telah disiapkan. Silakan lanjut ke halaman soal.</p><br/><br/>"
            + " <div class=\"action-buttons\">"
            + "     <a href=\"/soal?nama="+ peserta.getNama() +"\" class=\"link\">Mulai Soal</a>"
            + "     <a href=\"index.html\" class=\"link\">Kembali ke halaman utama</a>"
            + " </div>";

        return result;
    }

    public static String tampilanGrandSoal(List<Character> grandSoal) {
        String result = "<div class=\"question-container\">\n"
            + "<div class=\"answer-options\">\n"
            + "        <div class=\"option\">\n"
            + "            <span>" +grandSoal.get(0)+ "</span>\n"
            + "            <span>A</span>\n"
            + "        </div>\n"
            + "        <div class=\"option\">\n"
            + "            <span>" +grandSoal.get(1)+ "</span>\n"
            + "            <span>B</span>\n"
            + "        </div>\n"
            + "        <div class=\"option\">\n"
            + "            <span>" +grandSoal.get(2)+ "</span>\n"
            + "            <span>C</span>\n"
            + "        </div>\n"
            + "        <div class=\"option\">\n"
            + "            <span>" +grandSoal.get(3)+ "</span>\n"
            + "            <span>D</span>\n"
            + "        </div>\n"
            + "        <div class=\"option\">\n"
            + "            <span>" +grandSoal.get(4)+ "</span>\n"
            + "            <span>E</span>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "</div>\n";
        return result;
    }

    public static String tampilanSoal(String Soal) {
        List<Character> resultList = new ArrayList<>();
        for (int i = 0; i < Soal.length(); i++) {
            resultList.add(Soal.charAt(i));
        }

        String result = "<div class=\"question-container_2\">\n"
        + "<div class=\"answer-options_2\">\n"
        + "        <div class=\"soal\">\n"
        + "            <span>" +resultList.get(0)+ "</span>\n"
        + "        </div>\n"
        + "        <div class=\"soal\">\n"
        + "            <span>" +resultList.get(1)+ "</span>\n"
        + "        </div>\n"
        + "        <div class=\"soal\">\n"
        + "            <span>" +resultList.get(2)+ "</span>\n"
        + "        </div>\n"
        + "        <div class=\"soal\">\n"
        + "            <span>" +resultList.get(3)+ "</span>\n"
        + "        </div>\n"
        + "        <div class=\"soal\">\n"
        + "            <span>" +resultList.get(4)+ "</span>\n"
        + "        </div>\n"
        + "    </div>\n"
        + "</div>\n";

        return result;
    }

    public static String tampilanHalamanPengujian(Peserta peserta, Soal soal) {
        String result = "<h1  class=\"title tracking-in-expand\">Soal Pengujian " + (peserta.getJumlahPengujian() + 1) + "</h1>"
                + HTMLGenerator.tampilanGrandSoal(peserta.getGrandSoal())
                + "<p class=\"text\">Pilih jawaban yang benar dari soal berikut</p>"
                + HTMLGenerator.tampilanSoal(soal.getSoalDenganHilang())
                + "<div class=\"choices\">"
                + "     <button class='choice' type='button' onclick=\"location.href='/jawab_soal?nama=" + peserta.getNama() + "&jawaban=A'\">A</button>"
                + "     <button class='choice' type='button' onclick=\"location.href='/jawab_soal?nama=" + peserta.getNama() + "&jawaban=B'\">B</button>"
                + "     <button class='choice' type='button' onclick=\"location.href='/jawab_soal?nama=" + peserta.getNama() + "&jawaban=C'\">C</button>"
                + "     <button class='choice' type='button' onclick=\"location.href='/jawab_soal?nama=" + peserta.getNama() + "&jawaban=D'\">D</button>"
                + "     <button class='choice' type='button' onclick=\"location.href='/jawab_soal?nama=" + peserta.getNama() + "&jawaban=E'\">E</button>"
                + "</div>";
        return result;
    }

    public static String hasilTes(Peserta peserta) {
        String result = ""
            + " <div class=\"result-card\">"
            + "     <h1 class=\"title tracking-in-expand\">Hasil Tes Anda</h1>"
            + "     <div class=\"result-info\">"
            + "         <div class=\"result-item\">"
            + "             <span class=\"result-label\">Nama:</span>"
            + "             <span class=\"result-value\">" + peserta.getNama() +"</span>"
            + "         </div>"
            + "         <div class=\"result-item\">"
            + "             <span class=\"result-label\">Poin:</span>"
            + "             <span class=\"result-value\">"+ peserta.getPoin() +"</span>"
            + "         </div>"
            + "         <div class=\"result-item\">"
            + "             <span class=\"result-label\">Jumlah Tes:</span>"
            + "             <span class=\"result-value\">"+ peserta.getJumlahPengujian() +"</span>"
            + "         </div>"
            + "     </div>"
            + " </div>"
            + " <div class=\"action-buttons\">"
            + "     <button class=\"button\" onclick=\"location.href='leaderboard?nama="+ peserta.getNama() +"'\">Lihat Leaderboard</button>"
            + "     <button class=\"button\" onclick=\"location.href='/submit_nama?nama="+ peserta.getNama() +"'\">Kembali ke Beranda</button>"
            + " </div>";

        return result;
    }

    public static String top10Peserta(List<Peserta> top10Peserta, String nama) {
        String top4to10 = "";
        for (int i = 3; i < top10Peserta.size(); i++) {
            Peserta peserta = top10Peserta.get(i);
            top4to10 += "         <li><span class=\"rank-name\">" + peserta.getNama() + "</span> <span class=\"rank-points\">" + peserta.getPoin() + " Poin</span></li>";
        }

        String result = "<h1 class=\"title tracking-in-expand\">Leaderboard</h1>"
            + "<div class=\"podium-container tracking-in-expand\">"
            + "     <div class=\"podium silver\">"
            + "         <div class=\"podium-rank\">2</div> <!-- silver -->"
            + "         <div class=\"podium-name\">"+ top10Peserta.get(1).getNama() + "</div>"
            + "         <div class=\"podium-points\">"+ top10Peserta.get(1).getPoin() + "</div>"
            + "     </div>"
            + "     <div class=\"podium gold\">"
            + "         <div class=\"crown\">👑</div>"
            + "         <div class=\"podium-rank\">1</div> <!-- gold -->"
            + "         <div class=\"podium-name\">"+ top10Peserta.get(0).getNama() + "</div>"
            + "         <div class=\"podium-points\">"+ top10Peserta.get(0).getPoin() + "</div>"
            + "     </div>"
            + "     <div class=\"podium bronze\">"
            + "         <div class=\"podium-rank\">3</div> <!-- bronze -->"
            + "         <div class=\"podium-name\">"+ top10Peserta.get(2).getNama() + "</div>"
            + "         <div class=\"podium-points\">"+ top10Peserta.get(2).getPoin() + "</div>"
            + "     </div>"
            + "</div>"
            + "<div class=\"leaderboard-list tracking-in-expand\">"
            + "     <ol start=\"4\">"
            + top4to10
            + "     </ol>"
            + " </div>"
            + "<div class=\"action-buttons\">"
            + "    <button class=\"button\" onclick=\"location.href='/submit_nama?nama="+ nama +"'\">Kembali ke Beranda</button>"
            + "</div>"
            ;

        return result;
    }


}
