package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbContent;

public interface ContentService {

    /**
     * 列表查询
     */
    EasyUIDataGridResult getContentList(long categoryId, int page, int rows);

    /**
     * 添加内容
     */
    E3Result addContent(TbContent content);

    /**
     * 根据内容分类id查询内容列表
     */
    List<TbContent> getContentListByCid(long cid);

    /**
     * 编辑内容
     */
    E3Result updateContent(TbContent content);

    /**
     * 删除内容
     */
    E3Result deleteContent(long ids);
}
