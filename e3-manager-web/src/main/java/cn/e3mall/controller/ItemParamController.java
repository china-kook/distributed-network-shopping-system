package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.service.ItemParamService;

/**
 * 商品规格参数模板管理 Controller
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Resource
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    public @ResponseBody
    E3Result getItemParamByCid(@PathVariable Long itemCatId) {
        E3Result result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    @RequestMapping("/list")
    public @ResponseBody
    EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
        EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
        return result;
    }

    @RequestMapping("/save/{cid}")
    public @ResponseBody
    E3Result insertItemParam(@PathVariable Long cid, String paramData) {
        //创建pojo对象
        TbItemParam itemParam = new TbItemParam();

        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);

        E3Result result = itemParamService.insertItemParam(itemParam);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteItemParam(Long ids) {
        E3Result result = itemParamService.deleteItemParam(ids);
        return result;
    }

}