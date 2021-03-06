package com.harium.suneidesis.chat.input;

import com.harium.suneidesis.chat.Interceptor;
import com.harium.suneidesis.chat.Parser;
import com.harium.suneidesis.chat.box.BaseChatBox;
import com.harium.suneidesis.chat.output.Output;
import com.harium.suneidesis.chat.output.TextOutput;

import java.util.Scanner;

public class Terminal extends BaseChatBox {

    private String language = "";
    private String userName = "";

    private Output output = new TextOutput();

    public Terminal() {
        super();
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (sc.hasNextLine()) {
                        String sentence = sc.nextLine();
                        input(sentence);
                        sc.reset();
                    }
                }
            }
        }).start();
    }

    protected void input(String sentence) {
        InputContext context = new InputContext();
        context.setSentence(sentence);
        enhanceInputContext(context);

        parseInput(context, output);
    }

    protected void enhanceInputContext(InputContext context) {
        // Custom Properties
        context.getProperties().put(InputContext.USER_USERNAME, getUsername());
        // Fill with the same data until we find a better way to get user's id
        context.getProperties().put(InputContext.USER_ID, getUserId());
        context.getProperties().put(InputContext.USER_NAME, getUserName());
        context.getProperties().put(InputContext.CHANNEL_ID, "");
        context.getProperties().put(InputContext.CHANNEL_NAME, "");
        context.getProperties().put(InputContext.LANGUAGE, getLanguage());
    }

    protected String getUserName() {
        if (userName != null && !userName.isEmpty()) {
            return userName;
        }
        return "";
    }

    protected String getUsername() {
        return getSystemProperty("user.name");
    }

    protected String getUserId() {
        return getUsername();
    }

    protected String getLanguage() {
        if (language != null && !language.isEmpty()) {
            return language;
        }
        return getSystemProperty("user.language");
    }

    private String getSystemProperty(String property) {
        String value = System.getProperty(property);
        if (value == null || value.trim().isEmpty()) {
            return "";
        }
        return value;
    }

    @Override
    public void addParser(Parser parser) {
        parsers.add(parser);
    }

    @Override
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    @Override
    public void sendMessage(String channel, String message) {
        output.print(message);
    }

    @Override
    public void setOutput(Output output) {
        this.output = output;
    }

    @Override
    public Output getOutput() {
        return output;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
