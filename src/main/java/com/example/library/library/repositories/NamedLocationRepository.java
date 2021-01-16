package com.example.library.library.repositories;

import com.example.library.library.models.NamedLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamedLocationRepository extends JpaRepository<NamedLocation, Long> {
}

