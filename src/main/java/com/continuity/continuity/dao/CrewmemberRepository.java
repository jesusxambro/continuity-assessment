package com.continuity.continuity.dao;

import com.continuity.continuity.model.Crewmember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewmemberRepository extends JpaRepository<Crewmember, Long> {
}
