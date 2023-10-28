package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {
    private  String[][] docter_details ={
            {"Doctor Name : Ajit baste","Hospital Address :  Pimpri","Exp : 5yrs","Mobile No. : 9349248938","600"},
            {"Doctor Name : Prasad Pawar","Hospital Address :  Pali","Exp : 15yrs","Mobile No. : 9929448930","900"},
            {"Doctor Name : RamPrasad","Hospital Address :  Pune","Exp : 9yrs","Mobile No. : 8723538938","300"},
            {"Doctor Name : Deepak Deshmuk","Hospital Address :  jodhpur","Exp : 7yrs","Mobile No. : 9349688233","720"},
            {"Doctor Name : Ashok Panda","Hospital Address :  jaipur","Exp : 20yrs","Mobile No. : 8723456178","1200"},

    };
    private  String[][] docter_details1 ={
            {"Doctor Name : Ajit baste","Hospital Address :  Pimpri","Exp : 5yrs","Mobile No. : 9349248938","600"},
            {"Doctor Name : Prasad Pawar","Hospital Address :  Pali","Exp : 15yrs","Mobile No. : 9929448930","900"},
            {"Doctor Name : RamPrasad","Hospital Address :  Pune","Exp : 9yrs","Mobile No. : 8723538938","300"},
            {"Doctor Name : Deepak Deshmuk","Hospital Address :  jodhpur","Exp : 7yrs","Mobile No. : 9349688233","720"},
            {"Doctor Name : Ashok Panda","Hospital Address :  jaipur","Exp : 20yrs","Mobile No. : 8723456178","1200"},

    };
    private  String[][] docter_details2 ={
            {"Doctor Name : Ajit baste","Hospital Address :  Pimpri","Exp : 5yrs","Mobile No. : 9349248938","600"},
            {"Doctor Name : Prasad Pawar","Hospital Address :  Pali","Exp : 15yrs","Mobile No. : 9929448930","900"},
            {"Doctor Name : RamPrasad","Hospital Address :  Pune","Exp : 9yrs","Mobile No. : 8723538938","300"},
            {"Doctor Name : Deepak Deshmuk","Hospital Address :  jodhpur","Exp : 7yrs","Mobile No. : 9349688233","720"},
            {"Doctor Name : Ashok Panda","Hospital Address :  jaipur","Exp : 20yrs","Mobile No. : 8723456178","1200"},

    };
    private  String[][] docter_details3 ={
            {"Doctor Name : Ajit baste","Hospital Address :  Pimpri","Exp : 5yrs","Mobile No. : 9349248938","600"},
            {"Doctor Name : Prasad Pawar","Hospital Address :  Pali","Exp : 15yrs","Mobile No. : 9929448930","900"},
            {"Doctor Name : RamPrasad","Hospital Address :  Pune","Exp : 9yrs","Mobile No. : 8723538938","300"},
            {"Doctor Name : Deepak Deshmuk","Hospital Address :  jodhpur","Exp : 7yrs","Mobile No. : 9349688233","720"},
            {"Doctor Name : Ashok Panda","Hospital Address :  jaipur","Exp : 20yrs","Mobile No. : 8723456178","1200"},

    };
    private  String[][] docter_details4 ={
            {"Doctor Name : Ajit baste","Hospital Address :  Pimpri","Exp : 5yrs","Mobile No. : 9349248938","600"},
            {"Doctor Name : Prasad Pawar","Hospital Address :  Pali","Exp : 15yrs","Mobile No. : 9929448930","900"},
            {"Doctor Name : RamPrasad","Hospital Address :  Pune","Exp : 9yrs","Mobile No. : 8723538938","300"},
            {"Doctor Name : Deepak Deshmuk","Hospital Address :  jodhpur","Exp : 7yrs","Mobile No. : 9349688233","720"},
            {"Doctor Name : Ashok Panda","Hospital Address :  jaipur","Exp : 20yrs","Mobile No. : 8723456178","1200"},

    };
    String[][] docter_detail = {} ;
    TextView tv;
    HashMap<String, String> item;
    ArrayList list;
    Button btn ;
    SimpleAdapter sa;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        tv = findViewById(R.id.textviewHADtitle);
        btn = findViewById(R.id.btnLDGoBack);
        Intent it = getIntent();
        String title =  it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family Physician")==0){
            docter_detail=docter_details;
        } else
        if(title.compareTo("Dietician")==0){
            docter_detail=docter_details1;
        } else
        if(title.compareTo("Dentist")==0){
            docter_detail=docter_details2;
        } else
        if(title.compareTo("Surgeon")==0){
            docter_detail=docter_details3;
        } else{
            docter_detail=docter_details4;
        }


            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailActivity.this , fFndDoctorActivity.class));
            }
        });

        list = new ArrayList();
                for(int i=0 ; i<docter_detail.length;i++) {
                    item = new HashMap<String, String>();
                    item.put("line1", docter_detail[i][0]);
                    item.put("line2", docter_detail[i][1]);
                    item.put("line3", docter_detail[i][2]);
                    item.put("line4", docter_detail[i][3]);
                    item.put("line5", "cons Fees:" + docter_detail[i][4] + "/-");
                    list.add(item);
                }
                sa =new SimpleAdapter(this,list,
                            R.layout.multilines,
                            new String[]{"line1","line2","line3","line4","line5"},
                            new  int[]{R.id.line_a ,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ListView lst = findViewById(R.id.listviewLT);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Handle the item click here
                Intent it =  new Intent(DoctorDetailActivity.this , BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2", docter_detail[i][0]);
                it.putExtra("text3", docter_detail[i][1]);
                it.putExtra("text4", docter_detail[i][3]);
                it.putExtra("text5", docter_detail[i][4]);
                startActivity(it);
            }
        });
                }


}