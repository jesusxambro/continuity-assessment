package com.continuity.continuity.controllers;


import com.continuity.continuity.dao.SpaceshipRepository;
import com.continuity.continuity.model.Spaceship;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/spaceship")
public class SpaceshipController {
    private final SpaceshipRepository repository;

    public SpaceshipController(SpaceshipRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Spaceship> findAllSpaceships(){
        return this.repository.findAll();
    }
    @GetMapping("/{id}")
    public Spaceship getSpaceshipById(@PathVariable Long id) {
            return  this.repository.findById(id).orElseThrow(() ->new NoSuchElementException(String.format("null")));
    }
    @PostMapping("")
    public Object createASpaceShip(@RequestBody Spaceship spaceshipToAdd){
        return this.repository.save(spaceshipToAdd);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        this.repository.deleteById(id);
        return new ResponseEntity<>(
                String.format("The spaceship with an id of $d has been deleted from the database",id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/current")
    public String getCurrentSpaceship(@CookieValue Long id){
        Spaceship current = getSpaceshipById(id);
        if(current.getName() == null){
            return "You do not have a current spaceship";
        }else{
            return String.format("Your current spaceship has the id of %d", current.getId());
        }

    }




}
