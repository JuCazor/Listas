package com.example.listas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView simpleList;
    Button btn;
    int pos;
    Button btnEli;
    Button btnEdi;
    EditText edt;
    boolean editando = false;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> lista;
    String countryList[] = {"India","China","Australia","Rusia","Nueva Zelanda"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View inflatedView = getLayoutInflater().inflate(R.layout.activity_listview, null);
//
  //      btnEdi = inflatedView.findViewById(R.id.edit);
        //btnEli = inflatedView.findViewById(R.id.elim);

        //setContentView(R.layout.activity_listview);


        simpleList = findViewById(R.id.simpleList);
        btn = findViewById(R.id.button);
        edt = findViewById(R.id.editText);
        btn.setText("agregar");
        btnEdi = findViewById(R.id.edit);
        btnEli = findViewById(R.id.elim);

        lista = new ArrayList <String> (Arrays.asList(countryList));

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.activity_listview, R.id.textView,lista);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editando){
                    String nuevoTexto = edt.getText().toString();
                    lista.remove(pos);
                    lista.add(pos,nuevoTexto);
                    arrayAdapter.notifyDataSetChanged();
                    editando = false;
                    edt.setText("");

                }else{
                    String country = edt.getText().toString();
                    simpleList.setAdapter(arrayAdapter);
                    lista.add(country);
                    arrayAdapter.notifyDataSetChanged();
                    edt.setText("");
                }

            }
        });

    }

    public void eliminar(View view) {
        View item = (View) view.getParent();
        int pos = simpleList.getPositionForView(item);
        lista.remove(pos);
        arrayAdapter.notifyDataSetChanged();
    }

    public void editar(View view) {
        View item = (View) view.getParent();
        pos = simpleList.getPositionForView(item);
        edt.setText(""+lista.get(pos).toString());
        editando = true;
        btn.setText("aceptar");

    }
}
