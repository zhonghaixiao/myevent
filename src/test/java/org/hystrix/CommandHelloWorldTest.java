package org.hystrix;

import org.junit.Test;
import rx.Observable;

import java.util.concurrent.Future;

public class CommandHelloWorldTest {

    @Test
    public void run() throws Exception{
        String s = new CommandHelloWorld("Zhong").execute();
        System.out.println(s);
        Future<String> fs = new CommandHelloWorld("Hai").queue();
        System.out.println(fs.get());
        Observable<String> os = new CommandHelloWorld("xiao").observe();
        os.subscribe(v-> System.out.println(v));
        os.single();
    }
}