package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.services.WeeklyService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.weeklies)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class WeeklyController {

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_QUANTITY_PER_PAGE = 20;

    @Autowired
    private WeeklyService weeklyService;

    @GetMapping
    public List<Weekly> getAllWeeklies() {
        List<Weekly> weeklies = weeklyService.getAll();
        return weeklies;
    }

    @GetMapping(value = "/person/{id}/paged")
    public Page<Weekly> getAllWeekliesFromPerson(@PathVariable int id,
            @RequestParam Map<String, String> map) {
        Page<Weekly> page = null;

        if (!map.containsKey("page") && !map.containsKey("quantity")) {

        } else if (map.containsKey("page") && !map.containsKey("quantity")) {

        } else if (!map.containsKey("page") && map.containsKey("quantity")) {

        } else {

        }

        return page;
    }
}
