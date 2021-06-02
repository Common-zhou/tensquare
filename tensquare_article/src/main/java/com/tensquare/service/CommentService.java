package com.tensquare.service;

import com.tensquare.dao.CommentDao;
import com.tensquare.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @author zhoubing
 * @date 2021-06-02 10:25
 */
@Service
public class CommentService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CommentDao commentDao;

    public void add(Comment comment){
        comment.set_id( idWorker.nextId()+"" );
        commentDao.save(comment);
    }

    public List<Comment> findByArticleid(String articleid){
        return commentDao.findByArticleid(articleid);
    }

}
