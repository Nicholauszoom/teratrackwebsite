package com.example.demo.Service;

import com.example.demo.model.Comment;
import com.example.demo.model.PageVisits;
import com.example.demo.repository.CopyCommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyCommentService {
    @Autowired
    CopyCommentRepo copyCommentRepo;

   /* public List<Comment> findCopyCommentsById(long id) { return copyCommentRepo.moveComments(id); }*/
}
