package com.continuity.continuity.controllers;


import com.continuity.continuity.dao.CrewmemberRepository;
import com.continuity.continuity.model.Crewmember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/crewmember")
public class CrewmemberController {

    private final CrewmemberRepository repository;

    public CrewmemberController(CrewmemberRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Crewmember> getAll(){
        return this.repository.findAll();
    }
    @GetMapping("{id}")
    public Crewmember getById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("null")));
    }


    @PostMapping("")
    public Crewmember createCrewmember(@RequestBody Crewmember memberToAdd){
        return this.repository.save(memberToAdd);
    }
    @PostMapping("/group")
    public List<Crewmember> createABunchOfCrew(@RequestBody List<Crewmember> theCrew){
//        theCrew.forEach(crewmember -> createCrewmember(crewmember));
        return this.repository.saveAll(theCrew);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        this.repository.deleteById(id);
        return new ResponseEntity<>(
                String.format("The crewmember with an id of $d has been deleted from the database",id),HttpStatus.NO_CONTENT);
    }



}
