package com.airtek.tv.channels.manager.airtektvchannelsmanager;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements.IJsonDbInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IJsonDbInit jsonDbInitService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jsonDbInitService.insertInitialCategories();
    }
}
