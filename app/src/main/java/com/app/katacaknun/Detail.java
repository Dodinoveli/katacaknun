package com.app.katacaknun;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.katacaknun.RequestHandler.ApiServiceAll;
import com.app.katacaknun.endPoint.E_Detail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Detail extends AppCompatActivity {
    String data="";
    String KEY_ID="KEY_ID";
    String kat_id;
    TextView textView;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    Button button_salin_text,bagikan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        data = bundle.getString("KATA_ID");
        kat_id = bundle.getString("KAT_ID");
        getData(data);
        Log.d("tag","hasil"+data);
        Log.d("tag","kat id detail "+kat_id);
        textView  = (TextView)findViewById(R.id.txt_detail);
        textView.setMovementMethod(new ScrollingMovementMethod());
        button_salin_text = (Button)findViewById(R.id.button_salin_text);
        bagikan = (Button)findViewById(R.id.button_share);
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        button_salin_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();
            }
        });

        bagikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {

                    Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private  void getData(String data){
        Retrofit retrofit = ApiServiceAll.getRetrofitService();
        E_Detail endPoint      = retrofit.create(E_Detail.class);
        Call<ResponseBody>call = endPoint.getDetailData(data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    if (response.code()==200){
                        try {
                            JSONObject object   = new JSONObject(response.body().string().toString());
                            String status       = object.getString("status");
                            String data         = object.getString("data");
                            if (status.equals("true")){
                                JSONArray jsonArray = new JSONArray(data);
                                for (int item=0; item<jsonArray.length(); item++){
                                    JSONObject object1  = jsonArray.getJSONObject(item);
                                    String     isi      = object1.getString("isi");
                                    textView.setText(isi);
                                    Log.d("tag","isi "+isi);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.d("tag ","Result Gagal Ambil Data");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Detail.this,Kategory.class);
        intent.putExtra(KEY_ID,kat_id);
        finish();
        startActivity(intent);
        Log.d("tag ","onBackPressed = "+kat_id);
    }

    }
