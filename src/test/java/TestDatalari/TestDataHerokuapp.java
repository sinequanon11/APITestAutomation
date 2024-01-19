package TestDatalari;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestDataHerokuapp {


    public static JSONObject jsonRequestBodyOlustur(){

        JSONObject requestBody = new JSONObject();
        JSONObject bookingDatesBody = new JSONObject();

        bookingDatesBody.put( "checkin" , "2021-06-01" );
        bookingDatesBody.put( "checkout" , "2021-06-10" );

        requestBody.put( "firstname" , "Ahmet" );
        requestBody.put( "lastname" , "Bulut" );
        requestBody.put( "totalprice" , 500 );
        requestBody.put( "depositpaid" , false );
        requestBody.put(  "bookingdates", bookingDatesBody );
        requestBody.put(  "additionalneeds" , "wi-fi");

        return requestBody;
    }

    public static JSONObject jsonResponseBodyOlustur(){

        JSONObject responseBody = new JSONObject();
        JSONObject bookingBody = jsonRequestBodyOlustur();
        responseBody.put( "bookingid", 24 );
        responseBody.put( "booking", bookingBody );

        return responseBody;
    }

    public static Map<String,Object> requestBodyMapOlustur(){

        Map<String,Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put( "firstname" , "Ahmet" );
        requestBodyMap.put( "lastname", "Bulut" );
        requestBodyMap.put( "totalprice" , 500.0 );
        requestBodyMap.put( "depositpaid" , false );
        requestBodyMap.put( "bookingdates" , bookingDatesMapOlustur());
        requestBodyMap.put( "additionalneeds" , "wi-fi" );

        return requestBodyMap;
    }

    public static Map<String,String> bookingDatesMapOlustur (){

        Map<String,String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin" , "2021-06-01"  );
        bookingDatesMap.put( "checkout" , "2021-06-10" );

        return bookingDatesMap;
    }

    public static Map<String,Object> responseBodyMapOlustur(){

        Map<String,Object> responseBodyMap = new HashMap<>();

        responseBodyMap.put( "bookingid", 24 );
        responseBodyMap.put( "booking", requestBodyMapOlustur() );

        return  responseBodyMap;
    }

}
