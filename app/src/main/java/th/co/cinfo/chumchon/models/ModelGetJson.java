package th.co.cinfo.chumchon.models;

import android.content.Context;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by JuJiiz on 20/7/2560.
 */

public class ModelGetJson {
    public static void getHeadJson(Context context, String apiURL, String pKey, ListView listView) {
        String HEAD_COLUMN_NUMBER = "number";
        String HEAD_OWNER_NAME_COLUMN = "name";
        String HEAD_STATUS_COLUMN = "status";
        String status_count = "count";
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        JSONObject jsonObject = null;
        try {
            String strJsonObj = ModelGetData.getByName(context, apiURL, pKey);
            JSONArray jsonArray = new JSONArray(strJsonObj);
            for (int i = 0; i < jsonArray.length(); i++) {
                int statusCount = 0, progress = 0;
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(HEAD_COLUMN_NUMBER, jsonObj.getString("ID"));
                temp.put(HEAD_OWNER_NAME_COLUMN, jsonObj.getString("owner"));
                LIST.add(temp);
                temp.put(HEAD_STATUS_COLUMN, jsonObj.getString("status"));

                JSONArray tmpJsonArray = new JSONArray(jsonObj.getString("asset"));
                if (tmpJsonArray.length() != 0) {
                    for (int j = 0; j < tmpJsonArray.length(); j++) {
                        JSONObject jsonObjcheck = tmpJsonArray.getJSONObject(j);
                        status_count = jsonObjcheck.getString("status");
                        if (!status_count.equals("wait")) {
                            statusCount += 1;
                        }
                    }
                    progress = (statusCount * 100) / tmpJsonArray.length();
                }
                temp.put(HEAD_STATUS_COLUMN, progress + "%");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setHeadAdapter(context, LIST, HEAD_COLUMN_NUMBER, HEAD_OWNER_NAME_COLUMN, HEAD_STATUS_COLUMN, listView);
    }

    public static void getChildJson(Context context, String apiURL, String pKey, ListView listView) {
        String CHILD_COLUMN_NUMBER = "number";
        String CHILD_OWNER_NAME_COLUMN = "name";
        String CHILD_STATUS_COLUMN = "status";
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        JSONObject jsonObject = null;
        try {
            String strJsonObj = ModelGetData.getByName(context, apiURL, pKey);
            JSONArray jsonArray = new JSONArray(strJsonObj);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(CHILD_COLUMN_NUMBER, jsonObj.getString("H_NO"));
                JSONObject tmpJsonObj = new JSONObject(jsonObj.getString("zone"));
                temp.put(CHILD_OWNER_NAME_COLUMN, tmpJsonObj.getString("VIL_NAME"));
                tmpJsonObj = new JSONObject(jsonObj.getString("SURV_Status"));
                temp.put(CHILD_STATUS_COLUMN, tmpJsonObj.getString("status"));
                LIST.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setChildAdapter(context, LIST, CHILD_COLUMN_NUMBER, CHILD_OWNER_NAME_COLUMN, CHILD_STATUS_COLUMN, listView);
    }
}