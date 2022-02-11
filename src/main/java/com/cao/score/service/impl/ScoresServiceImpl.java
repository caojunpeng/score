package com.cao.score.service.impl;

import com.cao.score.dao.ScoresDao;
import com.cao.score.entity.Scores;
import com.cao.score.service.ScoresService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 成绩表(Scores)表服务实现类
 *
 * @author makejava
 * @since 2022-01-28 10:33:14
 */
@Service("scoresService")
public class ScoresServiceImpl implements ScoresService {
    @Resource
    private ScoresDao scoresDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Scores queryById(Long id) {
        return this.scoresDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Scores> queryAllByLimit(int offset, int limit) {
        return this.scoresDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param scores 实例对象
     * @return 实例对象
     */
    @Override
    public Scores insert(Scores scores) {
        this.scoresDao.insert(scores);
        return scores;
    }

    /**
     * 修改数据
     *
     * @param scores 实例对象
     * @return 实例对象
     */
    @Override
    public Scores update(Scores scores) {
        this.scoresDao.update(scores);
        return this.queryById(scores.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.scoresDao.deleteById(id) > 0;
    }
}