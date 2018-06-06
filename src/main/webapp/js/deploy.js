function deploy(eventCode) {
    const solidityVersion = '0.4.23'
    const contractName = `contract_${eventCode}`
    const source = contract({
        solidityVersion,
        name: contractName
    })
    console.log('Choosing web3 provider...');
    if (typeof web3 !== 'undefined') {
        web3 = new Web3(web3.currentProvider);
        console.log('using MetaMask as web3 provider');
    } else {
        web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));
        console.log('using local provider at 8545 port');
    }
    web3.eth.defaultAccount = web3.eth.accounts[0];
    console.log(`Using ${web3.eth.accounts[0]} eth account`);
    console.log('web3: ', web3)

    // Get a list of all possibile solc versions
    BrowserSolc.getVersions(function (soljsonSources, soljsonReleases) {
        console.log('soljsonReleases: ', soljsonReleases);

        //Load a specific compiler version
        BrowserSolc.loadVersion(soljsonReleases[solidityVersion], function (solc) {
            // BrowserSolc.loadVersion("soljson-v0.4.23+commit.124ca40d.js", function (solc) {
            console.log(`Compiling contract with solc ${solidityVersion}...`);
            console.log('Contract source: ', source)
            optimize = 1;
            const output = solc.compile(source, optimize);
            console.log('compiled contract: ', output);
            const name = `:${contractName}`
            const bytecode = output.contracts[name].bytecode;
            let code = output.contracts[name].bin;
            console.log('Getting abi of contract...');
            const abi = JSON.parse(output.contracts[name].interface);

            const contract = web3.eth.contract(abi);

            // Deploy contract instance
            console.log('Deploying contract instance...')
            const contractInstance = contract.new({
                data: '0x' + bytecode,
                gas: 200000,
                gasPrice: 5
            }, (err, res) => {
                if (err) {
                    console.error(err);
                    return;
                }
                console.log('Successfully deployed contract! Result: ', res)
                // Log the tx, you can explore status with eth.getTransaction()
                console.log('transactionHash: ', res.transactionHash);

                // If we have an address property, the contract was deployed
                if (res.address) {
                    console.log('Contract address: ' + res.address);
                }
            });
        })
    });
}