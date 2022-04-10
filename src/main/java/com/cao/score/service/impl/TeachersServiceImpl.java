package com.cao.score.service.impl;

import com.cao.score.dao.TeachersDao;
import com.cao.score.entity.Dict;
import com.cao.score.entity.Students;
import com.cao.score.entity.Teachers;
import com.cao.score.service.DictService;
import com.cao.score.service.TeachersService;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师信息表(Teachers)表服务实现类
 *
 * @author makejava
 * @since 2022-01-28 10:36:02
 */
@Service("teachersService")
public class TeachersServiceImpl implements TeachersService {
    @Resource
    private TeachersDao teachersDao;

    @Resource
    private DictService dictService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Teachers queryById(Long id) {
        return this.teachersDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Teachers> queryAllByLimit(int offset, int limit) {
        return this.teachersDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param teachers 实例对象
     * @return 实例对象
     */
    @Override
    public Teachers insert(Teachers teachers) {
        this.teachersDao.insert(teachers);
        return teachers;
    }

    /**
     * 修改数据
     *
     * @param teachers 实例对象
     * @return 实例对象
     */
    @Override
    public Teachers update(Teachers teachers) {
        this.teachersDao.update(teachers);
        return this.queryById(teachers.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.teachersDao.deleteById(id) > 0;
    }

    @Override
    public DataTablesResult<Teachers> getTeacherInfoDatas(ObjectParams objectParams) {
        if(!objectParams.isExportType()) {
            PageHelper.offsetPage(objectParams.getStart(), objectParams.getLength());
        }
        List<Teachers> teachers = teachersDao.getTeacherInfoDatas(objectParams);
        if(!teachers.isEmpty()){
            for (Teachers teacher: teachers){
                Integer subject = teacher.getSubject();
                if(subject!=null){
                    Dict dict = dictService.selectOneByDictIdAndTypeId(subject, 1);
                    if(dict!=null){
                        teacher.setSubjectStr(dict.getDictName());
                    }
                }
            }
        }
        DataTablesResult<Teachers> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(teachers);
        dataTablesResult.setRecordsFiltered(teachers.size());
        dataTablesResult.setRecordsTotal(teachers.size());
        return dataTablesResult;
    }
}