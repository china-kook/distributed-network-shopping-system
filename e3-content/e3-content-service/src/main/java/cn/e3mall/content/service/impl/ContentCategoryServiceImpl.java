package cn.e3mall.content.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;

/**
 * 内容分类管理Service
 * Title: ContentCategoryServiceImpl
 *
 * @version 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Resource
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {

        // 根据 parentid 查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();

        //设置查询条件
        criteria.andParentIdEqualTo(parentId);

        //执行查询
        List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);

        //转换成 EasyUITreeNode 的列表
        List<EasyUITreeNode> nodeList = new ArrayList<>();

        for (TbContentCategory tbContentCategory : catList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");

            //添加到列表
            nodeList.add(node);
        }

        return nodeList;
    }

    @Override
    public E3Result addContentCategory(long parentId, String name) {

        //创建一个tb_content_category表对应的pojo对象
        TbContentCategory contentCategory = new TbContentCategory();

        //设置pojo的属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);

        //1(正常),2(删除)
        contentCategory.setStatus(1);

        //默认排序就是1
        contentCategory.setSortOrder(1);

        //新添加的节点一定是叶子节点
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());

        //插入到数据库
        contentCategoryMapper.insert(contentCategory);

        //判断父节点的isparent属性。如果不是true改为true
        //根据parentid查询父节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);

            //更新到数数据库
            contentCategoryMapper.updateByPrimaryKey(parent);
        }

        //返回结果，返回E3Result，包含pojo
        return E3Result.ok(contentCategory);
    }

    @Override
    public E3Result updateContentCategory(long id, String name) {

        TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
        category.setName(name);
        contentCategoryMapper.updateByPrimaryKey(category);

        return E3Result.ok();
    }

    @Override
    public E3Result deleteContentCategory(long id) {

        deleteCategoryAndChildNode(id);

        return E3Result.ok();
    }

    /**
     * 递归删除
     * @param id
     */
    public void deleteCategoryAndChildNode(long id) {
        // 获取要删除的 Category
        TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);

        // 判断是否为父节点
        if (category.getIsParent()) {
            // 获取所有该节点的子节点
            List<TbContentCategory> list = getChildNodeList(id);

            // 删除所有孩子节点
            for (TbContentCategory category1 : list) {
                deleteCategoryAndChildNode(category1.getId());
            }
        }

        // 判断父节点下是否还有其他子节点
        if (getChildNodeList(category.getParentId()).size() == 1) {
            // 没有则将父节点标记为子节点
            TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(category.getParentId());
            parentCategory.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parentCategory);
        }

        // 删除本节点
        contentCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取所有该节点下的子节点
     * @param id
     * @return
     */
    public List<TbContentCategory> getChildNodeList(long id) {
        // 查询所有父节点为传入 id 的节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);

        //返回所有符合要求的节点
        return contentCategoryMapper.selectByExample(example);
    }

}
