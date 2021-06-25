# Plan Generator

In order to inform borrowers about the final repayment schedule, we need to have pre-calculated repayment plans throughout the lifetime of a loan.

In order to calculate a repayment plan specific input parameters are used:
* duration (number of installments in months)
* nominal rate (annual interest rate)
* loan amount (principal amount)
* Date of Disbursement/Payout ("startDate")


## Build & Run instructions

Please execute from root folder next command to build the project.

 ```
 mvn clean install
 ```

Please execute next command to run the service

```
mvn spring-boot:run
```

## How to use

Request example:

**Generate repayment plane**
``` 
POST http://localhost:8080/generate-plan
Content-Type: application/json

{
  "loanAmount": "5000",
  "nominalRate": "5.0",
  "duration": 24,
  "startDate": "2018-01-01T00:00:01Z"
}
```

### Points for improvement

* Javadocs
* logging