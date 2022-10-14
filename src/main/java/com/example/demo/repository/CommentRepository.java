package com.example.demo.repository;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface CommentRepository extends
        JpaRepository<Comment,Integer> {
   /* @Query(value = "SELECT count(id) FROM Comment where message = :message")*/
   /* long countByMessage(comment);*/


}
