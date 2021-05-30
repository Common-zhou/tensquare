package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * recruit数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
	List<Recruit> findTopByStateOrderByCreatetimeDesc(String state);

	List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
