package com.tqs;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    
    private List<Stock> stocks = new ArrayList<>();
    private IStockmarketService stockmarket;


    public StocksPortfolio(IStockmarketService stockmarket){
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock s){
        this.stocks.add(s);
    }

    public double getTotalValue(){
        double total = 0;
        for (Stock s: stocks){
            total = total + s.getQuantity() * this.stockmarket.lookUpPrice(s.getLabel());
        }
        return total;
    }

}
