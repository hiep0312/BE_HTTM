package app.controller;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import app.util.Util;

@RestController
@CrossOrigin
public class ModelTestController {
    private static final String ip = "http://localhost:80";

    @PostMapping("/train{id}")
    public ResponseEntity<?> trainModel(@RequestBody ModelTrain model) {
        String uri = ip + "/start_training?algorithmId=" + model.getAlgorithmId() + "&" + "datasetId=" + model.getDatasetId() + "&"
                + "modelName=" + model.getModelName();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, null, String.class);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/test/tts")
    public ResponseEntity<byte[]> testModel(@RequestBody String text) {
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
    public ResponseEntity<?> getAudiogenId(@RequestBody String text) {
        String uri = ip + "/tts?text=" + text;
        System.out.println("tts uri: " + uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/audiogen")
    public ResponseEntity<byte[]> getAudioGenerated(@RequestBody Integer id) {

        RestTemplate restTemplate = new RestTemplate();
        String uriAudioGen = ip + "/audiogen?id=" + id;
        System.out.println("audio-gen uri: " + uriAudioGen);
        byte[] audioFile = restTemplate.getForObject(uriAudioGen, byte[].class);

        return Util.getAudioResponse(audioFile, String.valueOf(id));
    }
}

class ModelTrain {
    private int algorithmId;
    private int datasetId;
    private String modelName;

    public ModelTrain(int algorithmId, int datasetId, String modelName) {
        this.algorithmId = algorithmId;
        this.datasetId = datasetId;
        this.modelName = modelName;
    }

    public int getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(int algorithmId) {
        this.algorithmId = algorithmId;
    }

    public int getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(int datasetId) {
        this.datasetId = datasetId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelname) {
        this.modelName = modelname;
    }
}