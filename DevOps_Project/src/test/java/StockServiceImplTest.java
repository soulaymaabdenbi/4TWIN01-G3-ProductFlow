import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStock() {
        Stock stock = new Stock(); // Set up your stock object as needed
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        Stock created = stockService.addStock(stock);

        assertNotNull(created);
        verify(stockRepository).save(stock);
    }

    @Test
    void retrieveStockFound() {
        Stock stock = new Stock(); // Set up your stock object
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));

        Stock found = stockService.retrieveStock(1L);

        assertNotNull(found);
        verify(stockRepository).findById(1L);
    }

    @Test
    void retrieveStockNotFound() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NullPointerException.class, () -> {
            stockService.retrieveStock(1L);
        });

        String expectedMessage = "Stock not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void retrieveAllStock() {
        List<Stock> stockList = new ArrayList<>(); // Populate the list as needed
        when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> stocks = stockService.retrieveAllStock();

        assertNotNull(stocks);
        verify(stockRepository).findAll();
    }
}
