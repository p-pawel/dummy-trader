Dummy Trader

# Introduction

This is a resolution to one challenge. For obvious reasons I'm not posting the requirements here.



Effectively it took a bit more than the assumed couple of hours - 
as I took this challenge as an opportunity to fathom the reactive Java (that's quite new to me).


A short explanation why I decided to use the stack that I'm not familiar with:

in order to not develop an integration junk that exhausts a backend by neverending requests just to check if anything has changed,
I decided to use one of the server-push technologies, which in turn are a streaming methods,
so the natural choice to base them on the reactive basis.

The developed solution is still far from being a masterpiece,
some weaknesses that I'm aware of are described in the last section,
also some refactor of the code could be recommended
as along with the introduced changes some architectonic assumptions
also could be rethinked. Unfortunately I'm slowly 
running out of time that could be required to look with enough fresh-eye. 
For the same reason also only 2 of 3 "user-stories" are implemented so far. 


# My assumptions

- there are no users - all request from any browser session as assumed to belong to the same trader
- trade engine is extremely simplified: 
  - no order book (so that no makers/takers) 
  - only buy orders (money inevitably ends)
  - no order cancellation
  - no fees
  - no wallets


# Running 

1. Make sure that ports 8080 and 4200 are available before running.

2. Start the Docker Compose, e.g. `docker-compose up` or (`docker-compose up --build` after code base changes)

3. Wait for build (if needed)

4. Navigate to: http://localhost:4200


 

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


# Corner cuts /  Open issues

Some, due to time limits for the challenge, aspects are over-simplified comparing to the prod-ready application:

- injection of environmental variables into Angular app to depend on real container url (currently backend host is hard-coded in )

- no tests in frontend project:

  - note: I would prefer to switch from Karma to the Jest, but as of current Angular version (v. 13) it is not so trivial as it was in earlier versions

- no Swagger docs exposed from the backend, so that no services and data models generated from it in the frontend

- no connection error handle, e.g. when backend service is down the price just stops updating and doesn't recover the connection later

- format of numbers in Place Order Form

- PlaceOrderResult has rather poor API

- DummyExchangeTest covers only main scenarios (without edge cases)

- there are some inline CSS declarations not extracted
