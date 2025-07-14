package Resources.Payloads;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceAPIPayload {

    public AddPlace addPlacePayload(String name){
        AddPlace ap=new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress("Balewadi High Street, Pune");
        ap.setName(name);
        ap.setLanguage("English-IN");
        ap.setWebsite("https://NewStore.com");
        ap.setPhone_number("+91 99999 99999");

        List<String> typesList= new ArrayList<>();
        typesList.add("Shoes");

        ap.setTypes(typesList);

        Location l=new Location();
        l.setLat(36.28312);
        l.setLng(41.98273);

        ap.setLocation(l);
        return ap;
    }
}
