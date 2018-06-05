function contract(data) {
    return `
                pragma solidity ^${data.solidityVersion || '0.4.23'};

                contract ${data.name || 'SampleContract'} {

                    // This value is visible in etherscan.io explorer
                    uint public value;

                    // Anyone can call this contract and override the value of the previous caller
                    function setValue(uint value_) public {
                        value = value_;
                    }

                }
            `
}