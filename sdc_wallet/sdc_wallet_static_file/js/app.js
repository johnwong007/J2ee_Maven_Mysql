//App = {
//  web3Provider: null,
//  contracts: {},
//
//  init: function() {
//    // Load pets.
//    $.getJSON('../pets.json', function(data) {
//      var petsRow = $('#petsRow');
//      var petTemplate = $('#petTemplate');
//
//      for (i = 0; i < data.length; i ++) {
//        petTemplate.find('.panel-title').text(data[i].name);
//        petTemplate.find('img').attr('src', data[i].picture);
//        petTemplate.find('.pet-breed').text(data[i].breed);
//        petTemplate.find('.pet-age').text(data[i].age);
//        petTemplate.find('.pet-location').text(data[i].location);
//        petTemplate.find('.btn-adopt').attr('data-id', data[i].id);
//
//        petsRow.append(petTemplate.html());
//      }
//    });
//
//    return App.initWeb3();
//  },
//
//  initWeb3: function() {
//    // Is there an injected web3 instance?
//    if (typeof web3 !== 'undefined') {
//      App.web3Provider = web3.currentProvider;
//    } else {
//      // If no injected web3 instance is detected, fall back to Ganache
//      App.web3Provider = new Web3.providers.HttpProvider('http://127.0.0.1:7545');
//    }
//    web3 = new Web3(App.web3Provider);
//
//    return App.initContract();
//  },
//
//  initContract: function() {
//    // 加载Adoption.json，保存了Adoption的ABI（接口说明）信息及部署后的网络(地址)信息，它在编译合约的时候生成ABI，在部署的时候追加网络信息
//    $.getJSON('Adoption.json', function(data) {
//      // 用Adoption.json数据创建一个可交互的TruffleContract合约实例。
//      var AdoptionArtifact = data;
//      App.contracts.Adoption = TruffleContract(AdoptionArtifact);
//
//      // Set the provider for our contract
//      App.contracts.Adoption.setProvider(App.web3Provider);
//
//      // Use our contract to retrieve and mark the adopted pets
//      return App.markAdopted();
//    });
//    return App.bindEvents();
//
//  },
//
//  bindEvents: function() {
//    $(document).on('click', '.btn-adopt', App.handleAdopt);
//  },
//
//  markAdopted: function(adopters, account) {
//    var adoptionInstance;
//
//    App.contracts.Adoption.deployed().then(function(instance) {
//      adoptionInstance = instance;
//
//      // 调用合约的getAdopters(), 用call读取信息不用消耗gas
//      return adoptionInstance.getAdopters.call();
//    }).then(function(adopters) {
//      for (i = 0; i < adopters.length; i++) {
//        if (adopters[i] !== '0x0000000000000000000000000000000000000000') {
//          $('.panel-pet').eq(i).find('button').text('Success').attr('disabled', true);
//        }
//      }
//    }).catch(function(err) {
//      console.log(err.message);
//    });
//  },
//
//  handleAdopt: function(event) {
//    event.preventDefault();
//
//    var petId = parseInt($(event.target).data('id'));
//
//    var adoptionInstance;
//
//    // 获取用户账号
//    web3.eth.getAccounts(function(error, accounts) {
//      if (error) {
//        console.log(error);
//      }
//      console.log(accounts);
//      var account = accounts[0];
//
//      //account = "0x5AEDA56215b167893e80B4fE645BA6d5Bab767DE";
//      alert(account);
//      // 合约ABI
//      var abi = [{"constant":true,"inputs":[],"name":"name","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"}],"name":"approve","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"totalSupply","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transferFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"decimals","outputs":[{"name":"","type":"uint8"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_value","type":"uint256"}],"name":"burn","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"balanceOf","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_value","type":"uint256"}],"name":"burnFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"symbol","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transfer","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"},{"name":"_extraData","type":"bytes"}],"name":"approveAndCall","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"address"}],"name":"allowance","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[{"name":"initialSupply","type":"uint256"},{"name":"tokenName","type":"string"},{"name":"tokenSymbol","type":"string"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":true,"name":"to","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Transfer","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Burn","type":"event"}];
//      // 合约地址
//      var address = "0x4d2d24899c0b115a1fce8637fca610fe02f1909e";
//      // 通过ABI和地址获取已部署的合约对象
//      var hehe = web3.eth.contract(abi).at(address);
//      hehe.balanceOf.call(account,function(error,nasha){
//        //console.log(nasha);
//        console.log("代币余额："+nasha.toNumber()/1000000000000000000);
//      });
//      //alert("fff:"+aa);
//      console.log(web3.eth);
//      web3.eth.getBalance(account,function(error,nasha){
//        //console.log(nasha);
//        console.log("以太币余额："+nasha.toNumber()/18);
//      });
//      return null;
//      alert(2);
//
//      return null;
//      //account = "0x627306090abaB3A6e1400e9345bC60c78a8BEf57";
//      App.contracts.Adoption.deployed().then(function(instance) {
//        adoptionInstance = instance;
//        // 发送交易领养宠物
//        return adoptionInstance.adopt(petId, {from: account});
//      }).then(function(result) {
//        return App.markAdopted();
//      }).catch(function(err) {
//        console.log(err.message);
//      });
//    });
//  }
//
//};
//
//$(function() {
//  $(window).load(function() {
//    App.init();
//  });
//});
