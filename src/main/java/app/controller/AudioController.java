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
import app.model.Audio;
import app.model.UploadedFile;

@RestController
@CrossOrigin
public class AudioController {
    AudioDao audioDao = new AudioDao();

    

    @GetMapping("/audios")
    public ResponseEntity<?> getAudios(@RequestParam int start_idx, @RequestParam int count, @RequestParam(defaultValue = "lastupdate") String sortBy, @RequestParam(defaultValue = "false") boolean ascend) {
        return audioDao.getAudios(start_idx, count, sortBy, ascend);
    }

    // @PostMapping("/editaudio")
    // public ResponseEntity<?> editAudio(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("audio") MultipartFile audio) {
    //     return audioDao.editAudio(id, name, audio);
    // }


    // @PostMapping("/addaudio")
    // public ResponseEntity<?> addAudio(@RequestParam("name") String name, @RequestParam("audio") MultipartFile audio) {
    //     return audioDao.addAudio(name, audio);
    // }

    // @GetMapping("/audios")
    // public ResponseEntity<?> getAudios(@RequestBody ObjectIndex  audioIndex) {
    //     return audioDao.getAudios(audioIndex);
    // }

    @GetMapping("/audio")
    public ResponseEntity<?> getAudioById(@RequestParam Integer id) {
        return audioDao.getAudioById(id);
    }

    @GetMapping("/audiourl")
    public ResponseEntity<byte[]> getAudioFileById(@RequestParam int id) {
        return audioDao.getAudioFileById(id);
    }


    @PostMapping("/addaudio")
    public ResponseEntity<?> addAudio(@RequestBody UploadedFile file) {
        return audioDao.addAudio(file);
    }


    @PostMapping("/deleteaudio")
    public ResponseEntity<?> deleteAudio(@RequestBody Audio audio) {
        return audioDao.deleteAudio(audio.getId());
    }

    @PostMapping("/editaudio")
    public ResponseEntity<?> editAudio(@RequestBody UploadedFile file) {
        return audioDao.editAudio(file);
    }

    @GetMapping("/audio-by-name")
    public ResponseEntity<?> getAudioByName (@RequestParam String name) {
        return audioDao.getAudioByName(name);
    }
    
}

