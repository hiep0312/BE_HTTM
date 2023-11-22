package app.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dao.AlgorithmDao;


@RestController
@CrossOrigin
public class AlgorithmController {
    AlgorithmDao algoDao = new AlgorithmDao();

    @GetMapping("/algorithms")
    public ResponseEntity<?> getAllAlgorithm() {
        return algoDao.getAllAlgorithm();
    }
}
