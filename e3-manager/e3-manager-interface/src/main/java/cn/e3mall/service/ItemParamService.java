package cn.e3mall.service;


import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItemParam;

public interface ItemParamService {

    E3Result getItemParamByCid(long cid);

    EasyUIDataGridResult getItemParamList(int page, int rows);

    E3Result insertItemParam(TbItemParam itemParam);

    E3Result deleteItemParam(long ids);

}