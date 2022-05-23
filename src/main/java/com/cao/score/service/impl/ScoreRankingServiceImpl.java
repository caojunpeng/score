package com.cao.score.service.impl;

import com.cao.score.entity.ScoreRanking;
import com.cao.score.dao.ScoreRankingDao;
import com.cao.score.service.ScoreRankingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (ScoreRanking)表服务实现类
 *
 * @author makejava
 * @since 2022-04-28 09:46:39
 */
@Service("scoreRankingService")
public class ScoreRankingServiceImpl implements ScoreRankingService {
    @Resource
    private ScoreRankingDao scoreRankingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ScoreRanking queryById(Long id) {
        return this.scoreRankingDao.queryById(id);
    }

    @Override
    public List<ScoreRanking> queryByMap(Map<String, Object> map) {
        return this.scoreRankingDao.queryByMap(map);
    }

    /**
     * 新增数据
     *
     * @param scoreRanking 实例对象
     * @return 实例对象
     */
    @Override
    public ScoreRanking insert(ScoreRanking scoreRanking) {
        this.scoreRankingDao.insert(scoreRanking);
        return scoreRanking;
    }

    /**
     * 修改数据
     *
     * @param scoreRanking 实例对象
     * @return 实例对象
     */
    @Override
    public ScoreRanking update(ScoreRanking scoreRanking) {
        this.scoreRankingDao.update(scoreRanking);
        return this.queryById(scoreRanking.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.scoreRankingDao.deleteById(id) > 0;
    }
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteTable() {
        return this.scoreRankingDao.deleteTable() > 0;
    }
}
