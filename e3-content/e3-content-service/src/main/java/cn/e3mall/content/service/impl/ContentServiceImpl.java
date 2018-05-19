package cn.e3mall.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;

/**
 * 内容管理Service
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TbContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult getContentList(long categoryId, int page, int rows) {

        // 使用插件处理分页
        PageHelper.startPage(page, rows);

        // 查询 categoryId 相匹配的所有 TbContent
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);

        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

        // 获取共有多少页
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);

        // 创建 EasyUIDataGridResult 结果集
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public E3Result addContent(TbContent content) {
        //将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入到数据库
        contentMapper.insert(content);
        return E3Result.ok();
    }

    /**
     * 根据内容分类id查询内容列表
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    @Override
    public E3Result updateContent(TbContent content) {

        content.setUpdated(new Date());
        contentMapper.updateByPrimaryKeyWithBLOBs(content);

        return E3Result.ok();
    }

    @Override
    public E3Result deleteContent(long ids) {

        contentMapper.deleteByPrimaryKey(ids);

        return E3Result.ok();
    }

}
