package app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dao.DatasetDAO;
import app.model.Dataset;
import app.model.Sample;


@RestController
@CrossOrigin
public class DatasetController {
    DatasetDAO datasetDAO = new DatasetDAO();

    @GetMapping("/datasets")
    public List<Dataset> getAllDataset() {
        return datasetDAO.getAllDataset();
    }

}
