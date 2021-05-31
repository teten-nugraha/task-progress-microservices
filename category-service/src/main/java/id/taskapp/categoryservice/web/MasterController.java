package id.taskapp.categoryservice.web;


import id.taskapp.categoryservice.domain.Master;
import id.taskapp.categoryservice.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterService masterService;


    @GetMapping()
    public ResponseEntity<List<Master>> findAll() {
        List<Master> masters = masterService.findAllMasters();
        if (masters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(masterService.findAllMasters(), HttpStatus.OK);
    }

}
