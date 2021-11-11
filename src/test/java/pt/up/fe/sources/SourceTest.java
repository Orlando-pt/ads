package pt.up.fe.sources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SourceTest {

    @Test
    public void testOnlineResourceParams() {
        OnlineResource onlineResource = new OnlineResource("D. João I");
        onlineResource.setLink("http://www.mosteirobatalha.gov.pt/pt/index.php?s=white&pid=203");
        assertEquals("D. João I", onlineResource.getName());
        assertEquals("http://www.mosteirobatalha.gov.pt/pt/index.php?s=white&pid=203", onlineResource.getLink());
    }

}