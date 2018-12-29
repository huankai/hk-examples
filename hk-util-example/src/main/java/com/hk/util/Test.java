package com.hk.util;

import com.hk.commons.util.JsonUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangkai
 * @date 2018/12/25 21:42
 */
public class Test {

    public static void main(String[] args) {
        Vo vo = new Vo();
        List<Currency> currencies = new ArrayList<>();
        Currency currency = new Currency();
        currency.currencyCode = "HKD";
        Tradability tradability = new Tradability();
        tradability.sellable = true;
        tradability.buyable = true;
        currency.tradability = tradability;
        currencies.add(currency);

        currency = new Currency();
        currency.currencyCode = "USD";
        tradability = new Tradability();
        tradability.sellable = true;
        tradability.buyable = false;
        currency.tradability = tradability;
        currencies.add(currency);

        currency = new Currency();
        currency.currencyCode = "UKD";
        tradability = new Tradability();
        tradability.sellable = false;
        tradability.buyable = true;
        currency.tradability = tradability;
        currencies.add(currency);

        vo.currencies = currencies;

        System.out.println(JsonUtils.serialize(vo, true));

        System.out.println("-----------------");
        List<String> sellableList = vo.currencies.stream()
                .filter(item -> item.tradability.sellable)
                .map(Currency::getCurrencyCode)
                .collect(Collectors.toList());
        System.out.println(JsonUtils.serialize(sellableList, true));

        System.out.println("-----------------");
        List<String> buyableList = vo.currencies.stream()
                .filter(item -> item.tradability.buyable)
                .map(Currency::getCurrencyCode)
                .collect(Collectors.toList());
        System.out.println(JsonUtils.serialize(buyableList, true));
    }

    @Data
    static class Vo {

        private List<Currency> currencies;
    }

    @Data
    static class Currency {

        private String currencyCode;

        private Tradability tradability;

    }

    @Data
    static class Tradability {

        private boolean sellable;

        private boolean buyable;

    }
}
