package io;

import org.junit.jupiter.api.Test;
import shoporswap.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRecordTest {

    @Test
    void constructorsTests(){
        ProductRecord testRecord1, testRecord2, testRecord3, testRecord4, testRecord5, testRecord6, testRecord7;

        testRecord1 = new ProductRecord();
        assertEquals("DEFAULT NAME", testRecord1.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testRecord1.getProductDescription());
        assertEquals(0.0, testRecord1.getProductValue());
        assertEquals(null, testRecord1.getProductTags());

        AbstractProduct testProduct1 = new SellProduct();
        testRecord2 = new ProductRecord(testProduct1);
        assertEquals("DEFAULT NAME", testRecord2.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testRecord2.getProductDescription());
        assertEquals(0.0, testRecord2.getProductValue());
        assertEquals(0, testRecord2.getProductTags().size());

        AbstractProduct testProduct2 = new SwapProduct();
        testRecord3 = new ProductRecord(testProduct2);
        assertEquals("DEFAULT NAME", testRecord3.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testRecord3.getProductDescription());
        assertEquals(0.0, testRecord3.getProductValue());
        assertEquals(0, testRecord3.getProductTags().size());

        AbstractProduct testProduct3 = new SellProduct("test1", "description1", 50, new Client("test1", "pass1"));
        testRecord4 = new ProductRecord(testProduct3);
        assertEquals("test1", testRecord4.getProductName());
        assertEquals("description1", testRecord4.getProductDescription());
        assertEquals(50, testRecord4.getProductValue());
        assertEquals(0, testRecord4.getProductTags().size());

        AbstractProduct testProduct4 = new SwapProduct("test2", "description2", 50, new Client("test2", "pass2"));
        testRecord5 = new ProductRecord(testProduct4);
        assertEquals("test2", testRecord5.getProductName());
        assertEquals("description2", testRecord5.getProductDescription());
        assertEquals(50, testRecord5.getProductValue());
        assertEquals(0, testRecord5.getProductTags().size());

        AbstractProduct testProduct5 = new SellProduct("test3", "description3", 50, new Client("test3", "pass3"), Arrays.asList(new Tag("tag1")));
        testRecord6 = new ProductRecord(testProduct5);
        assertEquals("test3", testRecord6.getProductName());
        assertEquals("description3", testRecord6.getProductDescription());
        assertEquals(50, testRecord6.getProductValue());
        assertEquals(1, testRecord6.getProductTags().size());

        AbstractProduct testProduct6 = new SwapProduct("test4", "description4", 50, new Client("test4", "pass4"), Arrays.asList(new Tag("tag1")));
        testRecord7 = new ProductRecord(testProduct6);
        assertEquals("test4", testRecord7.getProductName());
        assertEquals("description4", testRecord7.getProductDescription());
        assertEquals(50, testRecord7.getProductValue());
        assertEquals(1, testRecord7.getProductTags().size());
    }
}
