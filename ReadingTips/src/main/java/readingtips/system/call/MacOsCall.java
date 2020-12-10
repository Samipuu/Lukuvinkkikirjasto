package readingtips.system.call;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MacOsCall extends SystemCall {

    public String systemCall(List<String> bashScriptCommandLine) {
        List<String> msysCommand = new ArrayList<String>(
                Arrays.asList(
                    "/bin/bash"));
        msysCommand.addAll(bashScriptCommandLine);
        return super.systemCall(msysCommand);
    }

}
