package pt.up.fe.exports;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import pt.up.fe.places.CompoundPlace;
import pt.up.fe.places.Parish;
import pt.up.fe.places.Place;

public class PlaceExportLoadInterfaceTest {

    private PlaceExportLoadInterface placeExport;

    // @Test
    // void testExport() {
    //     System.out.println(this.generatePlacesTree().export());
    // }

    @Test
    void testLoad() {
        Place placesTree = this.generatePlacesTree();
        
        Map<String, Object> exportObject = placesTree.export();

        assertEquals(
            placesTree,
            new CompoundPlace().load(exportObject)
        );
    }

    private Place generatePlacesTree() {
        CompoundPlace portugal = new CompoundPlace("Portugal");

        CompoundPlace braga = new CompoundPlace("Braga");
        CompoundPlace porto = new CompoundPlace("Porto");
        portugal.addChild(braga);
        portugal.addChild(porto);
    
        Parish celorico = new Parish("Celorico");
        Parish fafe = new Parish("Fafe");
        braga.addChild(celorico);
        braga.addChild(fafe);

        Parish paranhos = new Parish("Paranhos");
        porto.addChild(paranhos);

        return portugal;
    }
}
