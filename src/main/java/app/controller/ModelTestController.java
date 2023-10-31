package app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
public class ModelTestController {
    private static final String ip = "http://localhost:80";

    final String uri = ip + "/tts?text=hello world";

    public static void getAudioId(String text)
    {
        final String uri = ip + "/tts?text=" + text;
    
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
    
        System.out.println(result);
    }

    @GetMapping("/test")
    public String testModel(@RequestParam String text) {
        String uri = ip + "/tts?text=" + text;
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
    
        System.out.println(result);
        return result;
    }

    
}
