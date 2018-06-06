function contract(data) {
    return (
`pragma solidity ${data.solidityVersion || '0.4.24'};
pragma experimental ABIEncoderV2;

import 'zeppelin-solidity/contracts/token/ERC20/StandardToken.sol';
import 'zeppelin-solidity/contracts/token/ERC20/DetailedERC20.sol';
import 'zeppelin-solidity/contracts/token/ERC20/MintableToken.sol';


contract ${data.name || 'EventTradeToken'} is StandardToken, DetailedERC20 {
    struct product {
        uint8 code;
        string name;
        uint price;
    }

    string [] clients_;
    product [] products_;

    mapping(string => uint) private balances_;

    uint initPremium_ = 50;

    constructor() public DetailedERC20("EventTradeTokenSample", "ETT", 2){
    }

    function newClient(string _client) public {
        //        TODO: check that this _client is uniq
        clients_.push(_client);
        balances_[_client] = initPremium_;
    }

    function getClientBalance(string _client) public view returns (uint){
        if (containString(clients_, _client)) {
            return balances_[_client];
        } else {
            return 0;
        }
    }

    function addProducts(product[] _products) public {
        uint _newProductsCount = _products.length;

        if (_newProductsCount > 0) {
            for (uint8 i = 0; i < _newProductsCount; i++) {
                uint _productsCount = products_.length;
                bool addProduct = true;
                if (_productsCount > 0) {
                    for (uint8 j = 0; j < _productsCount; j++) {
                        if (_products[i].code == products_[j].code) {
                            addProduct = false;
                        }
                    }
                }
                if (addProduct) {
                    products_.push(_products[i]);
                }
            }
        }
    }

    function getProducts() public view returns (product[]){
        return products_;
    }

    function buyProduct(string _client, uint8 _code) public {
        require(containString(clients_, _client));
        uint8 productIndex = getProductIndex(products_, _code);
        require(productIndex >= 0);
        require(balances_[_client] >= products_[productIndex].price);
        balances_[_client] -= products_[productIndex].price;
    }


    function compareStrings(string a, string b) view returns (bool){
        return keccak256(a) == keccak256(b);
    }

    function containString(string[] stringArray, string str) view returns (bool){
        if (stringArray.length > 0) {
            for (uint8 i = 0; i < stringArray.length; i++) {
                if (keccak256(stringArray[i]) == keccak256(str))
                {
                    return true;
                }
            }
        }
        return false;
    }

    function hasProductCode(product[] _products, uint8 _code) view returns (bool){
        if (_products.length > 0) {
            for (uint8 i = 0; i < _products.length; i++) {
                if (_products[i].code == _code) {
                    return true;
                }
            }
        }
        return false;
    }

    function getProductIndex(product[] _products, uint8 _code) view returns (uint8){
        require(hasProductCode(_products, _code));
        if (_products.length > 0) {
            for (uint8 i = 0; i < _products.length; i++) {
                if (_products[i].code == _code) {
                    return i;
                }
            }
        }
    }
}`)
}