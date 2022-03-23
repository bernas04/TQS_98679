package com.tqs;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Unit test for simple App.
 */
@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest 
{
    /**
     * Rigorous Test :-)
     */

     @Mock
     private IStockmarketService stock;

     @InjectMocks
     private StocksPortfolio portfolio;



    @DisplayName("Test getTotalValue() method")
    @Test
    public void testGetTotalValue(){
        Mockito.when(stock.lookUpPrice("Microsoft")).thenReturn(100.5);
        Mockito.when(stock.lookUpPrice("Apple")).thenReturn(231.5);
        Mockito.when(stock.lookUpPrice("McDonald's")).thenReturn(154.25);

        portfolio.addStock(new Stock("Microsoft", 4));
        portfolio.addStock(new Stock("Apple", 2));
        portfolio.addStock(new Stock("McDonald's", 1));

        double totalValue = portfolio.getTotalValue();
        assertThat(totalValue, is(1019.25));

        Mockito.verify(stock, Mockito.times(3)).lookUpPrice(Mockito.anyString());
    }
}
