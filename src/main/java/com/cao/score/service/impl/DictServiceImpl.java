package com.cao.score.service.impl;

import com.cao.score.dao.DictDao;
import com.cao.score.entity.Dict;
import com.cao.score.service.DictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Dict)表服务实现类
 *
 * @author makejava
 * @since 2022-01-29 17:21:15
 */
@Service("dictService")
public class DictServiceImpl implements DictService {
    @Resource
    private DictDao dictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dict queryById(Long id) {
        return this.dictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Dict> queryAllByLimit(int offset, int limit) {
        return this.dictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<Dict> queryAllDict(Map<String,Object> dict) {
        return this.dictDao.queryAll(dict);
    }

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    @Override
    public Dict insert(Dict dict) {
        this.dictDao.insert(dict);
        return dict;
    }

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    @Override
    public Dict update(Dict dict) {
        this.dictDao.update(dict);
        return this.queryById(dict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dictDao.deleteById(id) > 0;
    }
}