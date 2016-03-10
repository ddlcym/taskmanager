package com.changhong.user.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.user.domain.DepartmentCategory;
import com.changhong.user.repository.DepartmentDao;
import com.changhong.user.web.facade.assember.DepartmentWebAssember;
import com.changhong.user.web.facade.dto.DepartmentCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: di
 * Date: 16-3-1
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    public List<DepartmentCategoryDTO> obtainAllCategory() {
//        System.out.print("departmentDao is "+((null==departmentDao)?"null":"not null"));
        List<DepartmentCategory> departments = departmentDao.loadAllCategory();
        return DepartmentWebAssember.toDepartmentCategoryDTOList(departments);
    }

    /**
     * 获取支节点部门
     * @return
     */
    public List<DepartmentCategoryDTO> obtainBranchCategory() {
        List<DepartmentCategory> departments = departmentDao.loadAllCategory();
        List<DepartmentCategory> list = new ArrayList<DepartmentCategory>();
        list.addAll(departments);
        for(int i=0; i<departments.size(); i++){
            DepartmentCategory department=departments.get(i);
            if(null == department.getChildren()){
                  list.remove(department);
            }
        }
        return DepartmentWebAssember.toDepartmentCategoryDTOList(list);
    }

    public List<DepartmentCategoryDTO> obtainCategoryByLevel(String level) {
        List<DepartmentCategory> departments = departmentDao.loadDepartmentCategoryByLevel(level);
        return DepartmentWebAssember.toDepartmentCategoryDTOList(departments);
    }

    public void saveOrUpdateDepartmentCategory(int departmentId, String categoryName, String principleUser, String level,int parentId) {
        //To change body of implemented methods use File | Settings | File Templates.
         DepartmentCategory department = DepartmentWebAssember.toDepartmentDomain(departmentId, categoryName, principleUser, level,parentId);
         departmentDao.saveOrUpdate(department);

    }

    public DepartmentCategoryDTO obtainDepartmentCategoryById(int departmentId) {
        DepartmentCategory department = (DepartmentCategory)departmentDao.findById(departmentId,DepartmentCategory.class);
        return DepartmentWebAssember.toDepartmentCategoryDTO(department);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteDepartmentCategory(int departmentId) {
        if(departmentId <= 0 )return;

        DepartmentCategory department = (DepartmentCategory)departmentDao.findById(departmentId,DepartmentCategory.class);
         if(null  != department){
             departmentDao.delete(department);
         }
    }

    public boolean obtainDepartmentHasChildren(int departmentId) {

         if(departmentDao.getDepartmentChildrenSize(departmentId)>0)  {
             return true;
         }
        return false;
    }

    public boolean obtainDepartmentExist(int departmentId, String departmentName) {
        boolean isExist=false;
        if(departmentId > 0 && StringUtils.hasText(departmentName)){
             DepartmentCategory department = (DepartmentCategory)departmentDao.findById(departmentId,DepartmentCategory.class);
             if(department.getName().equals(departmentName)) {
                   isExist=true;
             }
        }
        return isExist;
    }

    public void changeDepartmentDetails(DepartmentCategoryDTO departmentCategoryDTO) {
    }

    public JSONArray obtainRecommendDepartments(String level) {
        List<DepartmentCategory> list = null;
        if (StringUtils.hasText(level) && !level.equals("LEVEL_FIRST")) {
            level=(level.equals("LEVEL_THIRD"))?"LEVEL_SECOND":"LEVEL_FIRST";
            list = departmentDao.loadDepartmentCategoryByLevel(level);
        }

        JSONArray array = new JSONArray();
        if (list != null) {
            for (DepartmentCategory dpt : list) {
                JSONObject o = new JSONObject();
                o.put("departmentId", dpt.getId());
                o.put("departmentName", dpt.getName());
                o.put("principleUser", dpt.getPrincipleUser());
                o.put("levelType", dpt.getLevelType());
                array.add(o);
            }
        }
        return array;
    }

}
