package com.cao.score.service.impl;

import com.cao.score.dao.ScoreNumberDao;
import com.cao.score.entity.ScoreNumber;
import com.cao.score.service.ScoreNumberService;
import com.cao.score.utiles.ScoreDateUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ScoreParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 考试场次(ScoreNumber)表服务实现类
 *
 * @author makejava
 * @since 2022-04-11 10:45:31
 */
@Service("scoreNumberService")
public class ScoreNumberServiceImpl implements ScoreNumberService {
    @Resource
    private ScoreNumberDao scoreNumberDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ScoreNumber queryById(Long id) {
        return this.scoreNumberDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ScoreNumber> queryAllByLimit(int offset, int limit) {
        return this.scoreNumberDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param scoreNumber 实例对象
     * @return 实例对象
     */
    @Override
    public ScoreNumber insert(ScoreNumber scoreNumber) {
        this.scoreNumberDao.insert(scoreNumber);
        return scoreNumber;
    }

    /**
     * 修改数据
     *
     * @param scoreNumber 实例对象
     * @return 实例对象
     */
    @Override
    public ScoreNumber update(ScoreNumber scoreNumber) {
        this.scoreNumberDao.update(scoreNumber);
        return this.queryById(scoreNumber.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.scoreNumberDao.deleteById(id) > 0;
    }

    @Override
    public DataTablesResult<ScoreNumber> scoreNumberDatas(ObjectParams objectParams) {
        PageHelper.offsetPage(objectParams.getStart(), objectParams.getLength());
        List<ScoreNumber> scoreNumberDatas = scoreNumberDao.getScoresNumberDatas(objectParams);
        if(!scoreNumberDatas.isEmpty()){
            for (ScoreNumber scoreNumberData : scoreNumberDatas) {
                Date scoreTime = scoreNumberData.getScoreTime();
                if(scoreTime!=null){
                    scoreNumberData.setScoreTimeStr(ScoreDateUtils.dateToStr(scoreTime,ScoreDateUtils.format_day));
                }
            }
        }
        PageInfo<ScoreNumber> page = new PageInfo<ScoreNumber>(scoreNumberDatas);
        DataTablesResult<ScoreNumber> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(page.getList());
        dataTablesResult.setRecordsFiltered(page.getTotal());
        dataTablesResult.setRecordsTotal(page.getTotal());
        return dataTablesResult;
    }
}