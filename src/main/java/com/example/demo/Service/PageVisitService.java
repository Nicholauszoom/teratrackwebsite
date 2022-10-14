package com.example.demo.Service;

import com.example.demo.model.PageVisits;
import com.example.demo.repository.PageVisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PageVisitService {

    @Autowired
    PageVisitsRepository pageVisitsRepository;
   /* public List<PageVisits> findSessionById(long id) { return pageVisitsRepository.moveSessions(id); }*/

    public long getCountByDate(){
        return pageVisitsRepository.count();
    }

  public List<PageVisits> getCountBysendDate(Date sendDate){
      Integer Date = null;
      return pageVisitsRepository.getCountByDate(Date);}

    public Object getDateByDate(Date sendDate) {
        return sendDate;
    }
}
