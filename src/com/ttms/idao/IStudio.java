package com.ttms.idao;



// 对演出厅的增删改查接口定义

import com.ttms.model.Studio;

import java.util.ArrayList;

public interface IStudio
{
    // 增
    public boolean insert(Studio studio);

    // 删
    public boolean delete(int studio_id);

    // 改
    public boolean update(Studio studio);

    // 查所有演出厅(一般用于和界面交互)
    public ArrayList<Studio> findStudioAll();

    // 根据演出厅名查(一般用于和界面交互)
    public ArrayList<Studio> findStudioByName(String studioName);

    // 根据演出厅id查(一般用于数据内部关联操作)
    public Studio findStudioById(int studio_id);
}
