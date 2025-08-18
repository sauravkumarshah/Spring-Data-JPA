package com.tipsontech.creditcard.listeners;

import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

@Component
public class PostLoadListener implements PostLoadEventListener {

    @Override
    public void onPostLoad(PostLoadEvent postLoadEvent) {
        System.out.println("In Post Load");
    }
}
