function contract(data) {
    return (
`pragma solidity 0.4.24;

/**
 * @title SafeMath
 * @dev Math operations with safety checks that throw on error
 */
library SafeMath {
    function mul(uint256 a, uint256 b) internal pure returns (uint256) {
        if (a == 0) {
            return 0;
        }
        uint256 c = a * b;
        assert(c / a == b);
        return c;
    }

    function div(uint256 a, uint256 b) internal pure returns (uint256) {
        // assert(b > 0); // Solidity automatically throws when dividing by 0
        uint256 c = a / b;
        // assert(a == b * c + a % b); // There is no case in which this doesn't hold
        return c;
    }

    function sub(uint256 a, uint256 b) internal pure returns (uint256) {
        assert(b <= a);
        return a - b;
    }

    function add(uint256 a, uint256 b) internal pure returns (uint256) {
        uint256 c = a + b;
        assert(c >= a);
        return c;
    }
}

contract ${data.name} {
    struct product {
        uint8 code;
        string name;
        uint price;
        uint count;
    }

    string [] clients_;
    string allClientsAsString_ = "";
    product [] products_;

    string public constant name = 'EventTradeToken';
    string public constant symbol = 'ETT';
    uint8 public constant decimals = 2;

    mapping(string => uint) private balances_;

    uint initPremium_ = 50;

    function newClient(string _client) public {
        require(containString(clients_, _client) == false);
        clients_.push(_client);
        balances_[_client] = initPremium_;
        allClientsAsString_ = strConcat(allClientsAsString_, _client);
    }

    function getClientsAsString() public view returns (string) {
        return allClientsAsString_;
    }

    function getClientBalance(string _client) public returns (uint){
        if (containString(clients_, _client)) {
            return balances_[_client];
        } else {
            return 0;
        }
    }

    function addProduct(uint8 _code, string _name, uint _price, uint _count) public {
        uint _productsCount = products_.length;
        bool add = true;
        if (_productsCount > 0) {
            for (uint8 j = 0; j < _productsCount; j++) {
                if (_code == products_[j].code) {
                    add = false;
                }
            }
        }
        if (add) {
            products_.push(product({code : _code, name : _name, price : _price, count : _count}));
        }
    }

    function getProductsCount() public view returns (uint){
        return products_.length;
    }

    function getProductByIndex(uint8 num) public returns (uint8 _code, string _name, uint _price, uint count){
        return (products_[num].code, products_[num].name, products_[num].price, products_[num].count);
    }

    function buyProduct(string _client, uint8 _code) public {
        require(containString(clients_, _client));
        uint8 productIndex = getProductIndex(_code);
        require(productIndex >= 0);
        require(balances_[_client] >= products_[productIndex].price);
        require(products_[productIndex].count > 0);
        products_[productIndex].count = SafeMath.sub(products_[productIndex].count, 1);
        balances_[_client] = SafeMath.sub(balances_[_client], products_[productIndex].price);
    }


    function compareStrings(string a, string b) private returns (bool){
        return keccak256(a) == keccak256(b);
    }

    function containString(string[] stringArray, string str) private returns (bool){
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

    function hasProductCode(uint8 _code) private returns (bool){
        if (products_.length > 0) {
            for (uint8 i = 0; i < products_.length; i++) {
                if (products_[i].code == _code) {
                    return true;
                }
            }
        }
        return false;
    }

    function getProductIndex(uint8 _code) private returns (uint8){
        require(hasProductCode(_code));
        if (products_.length > 0) {
            for (uint8 i = 0; i < products_.length; i++) {
                if (products_[i].code == _code) {
                    return i;
                }
            }
        }
    }

    function strConcat(string _a, string _b, string _c, string _d, string _e) internal returns (string){
        bytes memory _ba = bytes(_a);
        bytes memory _bb = bytes(_b);
        bytes memory _bc = bytes(_c);
        bytes memory _bd = bytes(_d);
        bytes memory _be = bytes(_e);
        string memory abcde = new string(_ba.length + _bb.length + _bc.length + _bd.length + _be.length);
        bytes memory babcde = bytes(abcde);
        uint k = 0;
        for (uint i = 0; i < _ba.length; i++) babcde[k++] = _ba[i];
        for (i = 0; i < _bb.length; i++) babcde[k++] = _bb[i];
        for (i = 0; i < _bc.length; i++) babcde[k++] = _bc[i];
        for (i = 0; i < _bd.length; i++) babcde[k++] = _bd[i];
        for (i = 0; i < _be.length; i++) babcde[k++] = _be[i];
        return string(babcde);
    }

    function strConcat(string _a, string _b, string _c, string _d) internal returns (string) {
        return strConcat(_a, _b, _c, _d, "");
    }

    function strConcat(string _a, string _b, string _c) internal returns (string) {
        return strConcat(_a, _b, _c, "", "");
    }

    function strConcat(string _a, string _b) internal returns (string) {
        return strConcat(_a, _b, "", "", "");
    }
}`)
}