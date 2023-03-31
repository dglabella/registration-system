package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.model.WorkAttendance;
import ar.edu.unsl.fmn.gida.apis.registration.services.WorkAttendanceService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.workAttendances)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class WorkAttendanceController {
    
    private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 100;

    @Autowired
	private WorkAttendanceService service;

    // @GetMapping()
    // public List<WorkAttendance> getAllWorkAttendancesBetweenDates(@RequestParam Map<String, String> map){
    //     String from = map.get("from");
	// 	String to = map.get("to");

    //     return this.service.getAllWorkAttendancesBetweenDates(from, to);
    // }

    @GetMapping
    public Page<WorkAttendance> getAllWorkAttendancesBetweenDatesPaged(@RequestParam Map<String, String> map){
        Page<WorkAttendance> page = null;
        String from = map.get("from");
		String to = map.get("to");

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllWorkAttendancesBetweenDates(from, to, this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllWorkAttendancesBetweenDates(from, to, Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllWorkAttendancesBetweenDates(from, to, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllWorkAttendancesBetweenDates(from, to, Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

        return page;
    }

}
