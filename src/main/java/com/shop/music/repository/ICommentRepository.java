package com.shop.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, String> {

}
