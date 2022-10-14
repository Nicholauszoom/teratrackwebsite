package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.ZonedDateTime;

@Data
@Table(name = "page_visits")
@Entity(name = "page_visits")
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy, timezone = UTC")

//Json Format for your date here


public class PageVisits  {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "session_id", nullable = false)
    private String sessionId;
    @Column(name = "creation_time", nullable = false)
    private Long creationTime;
    private Long count;
    private Date sendDate;




}
