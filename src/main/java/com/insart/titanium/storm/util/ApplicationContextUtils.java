package com.insart.titanium.storm.util;

import com.insart.titanium.configuration.ApplicationContextProvider;
import com.insart.titanium.repository.GenericRepository;

/**
 * Created by v.kapustin on 8/3/15.
 */
public class ApplicationContextUtils {

    public static GenericRepository getGenericRepository() {
        return ApplicationContextProvider.getApplicationContext().getBean(GenericRepository.class);
    }
}
