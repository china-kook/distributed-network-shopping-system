package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

/**
 * 内容管理Controller
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * @version 1.0
 */
@Controller
public class ContentController {

    @Resource
    private ContentService contentService;

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addContent(TbContent content) {
        //调用服务把内容数据保存到数据库
        E3Result e3Result = contentService.addContent(content);
        return e3Result;
    }

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
        EasyUIDataGridResult result = contentService.getContentList(categoryId, page, rows);
        return result;
    }

    @RequestMapping(value = "/rest/content/edit", method = RequestMethod.POST)
    @ResponseBody
    public E3Result updateContent(TbContent content) {
        E3Result result = contentService.updateContent(content);
        return result;
    }

    @RequestMapping(value = "/content/delete", method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteContent(Long ids) {
        E3Result result = contentService.deleteContent(ids);
        return result;
    }
}
