package com.cao.score.dao;

import com.cao.score.entity.Menu;
import com.cao.score.entity.Role;
import com.cao.score.vo.ObjectParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜单表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 17:46:12
 */
@Mapper
public interface MenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    Menu queryById(Long menuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Menu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过map查询菜单数组
     * @param map
     * @return
     */
    List<Menu> getMenusByMaps(Map<String, Object> map);
    /**
     * 通过map查询菜单数组
     * @param map
     * @return
     */
    Menu getMenuOneByMaps(Map<String, Object> map);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param menu 实例对象
     * @return 对象列表
     */
    List<Menu> queryAll(Menu menu);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Menu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Menu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Menu> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Menu> entities);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Long menuId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param params 实例对象
     * @return 对象列表
     */
    List<Menu> getList(ObjectParams params);

}