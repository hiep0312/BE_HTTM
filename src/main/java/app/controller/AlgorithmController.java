package app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dao.AlgorithmDao;
import app.model.Algorithm;


@RestController
@CrossOrigin
public class AlgorithmController {
    AlgorithmDao algoDao = new AlgorithmDao();

    @GetMapping("/algorithms")
    public List<Algorithm> getAllAlgorithm() {
        return algoDao.getAllAlgorithm();
    }
}
