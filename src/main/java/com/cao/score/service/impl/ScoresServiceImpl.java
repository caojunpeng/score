package com.cao.score.service.impl;

import com.cao.score.dao.ScoresDao;
import com.cao.score.entity.GradeClass;
import com.cao.score.entity.Scores;
import com.cao.score.entity.Students;
import com.cao.score.service.ScoresService;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.utiles.ScoreStringUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ScoreParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 成绩表(Scores)表服务实现类
 *
 * @author makejava
 * @since 2022-01-28 10:33:14
 */
@Service("scoresService")
public class ScoresServiceImpl implements ScoresService {

    private static Logger logger = LoggerFactory.getLogger(ScoresServiceImpl.class);

    //分数线
    private static Integer minimumPassingScore = 60;

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

    @Override
    public Scores saveScore(Scores scores) {
        if(scores.getId()!=null){
            this.scoresDao.update(scores);
        }else{
            this.scoresDao.insert(scores);
        }
        return scores;
    }

    @Override
    public DataTablesResult<ScoreParams> getScoresInfoDatas(ObjectParams objectParams) {
        List<ScoreParams> studentInfoDatas = scoresDao.getScoresInfoDatas(objectParams);
        DataTablesResult<ScoreParams> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(studentInfoDatas);
        dataTablesResult.setRecordsFiltered(studentInfoDatas.size());
        dataTablesResult.setRecordsTotal(studentInfoDatas.size());
        return dataTablesResult;
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

    @Override
    public String saveScoreByParams(ScoreParams scoreParams) {
        try{
            if(ScoreStringUtils.isBlank(scoreParams.getStudentId())){
                return ResponseUtil.printFailJson(ResponseUtil.PARAMS_ERROR,"学号为空");
            }
            Scores scores = new Scores();
            scores.setStudentId(scoreParams.getStudentId());
            double chineseScore = scoreParams.getChineseScore();
            saveScoreInfo(chineseScore,scoreParams,1);//语文
            double mathScore = scoreParams.getMathScore();
            saveScoreInfo(mathScore,scoreParams,2);//数学
            double englishScore = scoreParams.getEnglishScore();
            saveScoreInfo(englishScore,scoreParams,3);//英语
            double politicsScore = scoreParams.getPoliticsScore();
            saveScoreInfo(politicsScore,scoreParams,4);//政治成就
            double historyScore = scoreParams.getHistoryScore();
            saveScoreInfo(historyScore,scoreParams,5);//历史成绩
            double geographyScore = scoreParams.getGeographyScore();
            saveScoreInfo(geographyScore,scoreParams,6);
            double biologicalScore = scoreParams.getBiologicalScore();
            saveScoreInfo(biologicalScore,scoreParams,7);
            double physicalScore = scoreParams.getPhysicalScore();
            saveScoreInfo(physicalScore,scoreParams,8);
            double chemicalScore = scoreParams.getChemicalScore();
            saveScoreInfo(chemicalScore,scoreParams,9);
            return ResponseUtil.printJson("成绩录入成功",null);
        }catch (Exception e){
            logger.error("成绩信息录入异常,异常信息:"+e.getMessage(),e);
            return ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"服务器异常");
        }
    }

    private void saveScoreInfo(double score,ScoreParams scoreParams,Integer subject){
        Scores s = new Scores();
        if(score !=0.0){//化学成绩
            scoreParams.setSubject(subject);
            Scores select = scoresDao.queryOneByScoreParams(scoreParams);
            //获取跟新或新增队想
            if (select!=null) {
                s =new Scores(select.getId(),scoreParams.getStudentId(),score,subject,checkScoreState(score));
            }else {
                s =new Scores(null,scoreParams.getStudentId(),score,subject,checkScoreState(score));
            }
            //保存或更新
            saveScore(s);
        }
    }

    /**
     * 及格状态
     * @return
     */
    private int checkScoreState(double score){
        if(score>=minimumPassingScore){
            return 1;
        }else {
            return 0;
        }
    }

}