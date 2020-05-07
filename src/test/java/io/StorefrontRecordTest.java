package io;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import shoporswap.*;
import util.JsonUtil;

import static org.junit.jupiter.api.Assertions.*;

public class StorefrontRecordTest {

    @Test
    void constructorsTests(){
        StorefrontRecord testStorefrontRecord1, testStorefrontRecord2, testStorefrontRecord3, testStorefrontRecord4, testStorefrontRecord5;

        testStorefrontRecord1 = new StorefrontRecord();
        assertEquals("DEFAULT NAME", testStorefrontRecord1.getStorefrontName());
        assertNull(testStorefrontRecord1.getSellProductList());
        assertNull(testStorefrontRecord1.getSwapProductList());

        Storefront testSellStorefront1 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord2 = new StorefrontRecord(testSellStorefront1);
        assertEquals("sell storefront 1", testStorefrontRecord2.getStorefrontName());
        assertEquals(0, testStorefrontRecord2.getSellProductList().size());
        assertNull(testStorefrontRecord2.getSwapProductList());

        Storefront testSellStorefront2 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        ((SellStorefront) testSellStorefront2).addProduct(new SellProduct("test1", "description1", 50, testSellStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord3 = new StorefrontRecord(testSellStorefront2);
        assertEquals("sell storefront 1", testStorefrontRecord3.getStorefrontName());
        assertEquals(1, testStorefrontRecord3.getSellProductList().size());
        assertNull(testStorefrontRecord3.getSwapProductList());

        Storefront testSwapStorefront1 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord4 = new StorefrontRecord(testSwapStorefront1);
        assertEquals("swap storefront 1", testStorefrontRecord4.getStorefrontName());
        assertNull(testStorefrontRecord4.getSellProductList());
        assertEquals(0, testStorefrontRecord4.getSwapProductList().size());

        Storefront testSwapStorefront2 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        ((SwapStorefront) testSwapStorefront2).addProduct(new SwapProduct("test1", "description1", 50, testSwapStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord5 = new StorefrontRecord(testSwapStorefront2);
        assertEquals("swap storefront 1", testStorefrontRecord5.getStorefrontName());
        assertNull(testStorefrontRecord5.getSellProductList());
        assertEquals(1, testStorefrontRecord5.getSwapProductList().size());
    }

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        StorefrontRecord testStorefrontRecord1, testStorefrontRecord2, testStorefrontRecord3, testStorefrontRecord4, testStorefrontRecord5;
        testStorefrontRecord1 = new StorefrontRecord();
        String testStorefrontJsonString1 = "{" + System.lineSeparator() +
                "  \"storefrontName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"sellProductList\" : null," + System.lineSeparator() +
                "  \"swapProductList\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testStorefrontJsonString1, JsonUtil.toJsonString(testStorefrontRecord1));

        Storefront testSellStorefront1 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord2 = new StorefrontRecord(testSellStorefront1);
        String testStorefrontJsonString2 = "{" + System.lineSeparator() +
                "  \"storefrontName\" : \"sell storefront 1\"," + System.lineSeparator() +
                "  \"sellProductList\" : [ ]," + System.lineSeparator() +
                "  \"swapProductList\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testStorefrontJsonString2, JsonUtil.toJsonString(testStorefrontRecord2));

        Storefront testSellStorefront2 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        ((SellStorefront) testSellStorefront2).addProduct(new SellProduct("test1", "description1", 50, testSellStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord3 = new StorefrontRecord(testSellStorefront2);
        String testStorefrontJsonString3 = "{" + System.lineSeparator() +
                "  \"storefrontName\" : \"sell storefront 1\"," + System.lineSeparator() +
                "  \"sellProductList\" : []," + System.lineSeparator() +
                "  \"swapProductList\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testStorefrontJsonString3, JsonUtil.toJsonString(testStorefrontRecord3));

        Storefront testSwapStorefront1 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord4 = new StorefrontRecord(testSwapStorefront1);

        Storefront testSwapStorefront2 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        ((SwapStorefront) testSwapStorefront2).addProduct(new SwapProduct("test1", "description1", 50, testSwapStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord5 = new StorefrontRecord(testSwapStorefront2);
    }

}
