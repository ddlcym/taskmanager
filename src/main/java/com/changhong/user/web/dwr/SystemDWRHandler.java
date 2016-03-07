package com.changhong.user.web.dwr;

import com.changhong.user.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("systemDWRHandler")
public class SystemDWRHandler {

    @Autowired
    private DepartmentService departmentService;

    public boolean obtainDepartmentHasChildren(int departmentId) {
        return departmentService.obtainDepartmentHasChildren(departmentId);
    }


}