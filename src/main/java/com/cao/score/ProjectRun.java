package com.cao.score;

import com.cao.score.service.StudentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 项目启动就会加载类
 */
@Component
public class ProjectRun implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(ProjectRun.class);

    @Resource
    private StudentsService studentsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("项目启动");
        studentsService.scoreRanking();
    }
}
