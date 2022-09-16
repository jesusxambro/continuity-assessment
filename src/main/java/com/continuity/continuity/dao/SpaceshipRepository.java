package com.continuity.continuity.dao;

import com.continuity.continuity.controllers.SpaceshipController;
import com.continuity.continuity.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceshipRepository extends JpaRepository<Spaceship,Long> {
}
