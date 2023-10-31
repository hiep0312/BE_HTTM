package app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dao.SampleDao;
import app.model.Sample;

@RestController
@CrossOrigin
public class SampleController {
    SampleDao sampleDao = new SampleDao();

    @GetMapping("/samples{start_idx}{count}")
    public List<Sample> getSamples(int start_idx, int count) {
        return sampleDao.getSamples(start_idx, count);
    }

    @PostMapping("/addsample")
    public ResponseEntity<?> addSample(@RequestParam String name, @RequestParam int audioId, @RequestParam int transcriptId) {
        return sampleDao.addSample(new Sample(name, audioId, transcriptId));
    }

    @PostMapping("/editsample")
    public ResponseEntity<?> editSample(@RequestParam int id, @RequestParam String name, @RequestParam int audioId, @RequestParam int transcriptId) {
        return sampleDao.editSample(id, name, audioId, transcriptId);
    }

    @PostMapping("/deletesample{id}")
    public ResponseEntity<?> deleteSample(int id) {
        return sampleDao.deleteSample(id);
    }

    @GetMapping("/sample-by-name")
    public ResponseEntity<?> getSampleByName(@RequestParam String name) {
        return sampleDao.getSampleByName(name);
    }

    
    
}
