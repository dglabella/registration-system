package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WeeklyRepository;

@Service
public class WeeklyService {

    @Autowired
    private WeeklyRepository weeklyRepository;

    public Weekly getOne(int id) {
        Weekly w = null;
        Optional<Weekly> optional = weeklyRepository.findById(id);

        if (optional.isPresent()) {
            w = optional.get();
        }

        return w;
    }

    public List<Weekly> getAll() {
        return weeklyRepository.findAll();
    }

    public Weekly insert(Weekly weekly) {
        Weekly w = weeklyRepository.save(weekly);
        return w;
    }

    public Weekly update(Weekly weekly) {
        Weekly w = weeklyRepository.save(weekly);
        return w;
    }

    public Weekly delete(int id) {
        return null;
    }
}
