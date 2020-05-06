## Design Pattern

The design pattern I used for this project is as follows:
    - Data Access Package: This serves as the database for the entire application. In this Project I used an in memory
    storage structure (HashMap) to accomplish this.
    - Modal Package: I used this layer to define all custom objects used in this application. i.e Bidder Object
    - Service: The service layer is where I defined all the functional requirement/business logic which includes the
    algorithm for retrieving the highest bidder
    - Controller Package: This is layer I used to define the RESTful APIs that handles the client requests and responses

## Tech Stack

   - Java 1.8
   - SpringBoot 2.25
   - Gradle
   - Postman
   I picked these tools and technologies solely based on familiarity

## API Design

   My goal was to design two api:
   1. Post API: This will allow clients to enter the bidder into the auction system by providing a JSON Object containing
      bidder's name, starting bid amount, maximum bid amount and the incremental amount
   2. Get API: This api is intended to have a query path,the auction item. And the response for this api will be
   the highest bidder of the query path(i.e auction Item)

## Algorithm Design

   The algorithm design decision:
   - Design a custom object(Bidder) with a natural ordering on their starting bid amount. This way, I don't need to
   define extra comparators in any data structure I ended up using.
   - Pick a data structure that is able to store bidders in a sorted manner as they are inserted in ascending order.
   For example inserting {2, 1, 3} --> {1, 2, 3}. My choices were between a priority queue and a TreeSet. I went with
   TreeSet because I could access the least element and highest element simultaneously. More on this later
   - Implement a map with key(auction item) and value TreeSet<Bidder>. This allows me to retrieve bidders on a specific
   item to run the final algorithm on.

   # Actual Algorithm
   - Get the TreeSet of Bidders for the specified auction Item
   - If the size of the TreeSet is one, return the bidder.
   - while the (lossing bidder's starting bid is less than his maximumbid) and the number of bidders >= 2
        - Remove loosing Bidder
        - Increase loosing Bidder's starting bid by the incremental amount
        - if new starting bid amount is less than or equal to maximum Bid (meaning loosing bidder can keep bid)
            - Add loosing bidder back to the TreeSet.(TreeSet will take care of re ordering)
        - if loosing bidder's starting bid equals maximum bid (meaning loosing bidder is done bidding)
            - remove the bidder from the TreeSet
    Finally return the highest bidder from the TreeSet

## How to run and use the API
    Running the application
    `
    - cd assignment
    - gradle clean build
    - cd build/libs
    - java -jar assignment-0.0.1-SNAPSHOT.jar
    `

    Making api requests
    Example is provided below:
    Use this curl command to enter a bidder into the system
    `
    curl --location --request POST 'http://localhost:8080/api/v1/register/bid' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "bidder": "Mason",
        "startAmount": 55.00,
        "maxAmount": "85.00",
        "incrementalAmount": "5.00",
        "auctionItem": "Skate"
    }'
    `
    Use this curl command to get the highest bidder for any auction item in the system
    `
    curl --location --request GET 'http://localhost:8080/api/v1/getHighestBidder/Skate'

    `

## Areas of Improvement
   - Add more logging to provide more information on activities happening on the backend
   - Write test cases for the modal other packages besides the service package
   - Incorporate error handling and exception catching
