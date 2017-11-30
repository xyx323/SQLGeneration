package hello;

/**
 * Created by Bruinx on 2017/11/30.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
