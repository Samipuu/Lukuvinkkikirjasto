package readingtips.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TestSystemCalls {

    public static void main(String[] args) {
        String komentoPolku = "/home/jussi/ohjeto_github/Reading-tips-2020-covid-edition/ReadingTips/src/test/java/readingtips/system";
        String mediaPolku = "/home/jussi/ohjeto_github/Reading-tips-2020-covid-edition/ReadingTips/downloaded_media_files";
        String komentoRiviYhtena = "/usr/bin/bash " + komentoPolku + "/play_some_videos.sh " + mediaPolku;
        String[] komentoRiviOsina = komentoRiviYhtena.split(" ", 0);
        List<String> komentoJaArgumentit = List.of(komentoRiviOsina);
        // String cmd = "D://script.bat" //for windows
        ProcessBuilder pb = new ProcessBuilder(komentoJaArgumentit);
        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            String result = builder.toString();
            System.out.print(result);
            System.out.println("end of script execution");
        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
    }

}
