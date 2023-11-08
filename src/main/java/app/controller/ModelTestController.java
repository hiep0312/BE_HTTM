package app.controller;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import app.util.Util;


@RestController
@CrossOrigin
public class ModelTestController {
    private static final String ip = "http://localhost:80";


    @GetMapping("/test/tts")
    public ResponseEntity<byte[]> testModel(@RequestParam String text) {
        String uri = ip + "/tts?text=" + text;
        System.out.println("tts uri: " + uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        
        JSONObject obj = new JSONObject(result);
        String uriAudioGen = ip + "/audiogen?id=" + obj.getInt("audio_id");
        System.out.println("audio-gen uri: " + uriAudioGen);
        byte[] audioFile = restTemplate.getForObject(uriAudioGen, byte[].class);

        
        return Util.getAudioResponse(audioFile, String.valueOf(obj.getInt("audio_id")));
    }

    @GetMapping("/tts")
    public ResponseEntity<?> getAudiogenId(@RequestParam String text) {
        String uri = ip + "/tts?text=" + text;
        System.out.println("tts uri: " + uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return ResponseEntity.ok().body(result);
    }

    

    @GetMapping("/audiogen")
    public ResponseEntity<byte[]> getAudioGenerated(@RequestParam int id) {
        
        RestTemplate restTemplate = new RestTemplate();
        String uriAudioGen = ip + "/audiogen?id=" + id;
        System.out.println("audio-gen uri: " + uriAudioGen);
        byte[] audioFile = restTemplate.getForObject(uriAudioGen, byte[].class);

        
        return Util.getAudioResponse(audioFile, String.valueOf(id));
    }
}
