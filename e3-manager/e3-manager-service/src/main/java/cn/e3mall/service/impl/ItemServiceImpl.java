package cn.e3mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemDescExample;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 *
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper itemMapper;

    @Resource
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItem getItemById(long itemId) {
        //根据主键查询
        //TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);


        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();

        //设置查询条件
        criteria.andIdEqualTo(itemId);

        //执行查询
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        // 设置分页信息
        PageHelper.startPage(page, rows);
        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);

        // 创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        // 取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);

        //取总记录数
        long total = pageInfo.getTotal();
        result.setTotal(total);
        return result;
    }

    @Override
    public E3Result addItem(TbItem item, String desc) {
        // 生成商品id
        long itemId = IDUtils.genItemId();

        // 补全item的属性
        item.setId(itemId);

        // 1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());

        // 向商品表插入数据
        itemMapper.insert(item);

        // 创建一个商品描述表对应的pojo对象。
        TbItemDesc itemDesc = new TbItemDesc();

        // 补全属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());

        // 向商品描述表插入数据
        itemDescMapper.insert(itemDesc);

        // 返回成功
        return E3Result.ok();
    }

    @Override
    public E3Result deleteItem(long[] itemId) {

        // 删除商品表中的商品 -- item
        for (long item : itemId) {
            itemMapper.deleteByPrimaryKey(item);
        }

        // 删除商品描述表中的数据 -- itemDesc
        for (long itemDesc : itemId) {
            itemDescMapper.deleteByPrimaryKey(itemDesc);
        }
        return E3Result.ok();
    }

    @Override
    public E3Result getItemDesc(long itemId) {

        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();

        criteria.andItemIdEqualTo(itemId);

        List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);

        if (list != null && list.size() > 0) {
            return E3Result.ok(list.get(0));
        }

        return null;
    }

    @Override
    public E3Result updateItem(TbItem item, String desc) {

        // 获取商品 itemId
        long itemId = item.getId();

        TbItem updateItem = itemMapper.selectByPrimaryKey(itemId);

        updateItem.setCid(item.getCid());
        updateItem.setTitle(item.getTitle());
        updateItem.setSellPoint(item.getSellPoint());
        updateItem.setPrice(item.getPrice());
        updateItem.setNum(item.getNum());
        updateItem.setBarcode(item.getBarcode());
        updateItem.setImage(item.getImage());
        updateItem.setStatus((byte) 1);
        updateItem.setUpdated(new Date());

        // 更新商品信息
        itemMapper.updateByPrimaryKey(updateItem);

        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);

        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(new Date());

        // 更新商品描述
        itemDescMapper.updateByPrimaryKeyWithBLOBs(tbItemDesc);

        // 返回成功
        return E3Result.ok();
    }

    @Override
    public E3Result instockItem(long itemId) {

        TbItem item1 = itemMapper.selectByPrimaryKey(itemId);

        item1.setStatus((byte)2);
        item1.setUpdated(new Date());

        itemMapper.updateByPrimaryKey(item1);

        return E3Result.ok();
    }

    @Override
    public E3Result reshelfItem(long itemId) {
        TbItem item1 = itemMapper.selectByPrimaryKey(itemId);

        item1.setStatus((byte)1);
        item1.setUpdated(new Date());

        itemMapper.updateByPrimaryKey(item1);

        return E3Result.ok();
    }
}
















