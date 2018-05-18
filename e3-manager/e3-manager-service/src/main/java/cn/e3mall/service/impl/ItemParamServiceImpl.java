package cn.e3mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbItemParamExMapper;
import cn.e3mall.mapper.TbItemParamMapper;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.pojo.TbItemParamModel;
import cn.e3mall.service.ItemParamService;

/**
 * 商品规格参数模板管理
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Resource
    private TbItemParamMapper itemParamMapper;

    @Resource
    private TbItemParamExMapper itemParamExMapper;

    @Override
    public E3Result getItemParamByCid(long cid) {
        TbItemParamExample example = new TbItemParamExample();

        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

        //判断是否查询到结果
        if (list != null && list.size() > 0) {
            return E3Result.ok(list.get(0));
        }

        return E3Result.ok();
    }

    /**
     * 商品规格参数列表查询
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {

        //查询商品列表
        TbItemParamExample example = new TbItemParamExample();

        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItemParamModel> list = itemParamExMapper.selectItemParamList();

        //创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);

        //取记录总条数
        PageInfo<TbItemParamModel> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public E3Result insertItemParam(TbItemParam itemParam) {
        //补全 pojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());

        //插入到规格参数模板表
        itemParamMapper.insert(itemParam);

        return E3Result.ok();
    }

    @Override
    public E3Result deleteItemParam(long ids) {

        itemParamMapper.deleteByPrimaryKey(ids);

        return E3Result.ok();
    }
}