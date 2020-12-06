package readingtips.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TestGetMediaLength {

    public static void main(String[] args) {
        String media = "downloaded_media_files/files/" + "Indochine - Un été français (Clip officiel).mp4";
        
        TestGetMediaLength taa = new TestGetMediaLength();

        System.out.println(taa.getMediaLength(media));
    }

    private long getMediaLength(String media) {
           
        String komentoPolku = "command";
        String scripti = "get_media_length.sh";
        String[] komentoRiviOsina = new String[]{"/usr/bin/bash",komentoPolku + "/" + scripti, media};
        List<String> komentoJaArgumentit = List.of(komentoRiviOsina);
        ProcessBuilder pb = new ProcessBuilder(komentoJaArgumentit);
        String sekunnit;
        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String result = builder.toString();
            sekunnit = result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long returnValue = Long.parseLong(sekunnit);
        return returnValue;
    }

}
