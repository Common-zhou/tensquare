package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhoubing
 * @date 2021-05-23 17:09
 */
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {

}
