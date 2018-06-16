# Event Trade Token - Server

### Web interface
Hosted in [Heroku](https://calm-sands-35438.herokuapp.com)

You can create unique contract for your event and add any count of products.
Before start you need to be logged in MetaMask.
Insert name and code of your event into inputs, then press Deploy. Deploying process will be displayed in console.
Event code is a unique string by which clients can find your event using Telegram bot.

After successful deploy you will see a form for adding product. Fill in inputs and press Add product.

Tested on Ropsten network.

### Backend
Services:
- Save event contract: POST _/rest/event/new_
- Add new client: GET _/rest/{eventCode}/newclient/{client}_
- Get list of products: GET _/rest/{eventCode}/products_
- Buy a product: GET _/rest/{eventCode}/client/{client}/buyproduct/{productCode}_

Deploy to heroku:
> mvn clean heroku:deploy-war


### Known issues
- only latin symbols
- you can't return to your event web interface for adding products after you close browser tab
- you can't change products or event info
- you should add products one by one