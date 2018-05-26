package cn.e3mall.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
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

    @Resource
    private JedisClient jedisClient;

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    /**
     * 后台管理系统内容管理列表查询
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
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

        //缓存同步,删除缓存中对应的数据
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());

        return E3Result.ok();
    }

    /**
     * 根据内容分类id查询内容列表
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {

        //查询缓存
        try {
            //如果缓存中有直接响应结果
            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果没有查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

        //把结果添加到缓存
        try {
            jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public E3Result updateContent(TbContent content) {

        content.setUpdated(new Date());
        contentMapper.updateByPrimaryKeyWithBLOBs(content);

        //缓存同步,删除缓存中对应的数据
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());

        return E3Result.ok();
    }

    @Override
    public E3Result deleteContent(long ids) {

        TbContent content =  contentMapper.selectByPrimaryKey(ids);

        String cid = content.getCategoryId().toString();

        contentMapper.deleteByPrimaryKey(ids);

        //缓存同步,删除缓存中对应的数据
        jedisClient.hdel(CONTENT_LIST, cid);

        return E3Result.ok();
    }

}
