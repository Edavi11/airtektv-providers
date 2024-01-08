package com.airtek.tv.channels.manager.airtektvchannelsmanager;

import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.IJsonDbInit;

@Component
@Order(value = 1)
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IJsonDbInit jsonDbInitService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jsonDbInitService.insertInitialProviders();
        jsonDbInitService.insertInitialCategories();
        jsonDbInitService.insertInitialChannels();
        jsonDbInitService.insertInitialTestChannels();
        jsonDbInitService.insertInitialDemoChannels();
    }
}
