package io.pragra.framework.data;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class ContactProvider {

    @DataProvider(name = "contactProvider")
    public Iterator<Object[]> getContacts(){
        return CSVReader.getData().iterator();
    }
}
