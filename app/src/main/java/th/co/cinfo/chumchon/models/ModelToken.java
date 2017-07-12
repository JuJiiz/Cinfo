package th.co.cinfo.chumchon.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by ZaiMon on 7/7/2560.
 */

public class ModelToken {

    public static String getByName(String ptoken,String pname){
        String result = "";
        try {
            String url = "https://api.cinfo.co.th/v1/userData?token="+ptoken;
            String strGetJson = new CallApi().execute(url).get();

            JSONObject jsonObject = new JSONObject(strGetJson);
            result = jsonObject.getString(pname);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
