package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;

public interface JobRepository extends CrudRepository<Job, Long> {

    ArrayList<Job> findByTitleContainingIgnoreCase(String title);
    ArrayList<Job> findByDescriptionContainingIgnoreCase(String description);
    ArrayList<Job> findByAuthorContainingIgnoreCase(String author);
}
