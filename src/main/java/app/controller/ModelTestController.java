package app.controller;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
public class ModelTestController {
    private static final String ip = "http://localhost:80";


    @GetMapping("/tts")
    public ResponseEntity<byte[]> testModel(@RequestParam String text) {
        String uri = ip + "/tts?text=" + text;
        System.out.println("tts uri: " + uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        
        JSONObject obj = new JSONObject(result);
        String uriAudioGen = ip + "/audiogen?id=" + obj.getInt("audio_id");
        System.out.println("audio-gen uri: " + uriAudioGen);
        byte[] audioFile = restTemplate.getForObject(uriAudioGen, byte[].class);
        
        ResponseEntity<byte[]> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "inline; filename=" + obj.getInt("audio_id") + ".wav")
            .body(audioFile);

        
        return response;
    }

    
}
