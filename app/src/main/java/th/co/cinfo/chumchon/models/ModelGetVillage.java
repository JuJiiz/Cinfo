package th.co.cinfo.chumchon.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ModelGetVillage {
    public static String getByName(Context context, String pname) {
        String apiURL = "https://api.cinfo.co.th/v2/village?";
        String result = "";
        SharedPreferences sp = context.getSharedPreferences("myStorage", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        String districtCode = ModelToken.getByName(token, "cus_data.DISTRICT_CODE");
        Log.d("XXXXXXXXXXXX", "districtCode: " + districtCode);
        try {
            String url = apiURL + "DISTRICT_CODE=" + districtCode ;
            String strGetJson = new CallApi().execute(url).get();
            Log.d("XXXXXXXXXXXX", "strGetJson: " + strGetJson);
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
