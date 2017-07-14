package th.co.cinfo.chumchon.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ModelHousehold {
    public static String getByName(Context context, String pname) {
        String result = "";
        SharedPreferences sp = context.getSharedPreferences("My_Storage", Context.MODE_PRIVATE);
        String token = sp.getString("token","");
        Log.d("XXXXXXXXXXXX", "token: "+token);
        String districtCode = ModelToken.getByName(token,"cus_data.DISTRICT_CODE");
        Log.d("XXXXXXXXXXXX", "districtCode: "+districtCode);
        try {
            String url = "https://api.cinfo.co.th/v2/getTaskList_F01_01?DISTRICT_CODE=" + districtCode + "&token=" + token;
            String strGetJson = new CallApi().execute(url).get();
            Log.d("XXXXXXXXXXXX", "strGetJson: "+strGetJson);

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
