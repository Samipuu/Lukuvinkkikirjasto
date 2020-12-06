package readingtips.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TestPlayingVideo {

    public static void main(String[] args) {

        TestPlayingVideo taa = new TestPlayingVideo();
        String mediaPolku = "downloaded_media_files/files";

        Long media1Position;
        {
            String mediaFile = "Indochine - Un été français (Clip officiel).mp4";
            long sekunnit = 0;    
            media1Position = taa.playVideo(mediaPolku, mediaFile, sekunnit);
            System.out.println("medi1Position: " + media1Position);
        }
        
        Long media2Position;
        {
            String mediaFile = "Joe Rogan Experience #1531 - Miley Cyrus.mp4";
            long sekunnit = 0;    
            media2Position = taa.playVideo(mediaPolku, mediaFile, sekunnit);
            System.out.println("medi2Position: " + media2Position);
        }        
        
        {
            String mediaFile = "Indochine - Un été français (Clip officiel).mp4";
            media1Position = taa.playVideo(mediaPolku, mediaFile, media1Position);
            System.out.println("medi1Position: " + media1Position);
        }
        
        {
            String mediaFile = "Joe Rogan Experience #1531 - Miley Cyrus.mp4";
            media2Position = taa.playVideo(mediaPolku, mediaFile, media2Position);
            System.out.println("medi2Position: " + media2Position);
        }        
    }

    private long playVideo(String mediaPath, String mediaFile, long seconds) {
        String komentoPolku = "command";
        String scripti = "play_video_with_mpv.sh";
        String[] komentoRiviOsina = new String[]{"/usr/bin/bash",komentoPolku + "/" + scripti, mediaPath + "/" + mediaFile, seconds+""};
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
        return Long.parseLong(sekunnit);
    }

}
