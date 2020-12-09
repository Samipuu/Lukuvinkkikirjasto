package readingtips.system.call;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WindowsMsysCall extends SystemCall {

    public String systemCall(List<String> bashScriptCommandLine) {
        List<String> msysCommand = new ArrayList<String>(
                Arrays.asList(
                    "C:\\msys64\\msys2_shell.cmd", 
                    "-no-start", 
                    "-defterm", 
                    "-here"));
        msysCommand.addAll(bashScriptCommandLine);
        return super.systemCall(msysCommand);
    }

}
