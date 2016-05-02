package com.example.simps.bth_escribe;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mcc.ul.AiChanMode;
import com.mcc.ul.AiDevice;
import com.mcc.ul.AiScanOption;
import com.mcc.ul.AiUnit;
import com.mcc.ul.AoDevice;
import com.mcc.ul.DaqDevice;
import com.mcc.ul.DaqDeviceDescriptor;
import com.mcc.ul.DaqDeviceManager;
import com.mcc.ul.DigitalPortType;
import com.mcc.ul.DioDevice;
import com.mcc.ul.Range;
import com.mcc.ul.Status;
import com.mcc.ul.ULException;

import java.util.ArrayList;
import java.util.EnumSet;

public class MainActivity extends AppCompatActivity {

    private Button connect;
    private Button max;
    private Button min;
    private DaqDeviceManager ddm;
    private DaqDevice dd;
    private AiDevice aid;
    private DioDevice dod;
    ArrayList<DaqDeviceDescriptor> ddi;
    public static   DigitalPortType AUXPORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("hola", "main");
        AUXPORT=DigitalPortType.AUXPORT;

        //inicializa tarjeta
        ddm = new DaqDeviceManager(this);
        ddi = ddm.getDaqDeviceInventory();
        if(ddi.size() > 0){
        dd = ddm.createDaqDevice(ddi.get(0));}

        //conecta tarjeta
        Log.d("hola","tarjeta");


        Log.d("hola","botones");




        connect=(Button)findViewById(R.id.connect);
        max=(Button)findViewById(R.id.max);
        min=(Button)findViewById(R.id.min);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Log.d("hola","connect");

                    dd.connect();
                    dd.flashLed(3);
                    aid = dd.getAiDev();
                    dod = dd.getDioDev();

        }catch (ULException e){
            Log.d("hola","crash");
            e.printStackTrace();
        }
            }

        });

        max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hola","max");

               try{        dod.dOut( AUXPORT,255 );
                   Log.d("hola","exito");
                }catch (ULException e){

                   e.printStackTrace();
                }

            }

        });
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{        dod.dOut(AUXPORT,0 );

                }catch (ULException e){
                    e.printStackTrace();
                }


            }

        });


//escritura de puerto va de 000 a 255, dependiendo del boton variara el valor
        /*try{        dod.dOut(AUXPORT,0 );

        }catch (ULException e){
            e.printStackTrace();
        }*/









    }



}
