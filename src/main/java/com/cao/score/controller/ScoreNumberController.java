package com.cao.score.controller;

import com.cao.score.entity.GradeClass;
import com.cao.score.entity.ScoreNumber;
import com.cao.score.service.ScoreNumberService;
import com.cao.score.service.ScoresService;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.utiles.ScoreDateUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ScoreParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考试场次(ScoreNumber)表控制层
 *
 * @author makejava
 * @since 2022-04-11 10:45:32
 */
@RestController
@RequestMapping("scoreNumber")
public class ScoreNumberController {

    private static Logger logger = LoggerFactory.getLogger(ScoreNumberController.class);

    /**
     * 服务对象
     */
    @Resource
    private ScoreNumberService scoreNumberService;

    @Resource
    private ScoresService scoresService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ScoreNumber selectOne(Long id) {
        return this.scoreNumberService.queryById(id);
    }
    /**
     * 跳转到考试场次管理
     * @return
     */
    @RequestMapping("/scoreNumberManagement")
    public ModelAndView scoreManagement(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/score/scoresNumberManagement");
        return modelAndView;
    }

    /**
     * 跳转到考试场次管理
     * @return
     */
    @RequestMapping("/saveScoreNumberPage")

    public ModelAndView saveScoreNumberPage(Long scoreNumberId){
        ModelAndView modelAndView=new ModelAndView();
        ScoreNumber scoreNumber = new ScoreNumber();
        if(scoreNumberId!=null){
            scoreNumber = scoreNumberService.queryById(scoreNumberId);
        }
        modelAndView.addObject("scoreNumber",scoreNumber);
        modelAndView.setViewName("/score/saveScoresNumber");
        return modelAndView;
    }

    /**
     * 获取考试场次信息列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/scoreNumberDatas")
    public DataTablesResult<ScoreNumber> scoreNumberDatas(ObjectParams objectParams){
        return scoreNumberService.scoreNumberDatas(objectParams);

    }

    /**
     * 获取考试场次信息列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveScoreNumber")
    public String saveScoreNumber(ScoreNumber scoreNumber){
        String result="";
        try {
            if(scoreNumber.getId()!=null){
                ScoreNumber one = scoreNumberService.queryById(scoreNumber.getId());
                one.setScoreName(scoreNumber.getScoreName());
                scoreNumberService.update(one);
            }else{
                String scoreNum = "";
                if(scoreNumber.getScoreTime()!=null){
                    scoreNum=ScoreDateUtils.dateToStr(scoreNumber.getScoreTime(),ScoreDateUtils.format_dayStr);

                }else{
                    scoreNum=ScoreDateUtils.dateToStr(new Date(),ScoreDateUtils.format_dayStr);
                }
                scoreNumber.setScoreNum(scoreNum);
                scoreNumberService.insert(scoreNumber);
            }
            result= ResponseUtil.printJson("更新成功",null);
        }catch (Exception e){
            logger.error("考试场次信息更新异常，异常信息："+e.getMessage(),e);
            result= ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"考试场次信息更新异常");
        }
        return result;
    }
    /**
     * 获取考试场次信息列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/delScoreNumber")
    public String delScoreNumber(Long id){
        String result="";
        try {
            scoreNumberService.deleteById(id);
            //删除本次考试所有学生成绩
            // TODO: 2022/4/13  
            result= ResponseUtil.printJson("删除成功",null);
        }catch (Exception e){
            logger.error("考试场次信息更新异常，异常信息："+e.getMessage(),e);
            result= ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"考试场次信息更新异常");
        }
        return result;
    }
}