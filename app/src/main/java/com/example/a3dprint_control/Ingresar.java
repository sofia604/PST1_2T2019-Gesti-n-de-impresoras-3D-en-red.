package com.example.a3dprint_control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ingresar extends AppCompatActivity {

    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "cD2kkGazJC";
    private String pwdMySQL = "34WoCvLgEW";
    private String database = "cD2kkGazJC";
    private String[] datosConexion = null;
    private EditText corr;
    private EditText contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        corr=(EditText)findViewById(R.id.correo);
        contra=(EditText)findViewById(R.id.contraseña);
    }

    public void volver(View view) {
        finish();
    }

    public void verificarUsuario(View view)
    {
        String[] resultadoSQL = null;
        try{
            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    "SELECT * FROM Usuario;"
            };
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            resultadoSQL = new AsyncQuery().execute(datosConexion).get();
            Toast.makeText(Ingresar.this,"Conexión Establecida", Toast.LENGTH_LONG).show();

            String resultadoConsulta = resultadoSQL[0];
            if ((resultadoConsulta.indexOf(corr.getText().toString())>-1) &&(resultadoConsulta.indexOf(contra.getText().toString())>-1)){
                Toast.makeText(this,"Si existe el usuario", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Usuario no valido", Toast.LENGTH_LONG).show();
            }
        }catch(Exception ex)
        {
            Toast.makeText(this, "Error al obtener resultados de la consulta Transact-SQL: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}