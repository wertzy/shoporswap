package io;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import shoporswap.*;
import util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRecordTest {

    @Test
    void constructorsTests(){
        ProductRecord testProductRecord1, testProductRecord2, testProductRecord3, testProductRecord4, testProductRecord5, testProductRecord6, testProductRecord7;

        testProductRecord1 = new ProductRecord();
        assertNull(testProductRecord1.getProductType());
        assertEquals("DEFAULT NAME", testProductRecord1.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testProductRecord1.getProductDescription());
        assertEquals(0.0, testProductRecord1.getProductValue());
        assertEquals(null, testProductRecord1.getProductTags());

        AbstractProduct testProduct1 = new SellProduct();
        testProductRecord2 = new ProductRecord(testProduct1);
        assertEquals("shoporswap.SellProduct", testProductRecord2.getProductType());
        assertEquals("DEFAULT NAME", testProductRecord2.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testProductRecord2.getProductDescription());
        assertEquals(0.0, testProductRecord2.getProductValue());
        assertEquals(0, testProductRecord2.getProductTags().size());

        AbstractProduct testProduct2 = new SwapProduct();
        testProductRecord3 = new ProductRecord(testProduct2);
        assertEquals("shoporswap.SwapProduct", testProductRecord3.getProductType());
        assertEquals("DEFAULT NAME", testProductRecord3.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testProductRecord3.getProductDescription());
        assertEquals(0.0, testProductRecord3.getProductValue());
        assertEquals(0, testProductRecord3.getProductTags().size());

        AbstractProduct testProduct3 = new SellProduct("test1", "description1", 50, new Client("test1", "pass1"));
        testProductRecord4 = new ProductRecord(testProduct3);
        assertEquals("shoporswap.SellProduct", testProductRecord4.getProductType());
        assertEquals("test1", testProductRecord4.getProductName());
        assertEquals("description1", testProductRecord4.getProductDescription());
        assertEquals(50, testProductRecord4.getProductValue());
        assertEquals(0, testProductRecord4.getProductTags().size());

        AbstractProduct testProduct4 = new SwapProduct("test2", "description2", 50, new Client("test2", "pass2"));
        testProductRecord5 = new ProductRecord(testProduct4);
        assertEquals("shoporswap.SwapProduct", testProductRecord5.getProductType());
        assertEquals("test2", testProductRecord5.getProductName());
        assertEquals("description2", testProductRecord5.getProductDescription());
        assertEquals(50, testProductRecord5.getProductValue());
        assertEquals(0, testProductRecord5.getProductTags().size());

        AbstractProduct testProduct5 = new SellProduct("test3", "description3", 50, new Client("test3", "pass3"), Arrays.asList(new Tag("tag1")));
        testProductRecord6 = new ProductRecord(testProduct5);
        assertEquals("shoporswap.SellProduct", testProductRecord6.getProductType());
        assertEquals("test3", testProductRecord6.getProductName());
        assertEquals("description3", testProductRecord6.getProductDescription());
        assertEquals(50, testProductRecord6.getProductValue());
        assertEquals(1, testProductRecord6.getProductTags().size());

        AbstractProduct testProduct6 = new SwapProduct("test4", "description4", 50, new Client("test4", "pass4"), Arrays.asList(new Tag("tag1")));
        testProductRecord7 = new ProductRecord(testProduct6);
        assertEquals("shoporswap.SwapProduct", testProductRecord7.getProductType());
        assertEquals("test4", testProductRecord7.getProductName());
        assertEquals("description4", testProductRecord7.getProductDescription());
        assertEquals(50, testProductRecord7.getProductValue());
        assertEquals(1, testProductRecord7.getProductTags().size());
    }

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        ProductRecord testProductRecord1, testProductRecord2, testProductRecord3, testProductRecord4, testProductRecord5, testProductRecord6, testProductRecord7;

        testProductRecord1 = new ProductRecord();
        String testString1 =
                "{" + System.lineSeparator() +
                "  \"productType\" : null," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testString1, JsonUtil.toJsonString(testProductRecord1));

        AbstractProduct testProduct1 = new SellProduct();
        testProductRecord2 = new ProductRecord(testProduct1);
        String testString2 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString2, JsonUtil.toJsonString(testProductRecord2));

        AbstractProduct testProduct2 = new SwapProduct();
        testProductRecord3 = new ProductRecord(testProduct2);
        String testString3 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString3, JsonUtil.toJsonString(testProductRecord3));

        AbstractProduct testProduct3 = new SellProduct("test1", "description1", 50, new Client("test1", "pass1"));
        testProductRecord4 = new ProductRecord(testProduct3);
        String testString4 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test1\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description1\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString4, JsonUtil.toJsonString(testProductRecord4));

        AbstractProduct testProduct4 = new SwapProduct("test2", "description2", 50, new Client("test2", "pass2"));
        testProductRecord5 = new ProductRecord(testProduct4);
        String testString5 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test2\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description2\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString5, JsonUtil.toJsonString(testProductRecord5));

        AbstractProduct testProduct5 = new SellProduct("test3", "description3", 50, new Client("test3", "pass3"), Arrays.asList(new Tag("tag1")));
        testProductRecord6 = new ProductRecord(testProduct5);
        String testString6 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test3\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description3\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ {" + System.lineSeparator() +
                "    \"name\" : \"tag1\"" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";
        assertEquals(testString6, JsonUtil.toJsonString(testProductRecord6));

        AbstractProduct testProduct6 = new SwapProduct("test4", "description4", 50, new Client("test4", "pass4"), Arrays.asList(new Tag("tag1")));
        testProductRecord7 = new ProductRecord(testProduct6);
        String testString7 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test4\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description4\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ {" + System.lineSeparator() +
                "    \"name\" : \"tag1\"" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";
        assertEquals(testString7, JsonUtil.toJsonString(testProductRecord7));
    }

    @Test
    void toAndFromJsonFileTest() throws IOException {

        ProductRecord testProductRecord1, testProductRecord2, testProductRecord3, testProductRecord4, testProductRecord5, testProductRecord6, testProductRecord7;
        String directoryPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

        testProductRecord1 = new ProductRecord();
        String testString1 =
                "{" + System.lineSeparator() +
                "  \"productType\" : null," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : null" + System.lineSeparator() +
                "}";
        String testFileName1 = directoryPath + "ProductRecordTest-toAndFromJsonFileTest-1.json";
        JsonUtil.toJsonFile(testFileName1, testProductRecord1);
        assertEquals(testString1, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName1, ProductRecord.class)));

        AbstractProduct testProduct1 = new SellProduct();
        testProductRecord2 = new ProductRecord(testProduct1);
        String testString2 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString2, JsonUtil.toJsonString(testProductRecord2));
        String testFileName2 = directoryPath + "ProductRecordTest-toAndFromJsonFileTest-2.json";
        JsonUtil.toJsonFile(testFileName2, testProductRecord2);
        assertEquals(testString2, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName2, ProductRecord.class)));

        AbstractProduct testProduct2 = new SwapProduct();
        testProductRecord3 = new ProductRecord(testProduct2);
        String testString3 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString3, JsonUtil.toJsonString(testProductRecord3));
        String testFileName3 = directoryPath + "ProductRecordTest-toAndFromJsonFileTest-3.json";
        JsonUtil.toJsonFile(testFileName3, testProductRecord3);
        assertEquals(testString3, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName3, ProductRecord.class)));

        AbstractProduct testProduct3 = new SellProduct("test1", "description1", 50, new Client("test1", "pass1"));
        testProductRecord4 = new ProductRecord(testProduct3);
        String testString4 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test1\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description1\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString4, JsonUtil.toJsonString(testProductRecord4));
        String testFileName4 = directoryPath + "ProductRecordTest-toAndFromJsonFileTest-4.json";
        JsonUtil.toJsonFile(testFileName4, testProductRecord4);
        assertEquals(testString4, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName4, ProductRecord.class)));

        AbstractProduct testProduct4 = new SwapProduct("test2", "description2", 50, new Client("test2", "pass2"));
        testProductRecord5 = new ProductRecord(testProduct4);
        String testString5 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test2\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description2\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString5, JsonUtil.toJsonString(testProductRecord5));
        String testFileName5 = directoryPath + "ProductRecordTest-toAndFromJsonFileTest-5.json";
        JsonUtil.toJsonFile(testFileName5, testProductRecord5);
        assertEquals(testString5, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName5, ProductRecord.class)));

        AbstractProduct testProduct5 = new SellProduct("test3", "description3", 50, new Client("test3", "pass3"), Arrays.asList(new Tag("tag1")));
        testProductRecord6 = new ProductRecord(testProduct5);
        String testString6 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test3\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description3\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ {" + System.lineSeparator() +
                "    \"name\" : \"tag1\"" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";
        assertEquals(testString6, JsonUtil.toJsonString(testProductRecord6));
        String testFileName6 = directoryPath + "ProductRecordTest-toAndFromJsonFileTest-6.json";
        JsonUtil.toJsonFile(testFileName6, testProductRecord6);
        assertEquals(testString6, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName6, ProductRecord.class)));

        AbstractProduct testProduct6 = new SwapProduct("test4", "description4", 50, new Client("test4", "pass4"), Arrays.asList(new Tag("tag1")));
        testProductRecord7 = new ProductRecord(testProduct6);
        String testString7 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test4\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description4\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ {" + System.lineSeparator() +
                "    \"name\" : \"tag1\"" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";
        assertEquals(testString7, JsonUtil.toJsonString(testProductRecord7));
        String testFileName7 = directoryPath + "ProductRecordTest-toAndFromJsonFileTest-7.json";
        JsonUtil.toJsonFile(testFileName7, testProductRecord7);
        assertEquals(testString7, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName7, ProductRecord.class)));
    }

    @Test
    void listFromJsonTest() throws IOException {
        ProductRecord testProductRecord1, testProductRecord2, testProductRecord3, testProductRecord4, testProductRecord5, testProductRecord6, testProductRecord7;
        String directoryPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

        testProductRecord1 = new ProductRecord();
        String testString1 =
                "{" + System.lineSeparator() +
                "  \"productType\" : null," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : null" + System.lineSeparator() +
                "}";

        AbstractProduct testProduct1 = new SellProduct();
        testProductRecord2 = new ProductRecord(testProduct1);
        String testString2 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";

        AbstractProduct testProduct2 = new SwapProduct();
        testProductRecord3 = new ProductRecord(testProduct2);
        String testString3 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"DEFAULT NAME\"," + System.lineSeparator() +
                "  \"productDescription\" : \"DEFAULT DESCRIPTION\"," + System.lineSeparator() +
                "  \"productValue\" : 0.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";

        AbstractProduct testProduct3 = new SellProduct("test1", "description1", 50, new Client("test1", "pass1"));
        testProductRecord4 = new ProductRecord(testProduct3);
        String testString4 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test1\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description1\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";

        AbstractProduct testProduct4 = new SwapProduct("test2", "description2", 50, new Client("test2", "pass2"));
        testProductRecord5 = new ProductRecord(testProduct4);
        String testString5 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test2\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description2\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ ]" + System.lineSeparator() +
                "}";

        AbstractProduct testProduct5 = new SellProduct("test3", "description3", 50, new Client("test3", "pass3"), Arrays.asList(new Tag("tag1")));
        testProductRecord6 = new ProductRecord(testProduct5);
        String testString6 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test3\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description3\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ {" + System.lineSeparator() +
                "    \"name\" : \"tag1\"" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";

        AbstractProduct testProduct6 = new SwapProduct("test4", "description4", 50, new Client("test4", "pass4"), Arrays.asList(new Tag("tag1")));
        testProductRecord7 = new ProductRecord(testProduct6);
        String testString7 =
                "{" + System.lineSeparator() +
                "  \"productType\" : \"shoporswap.SwapProduct\"," + System.lineSeparator() +
                "  \"productName\" : \"test4\"," + System.lineSeparator() +
                "  \"productDescription\" : \"description4\"," + System.lineSeparator() +
                "  \"productValue\" : 50.0," + System.lineSeparator() +
                "  \"productTags\" : [ {" + System.lineSeparator() +
                "    \"name\" : \"tag1\"" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";

        String testFileName1 = directoryPath + "ProductRecordTest-listFromJsonFileTest-1";
        JsonUtil.toJsonFile(testFileName1, Arrays.asList(testProductRecord1, testProductRecord2, testProductRecord3, testProductRecord4, testProductRecord5, testProductRecord6, testProductRecord7));

        List<ProductRecord> testProductRecordList = JsonUtil.listFromJsonFile(testFileName1, ProductRecord.class);

        assertEquals(testString1, JsonUtil.toJsonString(testProductRecordList.get(0)));
        assertEquals(testString2, JsonUtil.toJsonString(testProductRecordList.get(1)));
        assertEquals(testString3, JsonUtil.toJsonString(testProductRecordList.get(2)));
        assertEquals(testString4, JsonUtil.toJsonString(testProductRecordList.get(3)));
        assertEquals(testString5, JsonUtil.toJsonString(testProductRecordList.get(4)));
        assertEquals(testString6, JsonUtil.toJsonString(testProductRecordList.get(5)));
        assertEquals(testString7, JsonUtil.toJsonString(testProductRecordList.get(6)));
    }

    @Test
    void toProductTest(){
        ProductRecord testProductRecord1, testProductRecord2, testProductRecord3, testProductRecord4, testProductRecord5, testProductRecord6;

        AbstractProduct testProduct1 = new SellProduct();
        testProductRecord1 = new ProductRecord(testProduct1);
        AbstractProduct testExportProduct1 = testProductRecord1.toProduct();
        assertEquals(testProduct1.getProductName(), testExportProduct1.getProductName());
        assertEquals(testProduct1.getProductDescription(), testExportProduct1.getProductDescription());
        assertEquals(testProduct1.getProductValue(), testExportProduct1.getProductValue());
        assertEquals(testProduct1.getProductTags().size(), testExportProduct1.getProductTags().size());

        AbstractProduct testProduct2 = new SwapProduct();
        testProductRecord2 = new ProductRecord(testProduct2);
        AbstractProduct testExportProduct2 = testProductRecord2.toProduct();
        assertEquals(testProduct2.getProductName(), testExportProduct2.getProductName());
        assertEquals(testProduct2.getProductDescription(), testExportProduct2.getProductDescription());
        assertEquals(testProduct2.getProductValue(), testExportProduct2.getProductValue());
        assertEquals(testProduct2.getProductTags().size(), testExportProduct2.getProductTags().size());
        
        AbstractProduct testProduct3 = new SellProduct("test1", "description1", 50, new Client("test1", "pass1"));
        testProductRecord3 = new ProductRecord(testProduct3);
        AbstractProduct testExportProduct3 = testProductRecord3.toProduct();
        assertEquals(testProduct3.getProductName(), testExportProduct3.getProductName());
        assertEquals(testProduct3.getProductDescription(), testExportProduct3.getProductDescription());
        assertEquals(testProduct3.getProductValue(), testExportProduct3.getProductValue());
        assertEquals(testProduct3.getProductTags().size(), testExportProduct3.getProductTags().size());
        
        AbstractProduct testProduct4 = new SwapProduct("test2", "description2", 50, new Client("test2", "pass2"));
        testProductRecord4 = new ProductRecord(testProduct4);
        AbstractProduct testExportProduct4 = testProductRecord4.toProduct();
        assertEquals(testProduct4.getProductName(), testExportProduct4.getProductName());
        assertEquals(testProduct4.getProductDescription(), testExportProduct4.getProductDescription());
        assertEquals(testProduct4.getProductValue(), testExportProduct4.getProductValue());
        assertEquals(testProduct4.getProductTags().size(), testExportProduct4.getProductTags().size());

        AbstractProduct testProduct5 = new SellProduct("test3", "description3", 50, new Client("test3", "pass3"), Arrays.asList(new Tag("tag1")));
        testProductRecord5 = new ProductRecord(testProduct5);
        AbstractProduct testExportProduct5 = testProductRecord5.toProduct();
        assertEquals(testProduct5.getProductName(), testExportProduct5.getProductName());
        assertEquals(testProduct5.getProductDescription(), testExportProduct5.getProductDescription());
        assertEquals(testProduct5.getProductValue(), testExportProduct5.getProductValue());
        assertEquals(testProduct5.getProductTags().size(), testExportProduct5.getProductTags().size());

        AbstractProduct testProduct6 = new SwapProduct("test4", "description4", 50, new Client("test4", "pass4"), Arrays.asList(new Tag("tag1")));
        testProductRecord6 = new ProductRecord(testProduct6);
        AbstractProduct testExportProduct6 = testProductRecord6.toProduct();
        assertEquals(testProduct6.getProductName(), testExportProduct6.getProductName());
        assertEquals(testProduct6.getProductDescription(), testExportProduct6.getProductDescription());
        assertEquals(testProduct6.getProductValue(), testExportProduct6.getProductValue());
        assertEquals(testProduct6.getProductTags().size(), testExportProduct6.getProductTags().size());

    }
}
