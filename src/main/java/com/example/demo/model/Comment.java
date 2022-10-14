package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String country;

    @Column(columnDefinition="text", length=10485760)
    private String message;
    private Timestamp sendTime;
}
