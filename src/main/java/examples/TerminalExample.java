package examples;

import com.harium.suneidesis.chat.Parser;
import com.harium.suneidesis.chat.input.InputContext;
import com.harium.suneidesis.chat.input.Terminal;
import com.harium.suneidesis.chat.output.Output;

public class TerminalExample {

    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        terminal.addParser(new Parser() {
            @Override
            public boolean parse(InputContext context, Output output) {
                String sentence = context.getSentence();
                output.print("Received: " + sentence);
                return true;
            }
        });
        terminal.init();
    }

}
