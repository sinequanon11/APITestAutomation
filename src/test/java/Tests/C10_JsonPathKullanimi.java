package Tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C10_JsonPathKullanimi {

    @Test
    public void method1(){

        JSONObject kisiBilgileriJsonObj = new JSONObject();

        JSONObject adresJsonObj = new JSONObject();

        JSONArray telefonBilgileriArr = new JSONArray();
        JSONObject cepTelefonuJsonObj = new JSONObject();
        JSONObject evTelefonuJsonObj = new JSONObject();

        adresJsonObj.put( "streetAddress", "naist street" );
        adresJsonObj.put( "city", "Nara" );
        adresJsonObj.put( "postalCode", "630-0192" );

        cepTelefonuJsonObj.put( "type", "IPhone" );
        cepTelefonuJsonObj.put( "number", "0123-4567-8888" );
        evTelefonuJsonObj.put( "type", "home" );
        evTelefonuJsonObj.put( "number", "0123-4567-8910" );

        telefonBilgileriArr.put( cepTelefonuJsonObj );
        telefonBilgileriArr.put( evTelefonuJsonObj );

        kisiBilgileriJsonObj.put( "firstName", "John" );
        kisiBilgileriJsonObj.put( "lastName", "doe" );
        kisiBilgileriJsonObj.put( "age", 26 );
        kisiBilgileriJsonObj.put( "address", adresJsonObj );
        kisiBilgileriJsonObj.put( "phoneNumbers", telefonBilgileriArr );

        System.out.println(kisiBilgileriJsonObj);

        System.out.println("First Name is : " + kisiBilgileriJsonObj.get( "firstName" ));

        System.out.println("Street is : " + kisiBilgileriJsonObj.getJSONObject( "address" ).get( "streetAddress" ));

        System.out.println("City is : " + kisiBilgileriJsonObj.getJSONObject( "address" ).get( "city" ));

        System.out.println("Cep telefon no : " + kisiBilgileriJsonObj
                .getJSONArray( "phoneNumbers" ).getJSONObject( 0 ).get( "number" ));

    }
}
