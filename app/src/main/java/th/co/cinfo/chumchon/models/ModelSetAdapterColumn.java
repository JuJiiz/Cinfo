package th.co.cinfo.chumchon.models;

import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import th.co.cinfo.chumchon.R;

/**
 * Created by JuJiiz on 20/7/2560.
 */

public class ModelSetAdapterColumn {
    public static void setHeadAdapter(Context context, ArrayList LIST, String COLUMN_NUMBER, String OWNER_NAME_COLUMN, String STATUS_COLUMN, ListView listView) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, LIST, R.layout.view_head_column,
                new String[]{COLUMN_NUMBER, OWNER_NAME_COLUMN, STATUS_COLUMN},
                new int[]{R.id.tvNumberHead, R.id.tvOwnerNameHead, R.id.tvProgressHead}
        );
        listView.setAdapter(simpleAdapter);
    }

    public static void setChildAdapter(Context context, ArrayList LIST, String COLUMN_NUMBER, String OWNER_NAME_COLUMN, String STATUS_COLUMN, String TASKID_COLUMN, ListView listView) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, LIST, R.layout.view_child_column,
                new String[]{COLUMN_NUMBER, OWNER_NAME_COLUMN, STATUS_COLUMN, TASKID_COLUMN},
                new int[]{R.id.tvNumberChild, R.id.tvOwnerNameChild, R.id.tvStatusChild, R.id.tvTaskID}
        );
        listView.setAdapter(simpleAdapter);
    }
}
