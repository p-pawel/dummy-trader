Dummy Trader

# Introduction

This is a resolution to one challenge. For obvious reasons I'm not posting the requirements here.


My assumptions:

- there are no users - all request from any browser session as assumed to belong to the same trader
- trade engine is extremely simplified: 
  - no order book (so that no makers/takers) 
  - only buy orders (money inevitably ends)
  - no order cancellation
  - no fees
  - no wallets


# Running 

1. Make sure that ports 8080 and 4200 are available before running.

2. Start the Docker Compose, e.g. `docker-compose up`

3. Navigate to: http://localhost:4200


 
# Corner cuts

Some, due to time limits for the challenge, aspects are over-simplified comparing to the prod-ready application:

- injection of environmental variables into Angular app to depend on real container url (currently backend host is hard-coded in )   

- no tests in frontend project:

  - note: I would prefer to switch from Karma to the Jest, but as of current Angular version (v. 13) it is not so trivial as it was in earlier version

- no Swagger docs exposed from the backend, so that no services and data models generated from it in the frontend

- no connection error handle, e.g. when backend service is down the price just stops updating and doesn't recover the connection later


# Development steps

## No 1. I want to see price live

As a User, I can:
- navigate to the Trade Page
- see the current price
- see the price updated live

## No 2. I want to place Market Order

As a User, I can:
- place the buying Market Order
- I can see the order execution
- I can see my Quote Currency balance decreased

Exception:
- in case of not sufficient USD, I'm not able to place the order

Note: this restriction should be handled by trade engine, but due to challenge time limits let's handle only frontend validation (backend one can happen or not)



## No 3. I want to place Limit Order

As a User, I can:
- place the buying Limit Order
- I can see my Quote Currency balance is decreased
- I can see the order planned for execution (as long as the price not equal or lower to the limit)
- when the market price meet the limit, I can see the order execution

Exception:
- in case of not sufficient USD, I'm not able to place the order

