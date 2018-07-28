var __login_no = localStorage.getItem('login_no');
if(__login_no=="null"||__login_no==null){
    __login_no = "";
}

var baseLeftHtml = "";
baseLeftHtml += '<div class="base-left">';
baseLeftHtml += '    <div class="logo">';

baseLeftHtml += '    <span class="s1">SDC</span>';
baseLeftHtml += '    <span class="s2">钱包</span>';
baseLeftHtml += '    </div>';
baseLeftHtml += '    <div class="mmm">';
baseLeftHtml += '    <ul>';
if(__login_no=="") {
    baseLeftHtml += '    <li class="h"><i class="ivu-icon ivu-icon-checkmark"></i><span>资金管理</span></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-balance"><a href="account-balance.html">账户余额</a></li>';
// baseLeftHtml += '    <li class="m allAnimate"><a href="account-recharge.html">充值</a></li>';
// baseLeftHtml += '    <li class="m allAnimate"><a href="account-recharge-change.html">币种转换</a></li>';
// baseLeftHtml += '    <li class="m allAnimate"><a href="account-withdraw.html">提现</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-transfer-sdc"><a href="account-transfer-sdc.html">SDC转账</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-transfer"><a href="account-transfer.html">以太币转账</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-recharge-collect"><a href="account-recharge-collect.html">募集投资</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-collect-history"><a href="account-collect-history.html">个人募集投资记录</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-querySdcRecordForUse"><a href="account-querySdcRecordForUse.html">SDC锁仓记录</a></li>';
}
if(__login_no!=""){
    baseLeftHtml += '    <li class="m allAnimate" id="account-queryTransMainList"><a href="account-queryTransMainList.html">募集审核</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-querychn"><a href="account-querychn.html">邀请码设置</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-sdc-history"><a href="account-sdc-history.html">SDC转账记录</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-eth-history"><a href="account-eth-history.html">ETH转账记录</a></li>';
    // baseLeftHtml += '    <li class="m allAnimate" id="account-mujisuocang"><a href="account-mujisuocang.html">募集锁仓管理</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-getCollectInfo-history"><a href="account-getCollectInfo-history.html">募集投资总记录</a></li>';
    baseLeftHtml += '    <li class="m allAnimate" id="account-ratio-set"><a href="account-ratio-set.html">兑换比例设置</a></li>';
}


// baseLeftHtml += '    <li class="m allAnimate" id="account-transaction-history"><a href="account-transaction-history.html">交易记录</a></li>';

baseLeftHtml += '    </ul>';
// baseLeftHtml += '    <ul>';
// baseLeftHtml += '    <li class="h"><i class="ivu-icon ivu-icon-checkmark"></i><span>资金管理</span></li>';
// baseLeftHtml += '    <li class="m allAnimate"><a href="#">账户余额</a></li>';
// baseLeftHtml += '    <li class="m allAnimate"><a href="#">充值</a></li>';
// baseLeftHtml += '    <li class="m allAnimate"><a href="#">提现</a></li>';
// baseLeftHtml += '    <li class="m allAnimate"><a href="#">转账</a></li>';
// baseLeftHtml += '    <li class="m allAnimate"><a href="#">交易记录</a></li>';
// baseLeftHtml += '    </ul>';
baseLeftHtml += '   </div>';
baseLeftHtml += '  </div>';

Vue.component('base-left', {
    props: ['indexname'],
    template: baseLeftHtml,
    created: function () {
     console.log("当前页面",this.indexname);
        // `this` 指向 vm 实例
        // alert($(".name-account-balance").html());
    }
})




var baseRightHtml = "";
baseRightHtml += '<div class="base-right">';
baseRightHtml += ' <div class="mTop clearlw">';
baseRightHtml += '  <div class="mTopLeft"><a href="index.html">返回首页</a></div>';
baseRightHtml += '  <div class="mTopRight">';
if(__login_no!="") {
    baseRightHtml += '      <a href="login.html">注销登录</a>';
}
baseRightHtml += '  </div>';
baseRightHtml += ' </div>';
baseRightHtml += ' <div class="mUrl">';
baseRightHtml += '    <slot name="url"></slot>';

baseRightHtml += ' </div>';
baseRightHtml += '    <slot name="content"></slot>';
baseRightHtml += '  </div>';


Vue.component('base-right', {
    template: baseRightHtml
})