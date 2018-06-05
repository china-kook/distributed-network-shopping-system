<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${item.title } - 宜立方商城</title>
    <script>
        var _SF_CFG = {
            www_url: 'http://www.e3mall.cn',
            m_url: 'http://m.e3mall.cn',
            productId:${item.id},
            number: 1600229585,
            minBuy: 1,
            maxBuy: 99,
            presell: false,
            presellId: 0,
            parentId: 0,
            commentPage: 1,
            sdPage: 1,
            oneCategoryId: 6,
            twoCategoryId: 7321,
            threeCategoryId: 7331,
            sfprice: 0,
            price: 0,
            warehouse: 0,
            businessModel: 3,
            commentType: 0
        };
    </script>

    <script src="/js/jquery-1.5.1.min.js?v=20160713" type="text/javascript"></script>
    <script src="/js/jquery.alerts.js?v=20160713" type="text/javascript"></script>
    <script src="/js/common.js?v=20160713" type="text/javascript"></script>
    <script src="/js/cart.js?v=20160713" type="text/javascript"></script>
    <script src="/js/cloud-zoom.1.0.2.min.js?v=20160713" type="text/javascript"></script>
    <script src="/js/jquery.thickbox.js?v=20160713" type="text/javascript"></script>
    <script src="/js/goods.js?v=20160713" type="text/javascript"></script>
    <script src="/js/NewVersion.js?v=20160713" type="text/javascript"></script>
    <script src="/js/png.js?v=20160713" type="text/javascript"></script>
    <script src="/js/qiangGouPro.js?v=20160713" type="text/javascript"></script>
    <script src="/js/jquery.cookie.js?v=20160713" type="text/javascript"></script>
    <script src='/js/jquery.lazyload.js?v=20160713' type='text/javascript'></script>
    <script type="text/javascript" src="/js/jquery.qrcode.js?v=20160713"></script>
    <script type="text/javascript" src="/js/qrcode.js?v=20160713"></script>
    <script type="text/javascript" src="/js/cookie.js?v=20160416222"></script>
    <script type="text/javascript" src="/js/shadow.js?v=20160416"></script>
    <script src="/js/product.js?v=20160713" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/css/base_w1200.css?v=20160713">
    <link rel="stylesheet" type="text/css" href="/css/product.css?v=20160713">
    <link rel="stylesheet" type="text/css" href="/css/jquery.alerts.css?v=20160713"/>
    <link rel="stylesheet" type="text/css" href="/css/common.css?v=20160713"/>
</head>
<body>
<!-- header start -->
<jsp:include page="commons/header.jsp"/>
<!-- header end -->
<jsp:include page="commons/mainmenu.jsp"/>
<div class="linknav">
    <div class="breadcrumb"><strong><a
            href="javascript:void(0)">全部商品</a></strong><span> &gt; ${item.title }</span>
    </div>
</div>
<div class="pWrap">
    <div class="productIntro">
        <div class="pItems">
            <div class="pItemsMain">
                <div class="pItemsName">
                    <div class="cm">
                        <h1 id="base_name-sf">${item.title }</h1><br></br>
                        <strong id="adword-sf" title="" class="">${item.sellPoint }</strong>
                    </div>
                </div>
                <div class="pItemsPrice" id="price-sf">
                    <div class="priceBox">
                        <span class="dt">宜立方价：</span><span class="rmb">￥</span>
                        <strong class="price"><fmt:formatNumber groupingUsed="false"
                                                                maxFractionDigits="2"
                                                                minFractionDigits="2"
                                                                value="${item.price / 100 }"/></strong>
                    </div>
                    <div class="boxWb"></div>
                </div>
                <div class="clear"></div>
                <div id="presell-info-sf" class="pItemBook" style="display:none"></div>
                <div class="pItemsPromo" id="promotion-sf" style="display:none"></div>
                <div class="pItemsStock">
                    <div class="dt">送至：</div>
                    <div class="dd">
                        <div id="regionSf">
                            <div class="" id="store-selector">
                                <div class="text">
                                    <div title="山东省青岛市市北区<">山东省青岛市市北区</div>
                                    <b></b></div>

                                <div onclick="$('#store-selector').removeClass('hover')"
                                     class="close"></div>
                            </div>
                        </div>

                    </div>
                    <span class="clear"></span>
                </div>
                <div class="pItemsChoose">
                    <div class="chooseType">
                        <ul id="fatherson-sf"></ul>
                        <span class="clear"></span>
                    </div>
                    <div class="chooseBtns" id="buy-btn-sf">
                        <div class="pAmount">
                            <span><input type="text" id="number_${item.id}" class="text"
                                         value=""></span>
                            <span>
                                <a href="javascript:;" id="add-sell-num" class="p-add">+</a>
                                <a href="javascript:;" id="reduce-sell-num"
                                   class="p-reduce disable">-</a>
                            </span>
                        </div>
                        <div class="pBtn" id="cart-add-btn-sf"><a
                                onclick="cartAdd(${item.id}, 0, 1, 0, 1, this);"><b></b>加入购物车</a>
                        </div>

                        <span class="clear"></span>
                    </div>
                    <div class="finalBuy" id="finalbuy-sf" style="display:none"></div>
                </div>
            </div>
            <div class="pView">
                <div id="pView">
                    <div id="zoom-jpg" class="jqzoom">
                        <img alt="" width="330" height="330" src="${item.images[0] }"
                             jqimg="${item.images[0] }"/>
                    </div>
                    <div id="pic-list">
                        <a href="javascript:void(0);" class="btn-control disabled" id="btn-forward"><b></b></a>
                        <a href="javascript:void(0);" class="btn-control disabled"
                           id="btn-backward"><b></b></a>

                        <div class="pic-items"
                             style="position: absolute; width: 50px; height: 300px; overflow: hidden;">
                            <ul style="position: absolute; left: 0px; top: 0px; height: 240px;">
                                <c:forEach items="${item.images }" var="image">
                                    <li style="float: left;"><img title="${item.title } "
                                                                  alt="${item.title }"
                                                                  src="${image }"></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="pItemsSide">
            <div class="sideWrap">
                <div class="points">
                    <ul id="points-sf">
                        <li><img src="/images/productattr4.png"></li>
                        <li class="has_poptip attr3"><img src="/images/productattr3.png"></li>
                        <li><img src="/images/productattr2.png"></li>
                    </ul>
                </div>
            </div>
            <div class="pdetail">
                <ul>
                    <li>品牌：<span><a href="javascript:void(0)" title="${item.title }"
                                    target="_blank">${item.title }</a></span></li>

                    <li>产地：<span><a href="javascript:void(0)" title="中国"
                                    target="_blank">中国</a></span>
                    </li>
                    <li>商品编号：${item.id }</li>
                    <li class="card-pay">
                        <span class="card-pay-left">宜立方卡</span>
                        <span class="card-pay-right">支持宜立方卡支付</span>
                    </li>
                </ul>
                <div class="pDeclare">

                    <div class="nosupport"><b></b>本品不支持无理由退换货</div>
                </div>
            </div>
        </div>
        <span class="clear"></span>
    </div>
</div>
<div class="pWrap">
    <div class="main-box">
        <div id="package"></div>
        <div class="pDetail">
            <ul class="pTab">
                <li class="curr" pcont-target="div-detail"><a title="商品详情"
                                                              href="javascript:void(0);">商品介绍</a>
                </li>
            </ul>
            <div id="add-cart-r-btn-sf" class="p-btn"><a
                    onclick="cartAdd(${item.id}, 0, 1, 0, 1, this);"
                    pid="${item.id}">加入购物车</a></div>
        </div>
        <div class="clear" id="flow-layer-sf"></div>
        <div class="pCont cpjs_box" id="div-detail">
            <div
                    style="background-color: #f5f5f5; padding: 4px 20px 4px 0; line-height: 20px; overflow: hidden; zoom: 1;">
                <span style="float: left; width: 100px; text-align: right; padding-right: 6px; margin-right: 6px;">温馨提示：</span>
                <span style="display: block; overflow: hidden; zoom: 1;">
					宜立方商城所售商品均经过严格的供应商资质审查、商品审查、入库全检、出货全检流程。
					由于部分商品存在厂家更换包装、不同批次、不同生产日期、不同生产工厂等情况，
					导致商品实物与图片存在微小差异，因此请您以收到的商品实物为准，
					同时我们会尽量做到及时更新，由此给您带来不便敬请谅解，谢谢！
				</span>
            </div>
            <div class="product_info">${itemDesc.itemDesc }</div>
        </div>
        <div class="l-recommend proRomm" id="recommend-by-view-sf" style="display:none"></div>
    </div>
    <div class="left-box">
        <div id="brandCon" class="catlist" style="">
            <h2 class="t">相关品牌</h2>
            <ul class="pClass">
                <li><a target="_blank"
                       href="javascript:void(0)"
                       title="Apple">Apple</a>

                    <a target="_blank"
                       href="javascript:void(0)"
                       title="Google">Google</a>

                    <a target="_blank"
                       href="javascript:void(0)"
                       title="小米">小米</a>

                    <a target="_blank"
                       href="javascript:void(0)"
                       title="OPPO">OPPO</a>

                    <a target="_blank"
                       href="javascript:void(0)"
                       title="vivo">vivo</a>

                    <a target="_blank"
                       href="javascript:void(0)"
                       title="华为">华为</a>
                </li>
            </ul>
        </div>

    </div>
    <div class="clear"></div>
</div>
<div class="clear1"></div>
<jsp:include page="commons/footer.jsp"/>
</body>
</html>