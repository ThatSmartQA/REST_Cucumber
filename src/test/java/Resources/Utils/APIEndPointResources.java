package Resources.Utils;

public enum APIEndPointResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");
    String resource;

    APIEndPointResources(String resource){
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
