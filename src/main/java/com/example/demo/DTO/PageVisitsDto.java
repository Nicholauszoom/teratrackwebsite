package com.example.demo.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PageVisitsDto {
    private Long id;
    private int sessionId;
    private Timestamp creationTime;
}
