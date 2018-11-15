package com.adrian.ng;

/**
 * Created by Adrian on 12/10/2018.
 */
public class OptionPricer extends Utils {

    public static void main(String[] args) {
        String[] Name = readTxt("pricingtypes.txt");
        PricingFactory factory = new PricingFactory();
        for (String str : Name) {
            PricingType pricing = factory.getPricingType(str);

            double call = pricing.getCall();
            double put = pricing.getPut();

            System.out.printf("%s\n\tCall:%.14f\n\tPut:%.14f\n", str, call, put);
        }
    }
}
