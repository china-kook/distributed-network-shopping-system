package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

public interface ContentCategoryService {

    /**
     * 获取内容分类
     *
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getContentCatList(long parentId);

    /**
     * 添加内容分类
     *
     * @param parentId
     * @param name
     * @return
     */
    E3Result addContentCategory(long parentId, String name);

    /**
     * 重命名内容分类节点
     * @param id
     * @param name
     * @return
     */
    E3Result updateContentCategory(long id, String name);

    /**
     * 删除内容分类
     * @param id
     * @return
     */
    E3Result deleteContentCategory(long id);

}
