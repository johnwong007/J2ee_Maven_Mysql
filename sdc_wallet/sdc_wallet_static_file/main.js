function getNetWork(url,success){
    $.ajax({
        type: 'GET',
        url: url,
        data: data,
        success: callbackSuccess,
    });
}

function fuckfuck(){
    initWeb3();
    var ff = getMetaMaskAccountList();
    alert("1"+ff);
}
var web3;
function initWeb3(){
    // Is there an injected web3 instance?
    if (typeof web3 !== 'undefined') {
        App.web3Provider = web3.currentProvider;
    } else {
        // If no injected web3 instance is detected, fall back to Ganache
        App.web3Provider = new Web3.providers.HttpProvider('http://127.0.0.1:7545');
    }
    web3 = new Web3(App.web3Provider);
}

function getMetaMaskAccountList(){
    var ff = web3.eth.getAccounts(function(error, accounts) {
        if (error) {
            console.log(error);
        }
        console.log(accounts);
        var account = accounts[0];

        //account = "0x5AEDA56215b167893e80B4fE645BA6d5Bab767DE";
        alert("2"+account);
        //============这里可以去取到每个账户，把每个账户都显示到页面中显示成小块


        //=======================
        return accounts;
    });
    alert("3"+ff);
    return ff;
}
//模拟取值
function moniquzhi(){
    return [
        {
            a: '0xf17f52151EbEF6C7334FAD080c5704D77216b732',
            b: '5000.020421020421241988',
            c: '5000.020421020421241988',
        },
        {
            a: '0xf17f52151EbEF6C7334FAD080c5704D77216b732',
            b: '5000.020421020421241988',
            c: '5000.020421020421241988',
        },
        {
            a: '0xf17f52151EbEF6C7334FAD080c5704D77216b732',
            b: '5000.020421020421241988',
            c: '5000.020421020421241988',
        },
    ]
}
function getAccounSdc(accountAddress,ni_de_div_dui_xiang){
    // 合约ABI
    var abi = [{"constant":true,"inputs":[],"name":"name","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"}],"name":"approve","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"totalSupply","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transferFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"decimals","outputs":[{"name":"","type":"uint8"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_value","type":"uint256"}],"name":"burn","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"balanceOf","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_value","type":"uint256"}],"name":"burnFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"symbol","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transfer","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"},{"name":"_extraData","type":"bytes"}],"name":"approveAndCall","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"address"}],"name":"allowance","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[{"name":"initialSupply","type":"uint256"},{"name":"tokenName","type":"string"},{"name":"tokenSymbol","type":"string"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":true,"name":"to","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Transfer","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Burn","type":"event"}];
    // 合约地址
    var address = "0x4d2d24899c0b115a1fce8637fca610fe02f1909e";
    // 通过ABI和地址获取已部署的合约对象
    var hehe = web3.eth.contract(abi).at(address);

    hehe.balanceOf.call(accountAddress,function(error,nasha){
        //console.log(nasha);
        console.log("代币余额："+nasha.toNumber()/1000000/1000000/1000000);
        var sdcMoney = nasha.toNumber()/1000000/1000000/1000000;
    });
    //alert("fff:"+aa);
    console.log(web3.eth);
    web3.eth.getBalance(account,function(error,nasha){
        //console.log(nasha);
        console.log("以太币余额："+nasha.toNumber()/1000000/1000000/1000000);
        var ethMoney = nasha.toNumber()/1000000/1000000/1000000;
    });
}