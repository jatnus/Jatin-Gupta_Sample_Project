package Weather_Utilities;

import io.restassured.path.json.JsonPath;

public class JSONParser {

public static JsonPath Json(String response) {

JsonPath js = new JsonPath(response);
      return js;
}

}
