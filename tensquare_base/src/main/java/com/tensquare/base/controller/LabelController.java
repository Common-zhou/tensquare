package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2021-05-23 16:48
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询全部列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * 根据ID查询标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Label> findById(@PathVariable String id) {
        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result labelSearch(@RequestBody Label label) {
        List<Label> labels = labelService.search(label);

        return Result.success(labels);
    }

    //search/{page}/{size}
    @RequestMapping(value = "search/{page}/{size}", method = RequestMethod.POST)
    public Result labelPageSearch(@RequestBody Label label,
                                  @PathVariable("page") int page,
                                  @PathVariable("size") int size) {
        Page<Label> pageRes = labelService.searchPage(label, page, size);

        return Result.success(new PageResult<>(pageRes.getTotalElements(), pageRes.getContent()));
    }

}
