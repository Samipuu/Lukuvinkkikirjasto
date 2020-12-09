package readingtips.system.call;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public abstract class SystemCall {
    
    public String systemCall(List<String> commandLine) {
        ProcessBuilder pb = new ProcessBuilder(commandLine);
        String returnString;
        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            returnString = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnString;
    }

}
