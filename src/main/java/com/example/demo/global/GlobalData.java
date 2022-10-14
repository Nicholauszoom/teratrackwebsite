package com.example.demo.global;


import com.example.demo.model.Comment;
import com.example.demo.model.PageVisits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    public static List<Comment> comments;
    static{
        comments=new ArrayList<Comment>();
    }





}
