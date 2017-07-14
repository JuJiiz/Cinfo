package th.co.cinfo.chumchon.models;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ModelToken {

    public static String getByName(String ptoken,String pname){
        String result = "";
        try {
            String url = "https://api.cinfo.co.th/v2/userData?token=" + ptoken;
            String strGetJson = new CallApi().execute(url).get();

            String[] keyNames = pname.split("\\.");
            JSONObject jsonObject = new JSONObject(strGetJson);
            for (int i = 0; i < keyNames.length; i++){
                if(i == keyNames.length-1){
                    result = jsonObject.getString(keyNames[i]);
                }else{
                    jsonObject = jsonObject.getJSONObject(keyNames[i]);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //test55
        return result;
    }
}
