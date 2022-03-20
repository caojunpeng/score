package com.cao.score.service.impl;

import com.cao.score.dao.ScoresDao;
import com.cao.score.entity.Scores;
import com.cao.score.service.ScoresService;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.utiles.ScoreStringUtils;
import com.cao.score.vo.ScoreParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            Scores s = new Scores();
            if(chineseScore!=0.0){//语文
                Integer subject = 1;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null){
                    s = new Scores(select.getId(),scoreParams.getStudentId(),chineseScore,subject,checkScoreState(chineseScore));
                }else{
                    s = new Scores(null,scoreParams.getStudentId(),chineseScore,subject,checkScoreState(chineseScore));
                }
                //保存或更新
                saveScore(s);
            }
            double mathScore = scoreParams.getMathScore();
            if(mathScore!=0.0){//数学
                Integer subject = 2;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null) {
                    s = new Scores(select.getId(), scoreParams.getStudentId(), mathScore, subject, checkScoreState(mathScore));
                }else {
                    s = new Scores(null, scoreParams.getStudentId(), mathScore, subject, checkScoreState(mathScore));
                }
                //保存或更新
                saveScore(s);
            }
            double englishScore = scoreParams.getEnglishScore();
            if(englishScore!=0.0){//英语
                Integer subject = 3;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null) {
                 s =new Scores(select.getId(),scoreParams.getStudentId(),englishScore,subject,checkScoreState(englishScore));
                }else {
                    s =new Scores(null,scoreParams.getStudentId(),englishScore,subject,checkScoreState(englishScore));
                }
                //保存或更新
                saveScore(s);
            }
            double politicsScore = scoreParams.getPoliticsScore();
            if(politicsScore!=0.0){//政治成绩
                Integer subject = 4;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null) {
                 s =new Scores(select.getId(),scoreParams.getStudentId(),politicsScore,subject,checkScoreState(politicsScore));
                }else {
                    s =new Scores(null,scoreParams.getStudentId(),politicsScore,subject,checkScoreState(politicsScore));
                }
                //保存或更新
                saveScore(s);
            }
            double historyScore = scoreParams.getHistoryScore();
            if(historyScore!=0.0){//历史成绩
                Integer subject = 5;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null) {
                 s =new Scores(select.getId(),scoreParams.getStudentId(),historyScore,subject,checkScoreState(historyScore));
                }else {
                    s =new Scores(null,scoreParams.getStudentId(),historyScore,subject,checkScoreState(historyScore));
                }
                //保存或更新
                saveScore(s);
            }
            double geographyScore = scoreParams.getGeographyScore();
            if(geographyScore !=0.0){//geographyScore
                Integer subject = 6;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null) {
                 s =new Scores(select.getId(),scoreParams.getStudentId(),geographyScore,subject,checkScoreState(geographyScore));
                }else {
                    s =new Scores(null,scoreParams.getStudentId(),geographyScore,subject,checkScoreState(geographyScore));
                }
                //保存或更新
                saveScore(s);
            }
            double biologicalScore = scoreParams.getBiologicalScore();
            if(biologicalScore !=0.0){//biologicalScore
                Integer subject = 7;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null) {
                 s =new Scores(select.getId(),scoreParams.getStudentId(),biologicalScore,subject,checkScoreState(biologicalScore));
                }else {
                    s =new Scores(null,scoreParams.getStudentId(),biologicalScore,subject,checkScoreState(biologicalScore));
                }
                //保存或更新
                saveScore(s);
            }
            double physicalScore = scoreParams.getPhysicalScore();
            if(physicalScore !=0.0){//物理成绩
                Integer subject = 8;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null) {
                 s =new Scores(select.getId(),scoreParams.getStudentId(),physicalScore,subject,checkScoreState(physicalScore));
                }else {
                    s =new Scores(null,scoreParams.getStudentId(),physicalScore,subject,checkScoreState(physicalScore));
                }
                //保存或更新
                saveScore(s);
            }
            double chemicalScore = scoreParams.getChemicalScore();
            if(chemicalScore !=0.0){//化学成绩
                Integer subject = 9;
                scoreParams.setSubject(subject);
                Scores select = scoresDao.queryOneByScoreParams(scoreParams);
                //获取跟新或新增队想
                if (select!=null) {
                 s =new Scores(select.getId(),scoreParams.getStudentId(),chemicalScore,subject,checkScoreState(chemicalScore));
                }else {
                    s =new Scores(null,scoreParams.getStudentId(),chemicalScore,subject,checkScoreState(chemicalScore));
                }
                //保存或更新
                saveScore(s);
            }
        }catch (Exception e){
            logger.error("成绩信息录入异常,异常信息:"+e.getMessage(),e);
        }
        return null;
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