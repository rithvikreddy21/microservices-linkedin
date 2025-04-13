package com.rithvikreddy.linkedInProject.connectionsService.controller;

import com.rithvikreddy.linkedInProject.connectionsService.auth.AuthContextHolder;
import com.rithvikreddy.linkedInProject.connectionsService.entity.Person;
import com.rithvikreddy.linkedInProject.connectionsService.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId){
//        if we want to use the header userId you can get it from
//        Long userIdFromHeader = AuthContextHolder.getCurrentUserId();
        log.info("User id is {}",userId);
        List<Person> personsList = connectionsService.getFirstDegreeConnectionsOfUser(userId);
        return ResponseEntity.ok(personsList);
    }
}
