package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.service.ItemCatService;

/**
 * Title: ItemCatController
 * Description: 商品分类管理 Controller
 * Author: ikook
 * Create: 2018-05-12 22:45
 * Version 1.0
 */
@Controller
public class ItemCatController {

    @Resource
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {

        //调用服务查询节点列表
        List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
        return list;
    }

}