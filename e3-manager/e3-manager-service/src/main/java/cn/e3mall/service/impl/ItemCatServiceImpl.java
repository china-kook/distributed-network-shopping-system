package cn.e3mall.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.service.ItemCatService;

/**
 * Title: ItemCatServiceImpl
 * Description: 商品分类管理
 * Author: ikook
 * Create: 2018-05-12 22:34
 * Version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {

        // 根据 parentId 查询子节点列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<>();

        //把列表转换成 EasyUITreeNode 列表
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            //设置属性
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent() ? "closed" : "open");
            //添加到结果列表
            resultList.add(node);
        }
        //返回结果
        return resultList;
    }
}
























