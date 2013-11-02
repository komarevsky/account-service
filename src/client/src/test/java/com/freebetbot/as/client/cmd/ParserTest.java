/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client.cmd;

import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class ParserTest extends TestCase {
    
    public ParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPositive() {
        int readers = 8;
        int writers = 10;
        int id1 = 15;
        int id2 = 23;
        String idList = id1 + "," + id2;
        String url = "http://test.com/test";
        
        String[] args = prepareArgsArray(readers, writers, idList, url);
        Options options = ArgsParser.parse(args);
        assertNotNull(options);
        assertTrue(readers == options.getReaders());
        assertTrue(writers == options.getWriters());
        
        List<Integer> ids = options.getIdList();
        assertNotNull(ids);
        assertTrue(ids.contains(id1) && ids.contains(id2));
        assertTrue(url.equals(options.getServiceUrl()));
    }
    
    public void testOneId() {
        int readers = 8;
        int writers = 10;
        int id1 = 15;
        String idList = id1 + "";
        String url = "http://test.com/test";
        
        String[] args = prepareArgsArray(readers, writers, idList, url);
        Options options = ArgsParser.parse(args);
        assertNotNull(options);
        assertTrue(readers == options.getReaders());
        assertTrue(writers == options.getWriters());
        
        List<Integer> ids = options.getIdList();
        assertNotNull(ids);
        assertTrue(ids.contains(id1) && ids.size() == 1);
        assertTrue(url.equals(options.getServiceUrl()));
    }
    
    public void testIncorrectReaders() {
        int readers = -8;
        int writers = 10;
        int id1 = 15;
        int id2 = 23;
        String idList = id1 + "," + id2;
        String url = "http://test.com/test";
        
        String[] args = prepareArgsArray(readers, writers, idList, url);
        Options options = ArgsParser.parse(args);
        assertNull(options);
    }
    
    public void testIncorrectWriters() {
        int readers = 8;
        int writers = -10;
        int id1 = 15;
        int id2 = 23;
        String idList = id1 + "," + id2;
        String url = "http://test.com/test";
        
        String[] args = prepareArgsArray(readers, writers, idList, url);
        Options options = ArgsParser.parse(args);
        assertNull(options);
    }
    
    public void testIncorrectUrl() {
        int readers = 8;
        int writers = 10;
        int id1 = 15;
        int id2 = 23;
        String idList = id1 + "," + id2;
        String url = "";
        
        String[] args = prepareArgsArray(readers, writers, idList, url);
        Options options = ArgsParser.parse(args);
        assertNull(options);
    }
    
    public void testEmptyList() {
        int readers = 8;
        int writers = 10;
        String idList = "";
        String url = "http://test.com/test";
        
        String[] args = prepareArgsArray(readers, writers, idList, url);
        Options options = ArgsParser.parse(args);
        assertNull(options);
    }
    
    public void testCommaList() {
        int readers = 8;
        int writers = 10;
        String idList = ",";
        String url = "http://test.com/test";
        
        String[] args = prepareArgsArray(readers, writers, idList, url);
        Options options = ArgsParser.parse(args);
        assertNull(options);
    }

    public void testIncorrectList() {
        int readers = 8;
        int writers = 10;
        String idList = "12;32";
        String url = "http://test.com/test";
        
        String[] args = prepareArgsArray(readers, writers, idList, url);
        Options options = ArgsParser.parse(args);
        assertNull(options);
    }

    private String[] prepareArgsArray(int readers, int writers, String idList, String url) {
        String[] args = new String[4];
        args[0] = Options.R_COUNT_OPTION + readers;
        args[1] = Options.W_COUNT_OPTION + writers;
        args[2] = Options.ID_LIST_OPTION + idList;
        args[3] = Options.URL_OPTION + url;
        return args;
    }
}
