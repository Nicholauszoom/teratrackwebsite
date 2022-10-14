package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Dto  {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String country;
    private String message;
    private Timestamp sendTime;
}
