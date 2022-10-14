package com.example.demo.repository;

import com.example.demo.model.Comment;
import com.example.demo.model.CopyComments;
import com.example.demo.model.PageVisits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyCommentRepo extends JpaRepository<CopyComments,Long> {
   /* @Query(nativeQuery = true, value = "INSERT into copy_comments (name, email, phone, country, messsage,"
            + "sendTime) SELECT name, email, phone, country, messsage,sendTime"
            + "from comment WHERE id > 1")
    List<Comment> moveComments(@Param("id") long id);*/
}
