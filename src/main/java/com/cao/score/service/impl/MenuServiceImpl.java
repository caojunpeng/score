package com.cao.score.service.impl;

import ch.qos.logback.core.util.InvocationGate;
import com.cao.score.dao.MenuDao;
import com.cao.score.entity.Menu;
import com.cao.score.entity.RoleMenu;
import com.cao.score.entity.User;
import com.cao.score.service.MenuService;
import com.cao.score.service.RoleMenuService;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ZtreeObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Resource
    private RoleMenuService roleMenuService;

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
        PageHelper.offsetPage(params.getStart(), params.getLength());
        List<Menu> menu=menuDao.getList(params);
        PageInfo<Menu> page = new PageInfo<Menu>(menu);
        DataTablesResult<Menu> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(page.getList());
        dataTablesResult.setRecordsFiltered(page.getTotal());
        dataTablesResult.setRecordsTotal(page.getTotal());
        return dataTablesResult;
    }

    @Override
    public List<ZtreeObject> getZtreeObjectList(Long roleId) {
        List<ZtreeObject> ZtreeObjectList=  getMenuList();
        Map<String,Object> map=new HashMap<>();
        map.put("roleId",roleId);
        List<RoleMenu> menus =roleMenuService.queryByMap(map);
        if(!ZtreeObjectList.isEmpty()&&!menus.isEmpty()){
            List<Integer> menusIdList=new ArrayList<>();
            for (RoleMenu menu : menus) {
                menusIdList.add(menu.getMenuId());
            }
            for (ZtreeObject ztreeObject : ZtreeObjectList) {
                List<ZtreeObject> childrenList = ztreeObject.getChildren();
                if(menusIdList.contains(ztreeObject.getId())){
                    ztreeObject.setChecked(true);
                }
                if(!childrenList.isEmpty()){
                    for (ZtreeObject object : childrenList) {
                        if(menusIdList.contains(object.getId())){
                            object.setChecked(true);
                        }
                    }
                }
                ztreeObject.setChildren(childrenList);
            }
        }
        return ZtreeObjectList;
    }
    private List<ZtreeObject> getMenuList(){
        Map<String,Object> map = new HashMap<>();
        map.put("status",1);
        List<ZtreeObject> ZtreeObjectList = new ArrayList<>();
        List<ZtreeObject> secondMenus= new ArrayList<>();
        List<ZtreeObject> ThirdMmenus= new ArrayList<>();
        List<Menu> menus=menuDao.getMenusByMaps(map);
        if(!menus.isEmpty()){
            for (Menu menu : menus) {
                Integer parentId= -1;
                ZtreeObject ztreeObject = new ZtreeObject();
                if("main".equals(menu.getParentName())){
                    parentId=-2;
                    ztreeObject=new ZtreeObject(menu.getMenuTitle(),parentId,Integer.valueOf(menu.getMenuId()+""),false,null);
                    ThirdMmenus.add(ztreeObject);
                }else if("admin".equals(menu.getParentName())){
                    parentId=-3;
                    ztreeObject=new ZtreeObject(menu.getMenuTitle(),parentId,Integer.valueOf(menu.getMenuId()+""),false,null);
                    secondMenus.add(ztreeObject);
                }else{
                    ztreeObject=new ZtreeObject(menu.getMenuTitle(),parentId,Integer.valueOf(menu.getMenuId()+""),false,null);
                    ZtreeObjectList.add(ztreeObject);;
                }
            }
        }
        if(!ZtreeObjectList.isEmpty()){
            for (ZtreeObject firstMenu : ZtreeObjectList) {
                if(firstMenu.getId()==1){
                    firstMenu.setChildren(ThirdMmenus);
                }else if(firstMenu.getId()==6){
                    firstMenu.setChildren(secondMenus);
                }
            }
        }
        return ZtreeObjectList;
    }

}