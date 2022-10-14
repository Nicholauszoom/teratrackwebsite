package com.example.demo.Service;

import com.example.demo.repository.SiteVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SiteVisitServive {
    @Autowired
    SiteVisitRepository siteVisitRepository;

    public void reseiveSessions(String siteVisit, HttpSession session) {
        siteVisitRepository.findAll();
    }
}
