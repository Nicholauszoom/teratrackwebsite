package com.example.demo.Service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public void sendComment(Comment comment) {
        commentRepository.save(comment);
    }
    public List<Comment> getAllComments(){return commentRepository.findAll();}

    public void deleteCommentById(int id){commentRepository.deleteById(id);}


    public Optional<Comment> getCommentById(int id){return commentRepository.findById(id);}




}

