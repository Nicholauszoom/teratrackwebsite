package com.example.demo.controller;

import com.example.demo.Service.CommentService;
import com.example.demo.Service.CopyCommentService;
import com.example.demo.Service.PageVisitService;
import com.example.demo.global.GlobalData;
import com.example.demo.model.Comment;
import com.example.demo.model.Dto;
import com.example.demo.model.PageVisits;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.CopyCommentRepo;
import com.example.demo.repository.PageVisitsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

@SessionAttributes
@WebListener
@Controller
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    PageVisitService pageVisitService;
    @Autowired
    PageVisitsRepository pageVisitsRepository;

    private ReactiveSessionRepository<? extends Session> sessionRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    CopyCommentRepo copyCommentRepo;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CopyCommentService copyCommentService;



    @GetMapping("/")
    public String getHome(@ModelAttribute("pageViews") PageVisits pageViews,HttpSession session, Model model, HttpServletRequest request ,  BindingResult bindingResult ) throws ServletException, IOException {

        List<String> messgs = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messgs == null) {
            messgs = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messgs.size());

        PageVisits pageVisits=new PageVisits();
        pageVisits.setSessionId(session.getId());
        pageVisits.setCreationTime(session.getCreationTime());

       /* pageVisits.setSendDate(new Timestamp(new Date().getTime()));*/

        pageVisits.setSendDate(new java.sql.Date(new Date().getTime()));

        /*pageVisitsRepository.save(pageVisits);*/
        PageVisits sessionExists = pageVisitsRepository.findBySessionId(pageVisits.getSessionId());
        logger.info(pageViews.getSessionId());
        logger.info(" " + bindingResult.getErrorCount());
        for (ObjectError er : bindingResult.getAllErrors()){
            logger.info(er.getDefaultMessage());
        }

        //PageVisits date=pageVisitsRepository.findBySendDate(pageVisits.getSendDate());
        if (sessionExists !=null ){

            bindingResult.rejectValue("sessionId", "error.pageVisits","already exist cessionId");
            System.out.println("already exist cessionId");
            return "index";


        }else {
            pageVisitsRepository.save(pageVisits);
        }


        session.getAttribute("SessionsRequests");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession sessionss = attr.getRequest().getSession();
        return "index";
    }


    static final String DB_URL = "jdbc:postgresql://localhost:5432/tera?serverTimezone=UTC";
    static final String USER = "postgres";
    static final String PASS = "SOMI";
    static final String QUERY = "SELECT send_date as sendDate,COUNT(*) as count FROM page_visits GROUP BY send_date ORDER BY sendDate";

    static List<Integer> dataCountNo = new ArrayList<>();
    static List<String> dataCountDate = new ArrayList<>();

    static int maxDataLength = 0;

    static int getMaxData = 0;
    public static void DataQuery() {
        // Open a connection
        List<Integer> dataCountNo2 = new ArrayList<>();
        List<String> dataCountDate2 = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            while(rs.next()){
                //Display values
                System.out.print("sendDate: " + rs.getString("sendDate"));
                System.out.print(", count: " + rs.getInt("count"));
                dataCountNo2.add(rs.getInt("count"));
                dataCountDate2.add(rs.getString("sendDate"));
            }
            dataCountNo =dataCountNo2;
            dataCountDate=dataCountDate2;
            maxDataLength = Collections.max(dataCountNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping  ("/tera-home-dashboard")
    public String getTeraHome(java.sql.Date sendDate, Model model){
        List<Integer> getCounts = new ArrayList<>();
        List<Integer> getCount = new ArrayList<>();
        List<String> getDataByDate= new ArrayList<>();
        DataQuery();
        model.addAttribute("messagesCount", GlobalData.comments.size());


        List<Long> countcomments= Collections.singletonList(commentRepository.count());
    /*    List<Long> monounsaturated= Collections.singletonList(pageVisitsRepository.count());*/

        List<Timestamp> timeSentList = commentService.getAllComments().stream().map(x-> x.getSendTime()).collect(Collectors.toList());

        model.addAttribute("countcomment",commentRepository.count());
        /*model.addAttribute("sendTime", pageVisitsRepository.find);*/

        model.addAttribute("countcomment",commentRepository.count());
        model.addAttribute("sessionsNo",pageVisitsRepository.count());
       /* List<Timestamp> getsendTime = pageVisitsRepository.findAll().stream().map(x-> x.getSendDate()).collect(Collectors.toList());*/
         List<java.sql.Date> getsendTime = pageVisitsRepository.findAll().stream().map(x-> x.getSendDate()).collect(Collectors.toList());
        List<Long> getCreationTimes = pageVisitsRepository.findAll().stream().map(x-> x.getCreationTime()).collect(Collectors.toList());
        //List<Long> getCounts = pageVisitsRepository.findAll().stream().map(x-> x.getCount()).collect(Collectors.toList());
       //List<Long> getCount= Collections.singletonList(pageVisitService.getCountByDate());
       //List<java.sql.Date> getDateBydate= Collections.singletonList(pageVisitService.getDateByDate());
         getCounts = dataCountNo;
         getCount = dataCountNo;
         getDataByDate= dataCountDate;
        getMaxData = maxDataLength;

        model.addAttribute("sendTime", getsendTime);
    /*    model.addAttribute("countcomments",countcomments);*/
        model.addAttribute("count",getCounts);
        model.addAttribute("getMaxData",getMaxData);
        model.addAttribute("creationtimes", getCreationTimes);
        model.addAttribute("getcount", pageVisitService.getCountByDate());
        model.addAttribute("getCounts",getCount);
        model.addAttribute("getDateBydate",pageVisitService.getDateByDate(sendDate));
        model.addAttribute("getDataByDate",getDataByDate);

        System.out.println("Hiiii - "+ getMaxData);
        return "tera-home";
    }

    @GetMapping("/adminViewComment")
    public String getComments(Model model) {
        model.addAttribute("comment", commentService.getAllComments());
        model.addAttribute("dto",new Dto());
       /* CopyComments copyComment= (CopyComments) copyCommentService.findCopyCommentsById(id);*/

        return "comments";
    }

    @GetMapping("/adminViewComment/delete/{id}")
    public String deleteComment(@PathVariable int id){
        commentService.deleteCommentById(id);
        return "redirect:/adminViewComment";
    }


   /* @GetMapping("/adminViewComment/update/{id}")
    public String updateComment(@PathVariable int id,Model model){
        Optional<Comment> comment = commentService.getCommentById(id);
        if (comment.isPresent())
        {
            model.addAttribute("categories",comment.get());
            return "commentAdd";
        }else
            return "404";
    }*/


    /*@GetMapping("/commentAdd")
    public String getCommentAdd(Model model){
        model.addAttribute("dto",new Dto());
        return "commentAdd";
    }*/


    @GetMapping("/contactUs/update/{id}")
    public String updateProductGet(@PathVariable int id, Model model){
        Comment comment =commentService.getCommentById(id).get();
        Dto dto =new Dto();
        comment.setId(dto.getId());
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setPhone(dto.getPhone());
        comment.setCountry(dto.getCountry());
        comment.setMessage(dto.getMessage());
        comment.setSendTime(new Timestamp(new Date().getTime()));
        model.addAttribute("dto", dto);
        model.addAttribute("dto",new Dto());
        return "commentAdd";
    }

   /*@PostMapping("/POST")
    public String getPageVisitsInDto(@ModelAttribute("pageVisitsDto") PageVisitsDto pageVisitsDto,@PathVariable(value = "id")long id,@PathVariable Long SessionId,@RequestParam("msg") String msg, HttpServletRequest request, Model model){

       List<String> msgs = (List<String>) request.getAttribute("MY_SESSION_MESSAGES");
       if (msgs == null) {
           msgs = new ArrayList<>();
           request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
       }

       msgs.add(msg);
       request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);

       HttpSession session= request.getSession();



      return "redirect:/";*/

    /*}*/








}
