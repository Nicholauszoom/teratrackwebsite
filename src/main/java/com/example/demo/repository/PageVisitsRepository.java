package com.example.demo.repository;

import com.example.demo.model.PageVisits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PageVisitsRepository extends JpaRepository<PageVisits,Long> {

    //JPQL
    //Native Query - SQL

    @Query("SELECT sendDate as sendDate,COUNT(*) as count FROM page_visits GROUP BY sendDate")
    public List<PageVisits> getCountByDate(Integer COUNT);


    PageVisits findBySessionId(String sessionId);


     /*PageVisits findsendDate();

    PageVisits findBySendDate();*/
     PageVisits findBySendDate(Date sendDate);
   /* PageVisits getSendDate(Date sendDate);*/





}


