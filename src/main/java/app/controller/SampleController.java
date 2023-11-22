package app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dao.SampleDao;
import app.model.Sample;

@RestController
@CrossOrigin
public class SampleController {
    SampleDao sampleDao = new SampleDao();

    @GetMapping("/samples")
    public List<Sample> getSamples(@RequestParam int start_idx,@RequestParam int count, @RequestParam(defaultValue = "lastupdate") String sortBy, @RequestParam(defaultValue = "false") boolean ascend) {
        return sampleDao.getSamples(start_idx, count, sortBy, ascend);
    }

    // @PostMapping("/addsample")
    // public ResponseEntity<?> addSample(@RequestParam String name, @RequestParam int audioId, @RequestParam int transcriptId) {
    //     return sampleDao.addSample(new Sample(name, audioId, transcriptId));
    // }

    @PostMapping("/addsample")
    public ResponseEntity<?> addSample(@RequestBody Sample sample) {
        return sampleDao.addSample(sample);
    }

    @PostMapping("/editsample")
    public ResponseEntity<?> editSample(@RequestBody Sample sample) {
        return sampleDao.editSample(sample);
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
