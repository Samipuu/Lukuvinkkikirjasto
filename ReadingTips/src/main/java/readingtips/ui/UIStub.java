package readingtips.ui;
import readingtips.ui.UI;
import java.util.ArrayList;

public class UIStub implements IO {

    String[] inputs;
    int command;
    ArrayList<String> outputs;

    public UIStub(String... inputs) {
        this.command = 0;
        this.inputs = inputs;
        this.outputs = new ArrayList<String>();
    }

    // public String add() {
    //     return("Add");
    // }

    @Override
    public String nextLine() {
        return inputs[command++];
    }

    @Override
    public void print(String m) {
        outputs.add(m);
    }
    
    public ArrayList<String> getOutputs() {
        return outputs;
    }
}
