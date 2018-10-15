# OptionPricer
Option Pricing: Monte Carlo, Black Scholes and Binomial Trees

We have two .txt files

input.txt

  Handles the testing parameters:
  
    - Stock price
    - Strike price
    - interest rate
    - volatility
    - timehorizon

pricingtypes.txt

  This is a list of the different methods of computing option prices:
  
  - AmericanTree
  - AsianMonteCarlo
  - EuropeanTree
  - EuropeanMonteCarlo
  - BlackScholes

  This list is fed into PricingFactory.java so that the right Class is initialized.

Interface: PricingType.java

  This has two methods:
  
    double getCall();
    double getPut();
    
We have two abstract classes. And these each have two subclasses to accommodate the various ways of computing payoff
  - MonteCarlo.java
    - European payoff
    - Asian payoff
  - BinomialTree.java
    - European put payoff
    - American put payoff
  
  
