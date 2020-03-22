package com.harium.suneidesis.chat.box;

import com.harium.suneidesis.chat.instance.Instance;
import com.harium.suneidesis.chat.output.Output;
import com.harium.suneidesis.chat.output.OutputContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyBoxTest {

    private DummyBox box;

    @Before
    public void setUp() {
        box = new DummyBox();
    }

    @Test
    public void testAnswers() {
        Instance instance = null;

        Out output = new Out();

        box.input("How are you?", instance, output);
        Assert.assertEquals("", output.answer);

        box.input("What's your name?", instance, output);
        Assert.assertEquals("", output.answer);

        box.input("Tell me more about the Theory of Relativity", instance, output);
        Assert.assertEquals("", output.answer);

        box.input("Thank you for listen to me", instance, output);
        Assert.assertEquals("", output.answer);
    }

    class Out implements Output {
        public String answer = "";
        @Override
        public void print(String sentence, OutputContext context) {
            this.answer = sentence;
        }

        @Override
        public void produceFile(String path, String description) {
            this.answer = path;
        }
    }
}