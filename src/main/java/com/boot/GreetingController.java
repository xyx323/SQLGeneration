package com.boot;

/**
 * Created by Bruinx on 2017/11/29.
 */
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/greeting")
//        public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//            return new Greeting(counter.incrementAndGet(),
//                    String.format(template, name));
//    }
    @RequestMapping("/postgreeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }
    public void postgreeting(@RequestParam String name, @RequestParam String name1){

    }
}