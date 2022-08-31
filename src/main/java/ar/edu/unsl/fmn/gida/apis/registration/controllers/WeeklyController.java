package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.services.WeeklyService;

@RestController
@RequestMapping(value = RegistrationSystemApplication.Endpoints.weeklies)
public class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Weekly> getWeekly(@PathVariable int id) {
        Weekly w = this.weeklyService.getOne(id);

        ResponseEntity<Weekly> response = w != null ? ResponseEntity.ok().body(w)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Weekly>> getAllWeeklies() {
        List<Weekly> weeklies = weeklyService.getAll();

        ResponseEntity<List<Weekly>> response = weeklies.size() > 0 ? ResponseEntity.ok().body(weeklies)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @PostMapping
    public ResponseEntity<Weekly> postWeekly(@RequestBody Weekly weekly) {
        Weekly w = weeklyService.insert(weekly);

        ResponseEntity<Weekly> response = w != null ? new ResponseEntity<Weekly>(w, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return response;
    }

    @PutMapping
    public ResponseEntity<Weekly> updateWeekly() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Weekly> deleteWeekly() {
        return null;
    }
}
