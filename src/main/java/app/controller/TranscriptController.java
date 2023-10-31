package app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dao.TranscriptDao;
import app.model.Transcript;


@RestController
@CrossOrigin
public class TranscriptController {
    TranscriptDao transcriptDao = new TranscriptDao();

    // @GetMapping("/transcripts")
    // public List<Transcript> getTranscripts(@RequestParam("start_idx") int start_idx, @RequestParam("count") int count) {
    //     return transcriptDao.getTranscript(start_idx, count);
    // }

    @PostMapping("/addtranscript{name}{content}")
    public ResponseEntity<?> addTranscript(String name, String content) {
        return transcriptDao.addTranscript(new Transcript(name, content));
    }

    @PostMapping("/edittranscript{id}{name}{content}")
    public ResponseEntity<String> editTranscript(int id, String name, String content) {
        return transcriptDao.editTranscript(id, name, content);
    }

    @PostMapping("/deletetranscript{id}")
    public ResponseEntity<String> deleteTranscript(int id) {
        return transcriptDao.deleteTranscript(id);
    }

    @GetMapping("/transcript-by-name")
    public ResponseEntity<?> getTranscriptByName(@RequestParam("name") String name) {
        return transcriptDao.getTranscriptByName(name);
    }

    @GetMapping("/transcript{id}")
    public ResponseEntity<?> getTranscriptById(int id) {
        return transcriptDao.getTranscriptById(id);
    }

    @GetMapping("/transcripts")
    public List<Transcript> getTranscripts(@RequestParam int start_idx, @RequestParam int count, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "true") boolean ascend) {
        return transcriptDao.getTranscript(start_idx, count, sortBy, ascend);
    }
}