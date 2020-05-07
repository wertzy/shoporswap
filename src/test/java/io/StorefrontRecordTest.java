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
        assertNull(testStorefrontRecord1.accessSellProductList());
        assertNull(testStorefrontRecord1.accessSwapProductList());
        assertNull(testStorefrontRecord1.getProductRecordList());

        Storefront testSellStorefront1 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord2 = new StorefrontRecord(testSellStorefront1);
        assertEquals("sell storefront 1", testStorefrontRecord2.getStorefrontName());
        assertEquals(0, testStorefrontRecord2.accessSellProductList().size());
        assertNull(testStorefrontRecord2.accessSwapProductList());
        assertEquals(0, testStorefrontRecord2.getProductRecordList().size());

        Storefront testSellStorefront2 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        ((SellStorefront) testSellStorefront2).addProduct(new SellProduct("test1", "description1", 50, testSellStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord3 = new StorefrontRecord(testSellStorefront2);
        assertEquals("sell storefront 1", testStorefrontRecord3.getStorefrontName());
        assertEquals(1, testStorefrontRecord3.accessSellProductList().size());
        assertNull(testStorefrontRecord3.accessSwapProductList());
        assertEquals(1, testStorefrontRecord3.getProductRecordList().size());

        Storefront testSwapStorefront1 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord4 = new StorefrontRecord(testSwapStorefront1);
        assertEquals("swap storefront 1", testStorefrontRecord4.getStorefrontName());
        assertNull(testStorefrontRecord4.accessSellProductList());
        assertEquals(0, testStorefrontRecord4.accessSwapProductList().size());
        assertEquals(0, testStorefrontRecord4.getProductRecordList().size());

        Storefront testSwapStorefront2 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        ((SwapStorefront) testSwapStorefront2).addProduct(new SwapProduct("test1", "description1", 50, testSwapStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord5 = new StorefrontRecord(testSwapStorefront2);
        assertEquals("swap storefront 1", testStorefrontRecord5.getStorefrontName());
        assertNull(testStorefrontRecord5.accessSellProductList());
        assertEquals(1, testStorefrontRecord5.accessSwapProductList().size());
        assertEquals(1, testStorefrontRecord5.getProductRecordList().size());
    }

    @Test
    void toStorefrontTest(){
        StorefrontRecord testStorefrontRecord1, testStorefrontRecord2, testStorefrontRecord3, testStorefrontRecord4;

        Storefront testSellStorefront1 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord1 = new StorefrontRecord(testSellStorefront1);
        Storefront testExportSellStorefront1 = testStorefrontRecord1.toStorefront();
        assertEquals(testSellStorefront1.getStorefrontName(), testExportSellStorefront1.getStorefrontName());
        assertEquals(testSellStorefront1.getStorefrontProducts().size(), testExportSellStorefront1.getStorefrontProducts().size());

        Storefront testSellStorefront2 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        ((SellStorefront) testSellStorefront2).addProduct(new SellProduct("test1", "description1", 50, testSellStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord2 = new StorefrontRecord(testSellStorefront2);
        Storefront testExportSellStorefront2 = testStorefrontRecord2.toStorefront();
        assertEquals(testSellStorefront2.getStorefrontName(), testExportSellStorefront2.getStorefrontName());
        assertEquals(testSellStorefront2.getStorefrontProducts().size(), testExportSellStorefront2.getStorefrontProducts().size());

        Storefront testSwapStorefront1 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord3 = new StorefrontRecord(testSwapStorefront1);
        Storefront testExportSellStorefront3 = testStorefrontRecord3.toStorefront();
        assertEquals(testSwapStorefront1.getStorefrontName(), testExportSellStorefront3.getStorefrontName());
        assertEquals(testSwapStorefront1.getStorefrontProducts().size(), testExportSellStorefront3.getStorefrontProducts().size());

        Storefront testSwapStorefront2 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        ((SwapStorefront) testSwapStorefront2).addProduct(new SwapProduct("test1", "description1", 50, testSwapStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord4 = new StorefrontRecord(testSwapStorefront2);
        Storefront testExportSellStorefront4 = testStorefrontRecord4.toStorefront();
        assertEquals(testSwapStorefront2.getStorefrontName(), testExportSellStorefront4.getStorefrontName());
        assertEquals(testSwapStorefront2.getStorefrontProducts().size(), testExportSellStorefront4.getStorefrontProducts().size());
    }

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        StorefrontRecord testStorefrontRecord1, testStorefrontRecord2, testStorefrontRecord3, testStorefrontRecord4, testStorefrontRecord5;
        testStorefrontRecord1 = new StorefrontRecord();
        String testStorefrontJsonString1 = "{" + System.lineSeparator() +
                "  \"storefrontType\" : null," + System.lineSeparator() +
                "  \"storefrontName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productRecordList\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testStorefrontJsonString1, JsonUtil.toJsonString(testStorefrontRecord1));

        Storefront testSellStorefront1 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord2 = new StorefrontRecord(testSellStorefront1);
        String testStorefrontJsonString2 = "{" + System.lineSeparator() +
                "  \"storefrontType\" : \"shoporswap.SellStorefront\"," + System.lineSeparator() +
                "  \"storefrontName\" : \"sell storefront 1\"," + System.lineSeparator() +
                "  \"productRecordList\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testStorefrontJsonString2, JsonUtil.toJsonString(testStorefrontRecord2));

        Storefront testSellStorefront2 = new SellStorefront("sell storefront 1", new Client("test1", "pass1"));
        ((SellStorefront) testSellStorefront2).addProduct(new SellProduct("test1", "description1", 50, testSellStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord3 = new StorefrontRecord(testSellStorefront2);
        String testStorefrontJsonString3 = "{" + System.lineSeparator() +
                "  \"storefrontType\" : \"shoporswap.SellStorefront\"," + System.lineSeparator() +
                "  \"storefrontName\" : \"sell storefront 1\"," + System.lineSeparator() +
                "  \"productRecordList\" : [ {" + System.lineSeparator() +
                "    \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "    \"productName\" : \"test1\"," + System.lineSeparator() +
                "    \"productDescription\" : \"description1\"," + System.lineSeparator() +
                "    \"productValue\" : 50.0," + System.lineSeparator() +
                "    \"productMerchantName\" : \"test1\"," + System.lineSeparator() +
                "    \"productTags\" : [ ]" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";
        assertEquals(testStorefrontJsonString3, JsonUtil.toJsonString(testStorefrontRecord3));

        Storefront testSwapStorefront1 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        testStorefrontRecord4 = new StorefrontRecord(testSwapStorefront1);
        String testStorefrontJsonString4 = "{" + System.lineSeparator() +
                "  \"storefrontType\" : \"shoporswap.SwapStorefront\"," + System.lineSeparator() +
                "  \"storefrontName\" : \"swap storefront 1\"," + System.lineSeparator() +
                "  \"productRecordList\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testStorefrontJsonString4, JsonUtil.toJsonString(testStorefrontRecord4));

        Storefront testSwapStorefront2 = new SwapStorefront("swap storefront 1", new Client("test1", "pass1"));
        ((SwapStorefront) testSwapStorefront2).addProduct(new SwapProduct("test1", "description1", 50, testSwapStorefront2.retrieveStorefrontOwner()));
        testStorefrontRecord5 = new StorefrontRecord(testSwapStorefront2);
        String testStorefrontJsonString5 = "{" + System.lineSeparator() +
                "  \"storefrontType\" : \"shoporswap.SwapStorefront\"," + System.lineSeparator() +
                "  \"storefrontName\" : \"swap storefront 1\"," + System.lineSeparator() +
                "  \"productRecordList\" : [ {" + System.lineSeparator() +
                "    \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "    \"productName\" : \"test1\"," + System.lineSeparator() +
                "    \"productDescription\" : \"description1\"," + System.lineSeparator() +
                "    \"productValue\" : 50.0," + System.lineSeparator() +
                "    \"productMerchantName\" : \"test1\"," + System.lineSeparator() +
                "    \"productTags\" : [ ]" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";
        assertEquals(testStorefrontJsonString5, JsonUtil.toJsonString(testStorefrontRecord5));
    }

}
