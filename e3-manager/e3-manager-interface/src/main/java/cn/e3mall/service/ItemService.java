package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;

public interface ItemService {

    TbItem getItemById(long itemId);

    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     * 添加商品
     */
    E3Result addItem(TbItem item, String desc);

    /**
     * 删除商品, 物理删除
     */
    E3Result deleteItem(long[] itemId);

    /**
     * 获取商品描述
     */
    E3Result getItemDesc(long itemId);

    /**
     * 编辑商品
     */
    E3Result updateItem(TbItem item, String desc);

    /**
     * 下架商品
     */
    E3Result instockItem(long itemId);

    /**
     * 上架商品
     */
    E3Result reshelfItem(long itemId);
}
