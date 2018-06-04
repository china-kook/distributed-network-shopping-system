var CART = {
    itemNumChange: function () {
        $(".increment").click(function () {//＋
            var _thisInput = $(this).siblings("input");

            var _thisTotalPrice =  $(this).siblings("input").parent().parent().next().next().children("#total_price");

            _thisInput.val(eval(_thisInput.val()) + 1);
            $.post("/cart/update/num/" + _thisInput.attr("itemId") + "/" + _thisInput.val() + ".action", function (data) {
                CART.refreshTotal(_thisInput, _thisTotalPrice);
                CART.refreshTotalPrice();
            });
        });
        $(".decrement").click(function () {//-
            var _thisInput = $(this).siblings("input");

            var _thisTotalPrice =  $(this).siblings("input").parent().parent().next().next().children("#total_price");
            if (eval(_thisInput.val()) == 1) {
                return;
            }
            _thisInput.val(eval(_thisInput.val()) - 1);
            $.post("/cart/update/num/" + _thisInput.attr("itemId") + "/" + _thisInput.val() + ".action", function (data) {
                CART.refreshTotal(_thisInput,_thisTotalPrice ) ;
                CART.refreshTotalPrice();
            });
        });
        // $(".itemnum").rnumber(1);//限制只能输入数字
        $(".itemnum").change(function () {
            var _thisInput = $(this);
            var _thisTotalPrice = $(this).parent().parent().next().next().children("#total_price");
            $.post("/cart/update/num/" + _thisInput.attr("itemId") + "/" + _thisInput.val() + ".action", function (data) {
                CART.refreshTotal(_thisInput, _thisTotalPrice);
                CART.refreshTotalPrice();
            });
        });
    },

    refreshTotalPrice: function () { //重新计算总价
        var total = 0;
        $(".itemnum").each(function (i, e) {
            var _this = $(e);
            total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
        });
        $("#allMoney2").html(new Number(total / 100).toFixed(2)).priceFormat({ //价格格式化插件
            prefix: '¥',
            thousandsSeparator: ',',
            centsLimit: 2
        });
    },

    refreshTotal: function (obj, thisTotalPrice) { // 重新计算小计(某个商品的总价格)
        var total = 0;
        var _this = obj;
        total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
        thisTotalPrice.html(new Number(total / 100).toFixed(2)).priceFormat({ //价格格式化插件
            prefix: '¥',
            thousandsSeparator: ',',
            centsLimit: 2
        });
    },

    totalPrice: function () { // 提示输入
        $("#amountdanjian-0-229363").change(function () {
            var totalCnt = $("#amountdanjian-0-229363").val();
            if (totalCnt != parseInt(totalCnt)) {
                jAlert(
                    '请输入正确的正整数',
                    '温馨提示',
                    function () {
                        $("#amountdanjian-0-229363").val("");
                    }
                )
                return false;
            }
        });
    }

};



$(function () {
    CART.totalPrice();
    CART.itemNumChange();
});