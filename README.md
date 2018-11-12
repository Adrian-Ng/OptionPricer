# OptionPricer
Option Pricing: Monte Carlo, Black Scholes and Binomial Trees

We have a number of input `.txt` files and a number of `Java` classes.

The main class is `OptionPricer`

- pricingtypes.txt

This first one enumerates the various ways in which we will price options:

* American Binomial Tree
* Asian Monte Carlo
* European Binomial Tree
* European Monte Carlo
* Black Scholes



- Binomial.txt
- BlackScholes.txt
- MonteCarlo.txt


  Handles the testing parameters:
  
- stock,115
- strike,120
- volatility,0.3
- interest,0.15
- timehorizon,0.5

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
  
  
