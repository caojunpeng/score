package com.cao.score.service.impl;

import com.cao.score.dao.GradeClassDao;
import com.cao.score.entity.GradeClass;
import com.cao.score.service.GradeClassService;
import com.cao.score.vo.ObjectParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (GradeClass)表服务实现类
 *
 * @author makejava
 * @since 2022-03-02 14:44:55
 */
@Service("gradeClassService")
public class GradeClassServiceImpl implements GradeClassService {
    @Resource
    private GradeClassDao gradeClassDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GradeClass queryById(Long id) {
        return this.gradeClassDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<GradeClass> queryAllByLimit(int offset, int limit) {
        return this.gradeClassDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param gradeClass 对象参数
     * @return 对象列表
     */
    @Override
    public List<GradeClass> queryAllByMap(Map<String,Object> gradeClass) {
        return this.gradeClassDao.queryAllByMap(gradeClass);
    }

    /**
     * 新增数据
     *
     * @param gradeClass 实例对象
     * @return 实例对象
     */
    @Override
    public GradeClass insert(GradeClass gradeClass) {
        this.gradeClassDao.insert(gradeClass);
        return gradeClass;
    }

    /**
     * 修改数据
     *
     * @param gradeClass 实例对象
     * @return 实例对象
     */
    @Override
    public GradeClass update(GradeClass gradeClass) {
        this.gradeClassDao.update(gradeClass);
        return this.queryById(gradeClass.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.gradeClassDao.deleteById(id) > 0;
    }
}