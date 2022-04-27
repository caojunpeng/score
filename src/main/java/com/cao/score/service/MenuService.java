package com.cao.score.service;

import com.cao.score.entity.Menu;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ZtreeObject;

import java.util.List;
import java.util.Map;

/**
 * 菜单表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 17:46:12
 */
public interface MenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    Menu queryById(Long menuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Menu> queryAllByLimit(int offset, int limit);
    /**
     * 查询多条数据
     *
     * @param map 参数
     * @return 对象列表
     */
    List<Menu> getMenusByMaps(Map<String,Object> map);
    /**
     * 查询一条数据
     *
     * @param map 参数
     * @return 对象列表
     */
    Menu getMenuOneByMaps(Map<String,Object> map);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);

    /**
     * 菜单table数据加载
     * @param params
     * @return
     */
    public DataTablesResult<Menu> dataLists(ObjectParams params);

    List<ZtreeObject> getZtreeObjectList(Long roleId);

}