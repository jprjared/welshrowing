package com.team1.welshrowing.api;

import com.team1.welshrowing.domain.RPE;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.service.RPEReadService;
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
public class RPEAPI {

    @Autowired
    RPEReadService rpeReadService;

    @Autowired
    UserReadService userReadService;

    @RequestMapping(path = "/RPE-form/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<RPE>> getRPE(@PathVariable Long id) {

        Optional<User> thisUser = userReadService.findById(id);

        List<RPE> listOfForms = null;

        if (thisUser.isPresent()) {
            listOfForms = rpeReadService.findByUser(thisUser.get());
        }

        return ResponseEntity.status(HttpStatus.OK).body(listOfForms);

    }

    @RequestMapping(path = "/RPE-form/latest/{id}", method = RequestMethod.GET)
    public ResponseEntity<RPE> getLatestRPE(@PathVariable Long id) {

        Optional<User> thisUser = userReadService.findById(id);

        if (thisUser.isPresent()) {
            Optional<RPE> latestForm = rpeReadService.findLatestRPEByUser(thisUser.get());
            if (latestForm.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(latestForm.get());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);

    }


}
