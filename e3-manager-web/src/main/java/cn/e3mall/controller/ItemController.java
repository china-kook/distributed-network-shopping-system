package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

/**
 * 商品管理 Controller
 * Title: ItemController
 * Version 1.0
 */
@Controller
public class ItemController {

    @Resource
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public @ResponseBody
    TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    /**
     * 查询商品列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //调用服务查询商品列表
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    /**
     * 商品添加功能
     */
    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addItem(TbItem item, String desc) {
        E3Result result = itemService.addItem(item, desc);
        return result;
    }

    /**
     * 编辑商品时的商品描述显示
     */
    @RequestMapping(value = "/rest/item/query/item/desc/{itemId}")
    @ResponseBody
    public E3Result getItemDesc(@PathVariable Long itemId) {
        E3Result result = itemService.getItemDesc(itemId);
        return result;
    }

    /**
     * 商品删除功能
     */
    @RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteItem(@RequestParam("ids") long[] itemId) {
        E3Result result = itemService.deleteItem(itemId);
        return result;
    }

    /**
     * 更新商品功能
     */
    @RequestMapping(value = "/rest/item/update", method = RequestMethod.POST)
    @ResponseBody
    public E3Result updateItem(TbItem item, String desc) {
        E3Result result = itemService.updateItem(item, desc);
        return result;
    }

    /**
     * 商品下架功能
     */
    @RequestMapping(value = "/rest/item/instock", method = RequestMethod.POST)
    @ResponseBody
    public E3Result instockItem(@RequestParam("ids") Long itemId) {
        E3Result result = itemService.instockItem(itemId);
        return result;
    }

    /**
     * 商品上架功能
     */
    @RequestMapping(value = "/rest/item/reshelf", method = RequestMethod.POST)
    @ResponseBody
    public E3Result reshelfItem(@RequestParam("ids") Long itemId) {
        E3Result result = itemService.reshelfItem(itemId);
        return result;
    }


}
