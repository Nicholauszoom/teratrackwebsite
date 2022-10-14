package com.example.demo.repository;

import com.example.demo.model.PageVisits;
import com.example.demo.model.SiteVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SiteVisitRepository extends JpaRepository<SiteVisit,Long> {

}