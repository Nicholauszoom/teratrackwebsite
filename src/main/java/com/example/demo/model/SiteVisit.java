package com.example.demo.model;

import lombok.Data;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.orm.hibernate5.SpringSessionContext;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
public class SiteVisit {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp sendTime;
    private Long count;
   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    private SpringSessionContext springSessionContext;
*/


}
