package com.ecommerce.service;

import com.ecommerce.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StockService {

    @Autowired
    private StockRepository stockRepository;


}
