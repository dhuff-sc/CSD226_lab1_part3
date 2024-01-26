package com.example.FredCarellaCsd226HtmxTutorial;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import org.springframework.web.reactive.result.view.Rendering;


//@SpringBootApplication
@RestController
public class GreetController {

    @GetMapping("/user")
    public String user() {
        return "Fred";
    }

    @PostMapping("/greet")
    public String greet(@ModelAttribute Greeting values) {
        return "Hello " + values.getValue() + "!";
    }

    @GetMapping(path = "/stream2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Rendering> stream2() {
        return Flux.interval(Duration.ofSeconds(5)).map(value -> Rendering.view("time")
                .modelAttribute("value", value)
                .modelAttribute("time", System.currentTimeMillis()).build());
    }


    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(5)).map(
                value -> value + ":" + System.currentTimeMillis()
        );
    }


    @GetMapping(path = "/test")
    public String test() {
        return "<div id=\"hello\" hx-swap-oob=\"true\">Hello</div>\n"
                + "<div id=\"world\" hx-swap-oob=\"true\">World</div>";
    }
    @GetMapping(path = "/test2")
    public Flux<Rendering> test2() {
        return Flux.just(
                Rendering.view("test").modelAttribute("id", "hello2")
                        .modelAttribute("value", "Hello2").build(),
                Rendering.view("test").modelAttribute("id", "world2")
                        .modelAttribute("value", "World2").build());
    }

    static class Greeting {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}