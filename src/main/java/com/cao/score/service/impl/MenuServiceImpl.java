package com.cao.score.service.impl;

import com.cao.score.dao.MenuDao;
import com.cao.score.entity.Menu;
import com.cao.score.entity.RoleMenu;
import com.cao.score.service.MenuService;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 17:46:12
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public Menu queryById(Long menuId) {
        return this.menuDao.queryById(menuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Menu> queryAllByLimit(int offset, int limit) {
        return this.menuDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Menu> getMenusByMaps(Map<String, Object> map) {
        return this.menuDao.getMenusByMaps(map);
    }

    @Override
    public Menu getMenuOneByMaps(Map<String, Object> map) {
        return this.menuDao.getMenuOneByMaps(map);
    }

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu insert(Menu menu) {
        this.menuDao.insert(menu);
        return menu;
    }

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu update(Menu menu) {
        this.menuDao.update(menu);
        return this.queryById(menu.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.menuDao.deleteById(menuId) > 0;
    }

    @Override
    public DataTablesResult<Menu> dataLists(ObjectParams params) {
        List<Menu> menu=menuDao.getList(params);
        DataTablesResult<Menu> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(menu);
        dataTablesResult.setRecordsFiltered(menu.size());
        dataTablesResult.setRecordsTotal(menu.size());
        return dataTablesResult;
    }
}