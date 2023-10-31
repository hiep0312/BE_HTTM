package app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dao.DatasetSampleDao;
import app.model.Sample;

@RestController
@CrossOrigin
public class DatasetSampleController {
    DatasetSampleDao dsDao = new DatasetSampleDao();

    @GetMapping("/dataset-samples")
    public List<Sample> getSampleFromDataset(@RequestParam int start_idx, @RequestParam int count, @RequestParam int id) {
        return dsDao.getSampleFromDataset(start_idx, count, id);
    }
}
