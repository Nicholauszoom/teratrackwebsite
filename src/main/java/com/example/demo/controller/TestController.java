package com.example.demo.controller;

import com.example.demo.Service.CommentService;
import com.example.demo.Service.SiteVisitServive;
import com.example.demo.global.GlobalData;
import com.example.demo.model.Comment;
import com.example.demo.model.CopyComments;
import com.example.demo.model.Dto;
import com.example.demo.model.SiteVisit;
import com.example.demo.repository.CopyCommentRepo;
import com.example.demo.repository.SiteVisitRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.annotation.WebListener;
import java.sql.Timestamp;
import java.util.Date;


@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
@SessionAttributes
@WebListener
@Controller
public class TestController {
    @Autowired
    CommentService commentService;

    @Autowired
    SiteVisitServive siteVisitServive;

    @Autowired
    SiteVisitRepository siteVisitRepository;

    @Autowired
    CopyCommentRepo copyCommentRepo;


    @GetMapping("/features")
    public String getFeatures() {
        return "features";
    }

    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "aboutUs";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegistry() {
        return "registration";
    }


    @RequestMapping("/userDashboard")
    public String getUserHome() {
        return "home";
    }


    @GetMapping("/contactUs")
    public String getContact() {
        return "contactUs";
    }

 /*   @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id) {
        GlobalData.cart.add(productService.getProductById(id).get());
        GlobalData.users.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }*/

    @PostMapping("/contactUs")
    public String sendComment(@ModelAttribute("dto") Dto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setPhone(dto.getPhone());
        comment.setCountry(dto.getCountry());
        comment.setMessage(dto.getMessage());
        comment.setSendTime(new Timestamp(new Date().getTime()));
        commentService.sendComment(comment);

        //GlobalData.comments.add(commentService.getCommentById(id).get());

      /*  GlobalData.comments.add(commentService.getCommentById(id).get());*/

        /*CopyComments copyComments= new CopyComments();
        copyComments.setName(comment.getName());
        copyComments.setEmail(comment.getEmail());
        copyComments.setPhone(comment.getPhone());
        copyComments.setCountry(comment.getCountry());
        copyComments.setMessage(comment.getMessage());
        copyComments.setSendTime(comment.getSendTime());
        copyCommentRepo.save(copyComments);*/

        return "redirect:/contactUs";
    }


    @GetMapping("/fleet-management")
    public String getFleetManagement() {
        return "fleet_management";
    }

    @GetMapping("/equipment-tracking")
    public String getAssetTracking() {
        return "equipment-tracking";
    }

    @GetMapping("/family-tracking")
    public String getFamilyTracking() {
        return "family-tracking";
    }

    @GetMapping("/task-management")
    public String getTaskManagement() {
        return "task-management";
    }

    @GetMapping("/latra-vts")
    public String getLatraVts() {
        return "latra-vts";
    }

    @GetMapping("/customer")
    public String getCustomer(){
        return "customer";
    }

    @GetMapping("/school-bus-tracking")
    public String getSchoolBusTracking(){
        return "school-bus-tracking";
    }

    @GetMapping("/Vehicle")
    public String getVehicles(){
        return "Vehicle";
    }

    @GetMapping("/testimonies")
    public String getTestimonies(){
        return "testimonies";
    }



}

