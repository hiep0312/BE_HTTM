package app.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.dao.AudioDao;
import app.model.Audio;

@RestController
@CrossOrigin
public class AudioController {
    AudioDao audioDao = new AudioDao();

    @GetMapping("/audios")
    public List<Audio> getAudios(@RequestParam("start_idx") int start_idx, @RequestParam("count") int count) {
        return audioDao.getAudios(start_idx, count);
    }

    @GetMapping("/audio{id}")
    public ResponseEntity<?> getAudioById(int id) {
        return audioDao.getAudioById(id);
    }

    @GetMapping("/audiourl/{id}")
    public ResponseEntity<byte[]> getAudioFileById(@PathVariable int id) {
        return audioDao.getAudioFileById(id);
    }


    @PostMapping("/addaudio")
    public ResponseEntity<?> addAudio(@RequestParam("name") String name, @RequestParam("audio") MultipartFile audio) {
        return audioDao.addAudio(name, audio);
    }

    @PostMapping("/deleteaudio{id}")
    public ResponseEntity<?> deleteAudio(int id) {
        return audioDao.deleteAudio(id);
    }

    @PostMapping("/editaudio")
    public ResponseEntity<?> editAudio(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("audio") MultipartFile audio) {
        return audioDao.editAudio(id, name, audio);
    }

    @GetMapping("/audio-by-name")
    public ResponseEntity<?> getAudioByName (@RequestParam String name) {
        return audioDao.getAudioByName(name);
    }
    
}