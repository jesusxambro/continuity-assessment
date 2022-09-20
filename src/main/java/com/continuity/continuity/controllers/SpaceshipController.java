package com.continuity.continuity.controllers;


import com.continuity.continuity.dao.SpaceshipRepository;
import com.continuity.continuity.model.Crewmember;
import com.continuity.continuity.model.Spaceship;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    @PutMapping("/{id}")
    public Spaceship putById(@RequestBody Spaceship updatedInfo, @PathVariable long id){
        Spaceship shipToUpdate = this.repository.getReferenceById(id);
        shipToUpdate.setName(updatedInfo.getName());
        shipToUpdate.setFuel(updatedInfo.getFuel());
        return this.repository.save(shipToUpdate);
    }
    @PatchMapping("/{id}")
    public Spaceship patchById(@RequestBody Map<String, String> updatedInfo, @PathVariable long id){
        Spaceship shipToUpdate = this.repository.getReferenceById(id);

        updatedInfo.forEach((key, value) -> {
            if(key.equals("name")){
                updateName(shipToUpdate,value);
            }
            if(key.equals("morale")){
                updateFuel(shipToUpdate,Integer.valueOf(value) );
            }
        });


        return this.repository.save(shipToUpdate);
    }
    private void updateName(Spaceship ship, String newName ){
        ship.setName(newName);
    }
    private void updateFuel(Spaceship ship, Integer fuel){
        ship.setFuel(fuel);
    }




}
