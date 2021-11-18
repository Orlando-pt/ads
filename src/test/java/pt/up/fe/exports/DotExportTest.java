package pt.up.fe.exports;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DotExportTest {

    private DotExport dotExport;

    @BeforeEach
    void setUp() {
        dotExport = new DotExport(new ArrayList<>());
    }

    @Test
    void testGetImage() throws Exception{
        this.dotExport.experimentGraphViz();
    }
    
    @Test
    void testNumber2() throws Exception {
        this.dotExport.experimentNumber2();
    }
}
