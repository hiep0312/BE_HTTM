package app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import app.dao.ModelDao;
import app.model.Model;

@RestController
@CrossOrigin
public class ModelController {
    public static final String pythonIp = "http://localhost:80";

    // /ttms   return model task ttm
    // /vocoders return model task vocoder
    //   /model?ttmId=1 & vocoderId=1       // call post to python server (send path ttm and vocoder)
    ModelDao modelDao = new ModelDao();


    @GetMapping("/ttms")
    public List<Model> getModelTaskTtms() {
        return modelDao.getModelsByTask("ttm");
    }

    @GetMapping("/vocoders")
    public List<Model> getModelTaskVocoders() {
        return modelDao.getModelsByTask("vocoder");
    }

    @PostMapping("/model")
    public String getModelById(@RequestParam int ttmId, @RequestParam int vocoderId) {
        String uri = pythonIp + "/model-path/?ttmPath=" + modelDao.getPath(ttmId) + "&vocoderPath=" + modelDao.getPath(vocoderId);
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        
        String result = restTemplate.postForObject(uri, null, String.class);
        return result;
    }

    @PostMapping("addmodel")
    public ResponseEntity<?> addModel(@RequestParam String name, @RequestParam String path, @RequestParam float mos, @RequestParam int datasetId, @RequestParam int algorithmId, @RequestParam String task) {
        Model model = new Model(name, path, mos, datasetId, algorithmId, task);
        return modelDao.addModel(model);
    }

    @PostMapping("deletemodel")
    public ResponseEntity<?> deleteModel(@RequestParam int id) {
        return modelDao.deleteModel(id);
    }
}

