# Online Marketplace:

Java project that uses Spring Boot to create an API for an online wallet where users can manage their credit balance.
Users can add and remove credits to their wallets, as well as transfer credits to other wallets.

Wallets have the following properties: id, credit balance, creation time, balance last modified, and status.

## Main features include:
- View balance
- Buy credits
- Transfer credits
- Withdraw Credits
- Change status of wallet (frozen or normal)

## To access, run the onlinemarketplace Spring Book project  
- Access the database through H2-console: http://localhost:8080/h2-console
- Use a tool such as Postman to execute the HTTP Methods

## API Documentation
- View Balance: GET http://localhost:8080/wallet/{walletId}
- Add Credits: PUT http://localhost:8080/wallet/{walletId}/{amount}
- Withdraw Credits: PUT http://localhost:8080/withdraw/{walletId}/{amount}
- Transfer Credits: PUT http://localhost:8080/transfer/{sourceWallet}/{targetWallet}/{amount}
- Change Status: PUT http://localhost:8080/changeStatus/{walletId}
