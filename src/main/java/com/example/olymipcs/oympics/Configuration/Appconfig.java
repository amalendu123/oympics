package com.example.olymipcs.oympics.Configuration;


import com.example.olymipcs.oympics.DB;
import com.example.olymipcs.oympics.Devdata;
import com.example.olymipcs.oympics.ProdData;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {

    @Bean
    @ConditionalOnProperty(name = "project.mode",havingValue = "development")
    public DB getData(){
        return new Devdata();
    }

    @ConditionalOnProperty(name = "project.mode",havingValue = "production")
    @Bean
    public DB getProductionData(){
        return new ProdData();
    }


}
