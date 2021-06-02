package com.tensquare.dao;

import com.tensquare.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author zhoubing
 * @date 2021-06-02 10:24
 */
public interface CommentDao extends MongoRepository<Comment, String> {
    public List<Comment> findByArticleid(String articleid);
}
