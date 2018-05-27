package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.service.SearchItemService;

/**
 * 导入商品数据到索引库
 * Title: SearchItemController
 * Version 1.0
 */
@Controller
public class SearchItemController {

    @Resource
    private SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result importItemList() {
        E3Result e3Result = searchItemService.importAllItems();
        return e3Result;

    }
}
