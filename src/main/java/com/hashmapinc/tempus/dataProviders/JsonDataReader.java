package com.hashmapinc.tempus.dataProviders;

import com.google.gson.Gson;
import com.hashmapinc.tempus.config.AppConfig;
import com.hashmapinc.tempus.testDataTypes.Customer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JsonDataReader {
    @Autowired
    private AppConfig appConfig;

    @Getter
    private List<Customer> customerList;

    private List<Customer> getCustomerData(String customerFilePath) {
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(customerFilePath));
            Customer[] customers = gson.fromJson(bufferReader, Customer[].class);
            return Arrays.asList(customers);
        }catch(FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + customerFilePath);
        }finally {
            try { if(bufferReader != null) bufferReader.close();}
            catch (IOException ignore) {}
        }
    }

    public final Customer getCustomerByName(String customerName){
        return customerList.stream().filter(x -> x.firstName.equalsIgnoreCase(customerName)).findAny().get();
    }

    @PostConstruct
    private void initData() {
        //String customerFilePath = appConfig.getTestDataResourcePath()+ "Customer.json";
        //customerList = getCustomerData(customerFilePath);
    }

}
