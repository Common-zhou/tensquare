package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @author zhoubing
 * @date 2021-05-23 17:10
 */
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据ID查询标签
     *
     * @return
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 增加标签
     *
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");//设置ID
        labelDao.save(label);
    }

    /**
     * 修改标签
     *
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 删除标签
     *
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> search(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> predicatesList = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate labelNamePredicate = cb.like(root.get("labelname").as(String.class),
                        "%" + label.getLabelname() + "%");
                    predicatesList.add(labelNamePredicate);
                }

                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate statePredicate = cb.equal(root.get("state").as(String.class),
                        label.getState());
                    predicatesList.add(statePredicate);
                }

                if (label.getRecommend() != null && !"".equals(label.getRecommend())) {
                    Predicate recommendPredicate = cb.equal(root.get("recommend").as(String.class),
                        label.getRecommend());
                    predicatesList.add(recommendPredicate);
                }
                Predicate[] predicates = new Predicate[predicatesList.size()];
                Predicate total = cb.and(predicatesList.toArray(predicates));

                return total;
            }
        });
    }

    public Page<Label> searchPage(Label label, int page, int size) {
        Specification<Label> specification = new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> predicatesList = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate labelNamePredicate = cb.like(root.get("labelname").as(String.class),
                        "%" + label.getLabelname() + "%");
                    predicatesList.add(labelNamePredicate);
                }

                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate statePredicate = cb.equal(root.get("state").as(String.class),
                        label.getState());
                    predicatesList.add(statePredicate);
                }

                if (label.getRecommend() != null && !"".equals(label.getRecommend())) {
                    Predicate recommendPredicate = cb.equal(root.get("recommend").as(String.class),
                        label.getRecommend());
                    predicatesList.add(recommendPredicate);
                }
                Predicate[] predicates = new Predicate[predicatesList.size()];
                Predicate total = cb.and(predicatesList.toArray(predicates));

                return total;
            }
        };
        return labelDao.findAll(specification, new PageRequest(page - 1, size));
    }
}
