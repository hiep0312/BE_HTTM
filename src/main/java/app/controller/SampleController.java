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
    public ResponseEntity<?> addSample(@RequestParam("audioId") int audioId, @RequestParam("transcriptId") int transcriptId) {
        return sampleDao.addSample(new Sample(audioId, transcriptId));
    }

    @PostMapping("/editsample{id}{audioId}{transcriptId}")
    public ResponseEntity<?> editSample(int id, int audioId, int transcriptId) {
        return sampleDao.editSample(id, audioId, transcriptId);
    }

    @PostMapping("/deletesample{id}")
    public ResponseEntity<?> deleteSample(int id) {
        return sampleDao.deleteSample(id);
    }
}
