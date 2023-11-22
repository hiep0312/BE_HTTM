package app.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dao.AudioDao;
import app.model.ObjectIndex;
import app.model.UploadedFile;

@RestController
@CrossOrigin
public class AudioController {
    AudioDao audioDao = new AudioDao();

    // @GetMapping("/audios")
    // public List<Audio> getAudios(@RequestParam("start_idx") int start_idx, @RequestParam("count") int count) {
    //     return audioDao.getAudios(start_idx, count);
    // }

    // @GetMapping("/audios")
    // public List<Audio> getAudios(@RequestParam int start_idx, @RequestParam int count, @RequestParam(defaultValue = "lastupdate") String sortBy, @RequestParam(defaultValue = "false") boolean ascend) {
    //     return audioDao.getAudios(start_idx, count, sortBy, ascend);
    // }

    @GetMapping("/audios")
    public ResponseEntity<?> getAudios(@RequestBody ObjectIndex  audioIndex) {
        return audioDao.getAudios(audioIndex);
    }

    @GetMapping("/audio")
    public ResponseEntity<?> getAudioById(@RequestParam int id) {
        return audioDao.getAudioById(id);
    }

    @GetMapping("/audiourl/{id}")
    public ResponseEntity<byte[]> getAudioFileById(@PathVariable int id) {
        return audioDao.getAudioFileById(id);
    }


    // @PostMapping("/addaudio")
    // public ResponseEntity<?> addAudio(@RequestParam("name") String name, @RequestParam("audio") MultipartFile audio) {
    //     return audioDao.addAudio(name, audio);
    // }

     @PostMapping("/addaudio")
    public ResponseEntity<?> addAudio(@RequestBody UploadedFile file) {
        return audioDao.addAudio(file);
    }


    @PostMapping("/deleteaudio")
    public ResponseEntity<?> deleteAudio(@RequestParam int id) {
        return audioDao.deleteAudio(id);
    }

    // @PostMapping("/editaudio")
    // public ResponseEntity<?> editAudio(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("audio") MultipartFile audio) {
    //     return audioDao.editAudio(id, name, audio);
    // }

    @PostMapping("/editaudio")
    public ResponseEntity<?> editAudio(@RequestBody UploadedFile file) {
        return audioDao.editAudio(file);
    }

    @GetMapping("/audio-by-name")
    public ResponseEntity<?> getAudioByName (@RequestParam String name) {
        return audioDao.getAudioByName(name);
    }
    
}

