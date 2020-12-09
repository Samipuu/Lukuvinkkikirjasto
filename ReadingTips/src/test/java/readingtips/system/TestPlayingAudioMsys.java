package readingtips.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// tää ei tunnu toimivan kunnolla ainakaan vscodesta ajettuna. pitäis ilmeisesti ajaa oikeesta bash-konsolista.

public class TestPlayingAudioMsys {

    public static void main(String[] args) {

        TestPlayingAudioMsys taa = new TestPlayingAudioMsys();
        String mediaPolku = "downloaded_media_files/files";

        Long media1Position;
        {
            String mediaFile = "A Brief History of Time _ Audiobook _ Stephen Hawking.m4a";
            long sekunnit = 0;    
            media1Position = taa.playAudio(mediaPolku, mediaFile, sekunnit);
        }

        Long media2Position;
        {
            String mediaFile = "The Andromeda Strain by Michael Crichton _ Audiobook.m4a";
            long sekunnit = 0;    
            media2Position = taa.playAudio(mediaPolku, mediaFile, sekunnit);
        }        
        
        {
            String mediaFile = "A Brief History of Time _ Audiobook _ Stephen Hawking.m4a";
            media1Position = taa.playAudio(mediaPolku, mediaFile, media1Position);
        }
        
        {
            String mediaFile = "The Andromeda Strain by Michael Crichton _ Audiobook.m4a";
            media2Position = taa.playAudio(mediaPolku, mediaFile, media2Position);
        }        
    }

    private long playAudio(String mediaPath, String mediaFile, long seconds) {
        String komentoPolku = "command";
        String scripti = "play_audio_with_mpv.sh";
        // String[] komentoRiviOsina = new String[]{"/usr/bin/bash",komentoPolku + "/" + scripti, mediaPath + "/" + mediaFile, seconds+""};
        // List<String> komentoJaArgumentit = List.of(komentoRiviOsina);

        List<String> shinyCommand = new ArrayList<String>(Arrays.asList("C:\\msys64\\msys2_shell.cmd", 
            "-no-start", "-defterm", "-here"));
        shinyCommand.addAll(Arrays.asList(komentoPolku + "/" + scripti, mediaPath + "/" + mediaFile, seconds+""));

        ProcessBuilder pb = new ProcessBuilder(shinyCommand);
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
