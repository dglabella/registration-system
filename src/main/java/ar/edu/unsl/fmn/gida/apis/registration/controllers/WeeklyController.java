package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.endpoints.Endpoint;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.services.WeeklyService;

@RestController
@RequestMapping(value = Endpoint.weeklies)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    @RequestMapping(value = "/{id}")
    public Weekly getWeekly(@PathVariable int id) {
        Weekly w = this.weeklyService.getOne(id);
        return w;
    }

    @GetMapping
    public List<Weekly> getAllWeeklies() {
        List<Weekly> weeklies = weeklyService.getAll();
        return weeklies;
    }

    @PostMapping
    public Weekly postWeekly(@RequestBody Weekly weekly) {
        Weekly w = weeklyService.insert(weekly);
        return w;
    }

    @PutMapping(value = "/{id}")
    public Weekly updateWeekly(@PathVariable int id, @RequestBody Weekly weekly) {
        Weekly w = weeklyService.update(id, weekly);
        return w;
    }

    @DeleteMapping
    public Weekly deleteWeekly() {
        throw new ErrorResponse("delete person operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
