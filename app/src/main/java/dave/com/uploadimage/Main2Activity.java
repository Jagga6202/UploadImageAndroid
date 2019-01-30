package dave.com.uploadimage;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
RecAdapter adapter;
RecyclerView rec;
ArrayList<RecModel> arrayList=new ArrayList<>();
String imagepath,imagetag;
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rec=(RecyclerView)findViewById(R.id.rec);
        loadiamge();
    }

    public void loadiamge(){
        progressDialog = ProgressDialog.show(Main2Activity.this,"Loading Data",null,true,true);
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest st=new StringRequest(Request.Method.POST, EndPoints.GET_PICS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.e("Response",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("images");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject j=jsonArray.getJSONObject(i);
                        imagepath=j.getString("image");
                        imagetag=j.getString("tags");
                        Log.e("imagepath",imagepath);
                        arrayList.add(new RecModel(imagepath,imagetag));
                    }
                    adapter=new RecAdapter(Main2Activity.this,arrayList);
                    rec.setAdapter(adapter);
                    rec.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                    rec.addItemDecoration(new DividerItemDecoration(Main2Activity.this,DividerItemDecoration.VERTICAL));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("VolleyError",""+error);
            }
        });
        requestQueue.add(st);
    }
}
