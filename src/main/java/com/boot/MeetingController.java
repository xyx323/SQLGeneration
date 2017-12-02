package com.boot;

/**
 * Created by Bruinx on 2017/11/30.
 */
import java.util.concurrent.atomic.AtomicLong;

import com.entity.Object;
import com.entity.Universe;
import com.repository.ObjectRepository;
import com.repository.UniverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MeetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/meeting")
    public Meeting meeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Meeting(counter.incrementAndGet(),
                String.format(template, name));
    }
    @RequestMapping("/test")
    public String test(){
        return "test ok";
    }
    @Autowired
    private UniverseRepository universeRepository;
    @RequestMapping("/testUniverse")
    @ResponseBody
    public Universe testUniverse(){
        Universe universe = universeRepository.findOne(1);
        System.out.println(universeRepository);
        System.out.println(universe);
        return universe;
    }
}
