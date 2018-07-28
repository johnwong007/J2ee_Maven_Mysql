
App = {
    web3Provider: null,
    contracts: {},
    web3: {},
    abi : [{"constant":true,"inputs":[],"name":"name","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"}],"name":"approve","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"totalSupply","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transferFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"decimals","outputs":[{"name":"","type":"uint8"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_value","type":"uint256"}],"name":"burn","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"balanceOf","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_value","type":"uint256"}],"name":"burnFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"symbol","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transfer","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"},{"name":"_extraData","type":"bytes"}],"name":"approveAndCall","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"address"}],"name":"allowance","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[{"name":"initialSupply","type":"uint256"},{"name":"tokenName","type":"string"},{"name":"tokenSymbol","type":"string"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":true,"name":"to","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Transfer","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Burn","type":"event"}],
    abi2 : [{"constant":false,"inputs":[{"name":"_address","type":"address"},{"name":"_sdc","type":"uint256"},{"name":"_locktime","type":"uint256"}],"name":"inSdcForAdmin","outputs":[{"name":"b","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"getAccountOutputSdcslength","outputs":[{"name":"b","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"uint256"}],"name":"accoutInputOutputSdcLogs","outputs":[{"name":"account","type":"address"},{"name":"sdc","type":"uint256"},{"name":"locktime","type":"uint256"},{"name":"isIn","type":"bool"},{"name":"createttime","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[],"name":"insetMoney","outputs":[{"name":"b","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"nowInSeconds","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"uint256"}],"name":"accountInputSdcs","outputs":[{"name":"account","type":"address"},{"name":"sdc","type":"uint256"},{"name":"locktime","type":"uint256"},{"name":"createttime","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"getAccountInputSdcslength","outputs":[{"name":"b","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"uint256"}],"name":"accountOutputSdcs","outputs":[{"name":"account","type":"address"},{"name":"sdc","type":"uint256"},{"name":"createttime","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"getUnlockSdc","outputs":[{"name":"b","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"unlockSdc","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_sdc","type":"uint256"}],"name":"outSdcForUser","outputs":[{"name":"b","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"getLockSdc","outputs":[{"name":"b","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"payable":true,"stateMutability":"payable","type":"fallback"},{"anonymous":false,"inputs":[{"indexed":true,"name":"_controller","type":"address"},{"indexed":true,"name":"_user","type":"address"},{"indexed":false,"name":"_sdc","type":"uint256"},{"indexed":false,"name":"_locktime","type":"uint256"},{"indexed":false,"name":"_islock","type":"bool"}],"name":"lockLogs","type":"event"}],



    ip:"http://52.76.174.245:8080/", //正式
    address: "0xe85ed250e3d91fde61bf32e22c54f04754e695c5",//主网代币SDC 合约地址 正式
    companyAccount: "0xe7DbCcA8183cb7d67bCFb3DA687Ce7325779c02f", //公司收账地址 正式
    address2: "0x73fdda0b55305730c0730ef49b67fdb7a4163b71",// 主网三方锁仓合约 正式


    // ip:"http://192.168.31.157:8087/", // 本地
    // ip:"http://13.229.119.203:9985/", //测试
    // address: "0x787b1aa8e62ff2a45b61d9c975ce4f01e8292f30",// 合约地址 测试
    // companyAccount: "0xAD7B07322A7e144a0a99e027F9cb173006668D17", //公司收账地址 测试
    // address2: "0x8ecd747930a2cc74d110145a6b2f16d598edf40a",// 主网三方锁仓合约 测试


    init: function() {
    },
    getLoginNo: function() {
        var __login_no = localStorage.getItem('login_no');
        if(__login_no=="null"||__login_no==null){
            __login_no = "";
        }
        return __login_no;
    },

    initWeb3: function() {
        // Is there an injected web3 instance?


        if (typeof web3 !== 'undefined') {
            App.web3Provider = web3.currentProvider;
        } else {
            var urlAddress = window.location.pathname;
            if(urlAddress.substring(1,urlAddress.indexOf(".",1))!="account-help"){
                var r=confirm("系统检测到您当前的访问环境不正确!是否前往查看配置教程");
                if (r==true)
                {
                    window.location.href="account-help.html";
                }
                else
                {
                }
            }
            // alert();
            // If no injected web3 instance is detected, fall back to Ganache
            //App.web3Provider = new Web3.providers.HttpProvider('http://127.0.0.33:7545');
        }
        App.web3 = new Web3(App.web3Provider);
    },
    getMetaMaskAccountList: function() {

            App.web3.eth.getAccounts(function(error, accounts) {
                if (error) {
                    console.log(error);
                }
                console.log(accounts);

                //account = "0x5AEDA56215b167893e80B4fE645BA6d5Bab767DE";
                // alert("2"+account);
                //============这里可以去取到每个账户，把每个账户都显示到页面中显示成小块


                //=======================
                return accounts;
            });
    },
    getBigFloat:function (aa){
        if(aa==""){
            return 0;
        }
        else if(aa==undefined){
            return 0;
        }
        else if(aa=="undefined"){
            return 0;
        }
        else if(aa==NaN){
            return 0;
        }
        else if(aa=="NaN"){
            return 0;
        }
        else{
            var num = new Number(aa);

            var _array = num.toLocaleString().split(",");
            console.log("_array",_array);
            if(_array.length<=6){
                return 0;
            }
            else{
                var mm = "";
                for(var i=0;i<_array.length-6;i++){
                    // console.log("+",_array[i]);
                    mm += _array[i]+"";
                }
                // console.log(mm);
                return parseFloat(mm);
            }

        }
    }
};

$(function() {
    $(window).load(function() {
        App.initWeb3();
    });
    var urlAddress = window.location.pathname;
    $("#"+urlAddress.substring(1,urlAddress.indexOf(".",1))).addClass("in");
});




function accMul(arg1,arg2){
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}

    return (Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)).toFixed(2);
}

//
//
// function getNetWork(url,success){
//     $.ajax({
//         type: 'GET',
//         url: url,
//         data: data,
//         success: callbackSuccess,
//     });
// }
//
// function fuckfuck(){
//     initWeb3();
//     var ff = getMetaMaskAccountList();
//     alert("1"+ff);
// }
// var web3;
// function initWeb3(){
//     // Is there an injected web3 instance?
//     if (typeof web3 !== 'undefined') {
//         App.web3Provider = web3.currentProvider;
//     } else {
//         // If no injected web3 instance is detected, fall back to Ganache
//         App.web3Provider = new Web3.providers.HttpProvider('http://127.0.0.1:7545');
//     }
//     web3 = new Web3(App.web3Provider);
// }
//
// function getMetaMaskAccountList(){
//     var ff = web3.eth.getAccounts(function(error, accounts) {
//         if (error) {
//             console.log(error);
//         }
//         console.log(accounts);
//         var account = accounts[0];
//
//         //account = "0x5AEDA56215b167893e80B4fE645BA6d5Bab767DE";
//         alert("2"+account);
//         //============这里可以去取到每个账户，把每个账户都显示到页面中显示成小块
//
//
//         //=======================
//         return accounts;
//     });
//     alert("3"+ff);
//     return ff;
// }
//
// function getAccounSdc(accountAddress,ni_de_div_dui_xiang){
//     // 合约ABI
//     var abi = [{"constant":true,"inputs":[],"name":"name","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"}],"name":"approve","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"totalSupply","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transferFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"decimals","outputs":[{"name":"","type":"uint8"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_value","type":"uint256"}],"name":"burn","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"balanceOf","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_value","type":"uint256"}],"name":"burnFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"symbol","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transfer","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"},{"name":"_extraData","type":"bytes"}],"name":"approveAndCall","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"address"}],"name":"allowance","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[{"name":"initialSupply","type":"uint256"},{"name":"tokenName","type":"string"},{"name":"tokenSymbol","type":"string"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":true,"name":"to","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Transfer","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Burn","type":"event"}];
//     // 合约地址
//     var address = "0x4d2d24899c0b115a1fce8637fca610fe02f1909e";
//     // 通过ABI和地址获取已部署的合约对象
//     var hehe = web3.eth.contract(abi).at(address);
//
//     hehe.balanceOf.call(accountAddress,function(error,nasha){
//         //console.log(nasha);
//         console.log("代币余额："+nasha.toNumber()/1000000000000000000);
//         var sdcMoney = nasha.toNumber()/1000000000000000000;
//     });
//     //alert("fff:"+aa);
//     console.log(web3.eth);
//     web3.eth.getBalance(account,function(error,nasha){
//         //console.log(nasha);
//         console.log("以太币余额："+nasha.toNumber()/1000000000000000000);
//         var ethMoney = nasha.toNumber()/1000000000000000000;
//     });
// }
