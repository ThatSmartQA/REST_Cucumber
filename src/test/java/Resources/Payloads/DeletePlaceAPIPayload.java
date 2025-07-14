package Resources.Payloads;

public class DeletePlaceAPIPayload {

    public String deletePlacePayload(String placeId){
        return "{\r\n\"place_id\": \""+placeId+"\"\r\n}";
    }
}
