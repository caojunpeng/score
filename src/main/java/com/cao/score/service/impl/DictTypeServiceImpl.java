package com.cao.score.service.impl;

import com.cao.score.dao.DictTypeDao;
import com.cao.score.entity.DictType;
import com.cao.score.service.DictTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DictType)表服务实现类
 *
 * @author makejava
 * @since 2022-01-20 15:08:43
 */
@Service("dictTypeService")
public class DictTypeServiceImpl implements DictTypeService {
    @Resource
    private DictTypeDao dictTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DictType queryById(Long id) {
        return this.dictTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DictType> queryAllByLimit(int offset, int limit) {
        return this.dictTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dictType 实例对象
     * @return 实例对象
     */
    @Override
    public DictType insert(DictType dictType) {
        this.dictTypeDao.insert(dictType);
        return dictType;
    }

    /**
     * 修改数据
     *
     * @param dictType 实例对象
     * @return 实例对象
     */
    @Override
    public DictType update(DictType dictType) {
        this.dictTypeDao.update(dictType);
        return this.queryById(dictType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dictTypeDao.deleteById(id) > 0;
    }
}