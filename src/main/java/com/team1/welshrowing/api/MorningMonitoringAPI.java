package com.team1.welshrowing.api;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.service.MorningMonitoringReadService;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class MorningMonitoringAPI {

    @Autowired
    MorningMonitoringReadService morningMonitoringReadService;

    @Autowired
    UserReadService userReadService;

    @RequestMapping(path = "/morning-monitoring/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<MorningMonitoring>> getMorningMonitoring(@PathVariable Long id) {

        Optional<User> thisUser = userReadService.findById(id);

        List<MorningMonitoring> listOfForms = null;

        if (thisUser.isPresent()) {
            listOfForms = morningMonitoringReadService.findByUser(thisUser.get());
        }

        return ResponseEntity.status(HttpStatus.OK).body(listOfForms);

    }

    @RequestMapping(path = "/morning-monitoring/latest/{id}", method = RequestMethod.GET)
    public ResponseEntity<MorningMonitoring> getLatestMorningMonitoring(@PathVariable Long id) {

        Optional<User> thisUser = userReadService.findById(id);

        if (thisUser.isPresent()) {
            Optional<MorningMonitoring> latestForm = morningMonitoringReadService.findLatestByUser(thisUser.get());
            if (latestForm.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(latestForm.get());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);

    }


}
