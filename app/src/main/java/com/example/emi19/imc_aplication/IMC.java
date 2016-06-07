package com.example.emi19.imc_aplication;

import  android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class IMC extends AppCompatActivity {
    private EditText mEditTextPeso;
    private EditText mEditTextestatura;
    private Button mButtoncalcular;
    private TextView mTextViewindicado;
    private TextView mTextViewestado;
    private Button mButtonlimpiar;

    public void ocultarteclado(){
        InputMethodManager OCUT = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        OCUT.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        mEditTextPeso = (EditText) findViewById(R.id.editTextPeso);
        mEditTextestatura = (EditText) findViewById(R.id.editTextestatura);
        mButtoncalcular = (Button) findViewById(R.id.buttoncalcular);
        mTextViewindicado = (TextView) findViewById(R.id.textViewindicado);
        mTextViewestado = (TextView) findViewById(R.id.textViewestado);
        mButtonlimpiar = (Button) findViewById(R.id.buttonlimpiar);
        mButtonlimpiar.setVisibility(View.INVISIBLE);

        mButtonlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View o) {
                mTextViewestado.setText("");
                mTextViewindicado.setText("");
                mEditTextestatura.setText("");
                mEditTextPeso.setText("");
                double peso = 0,estatura = 0;
                mButtonlimpiar.setVisibility(View.INVISIBLE);
            }
        });

        mButtoncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ocultar teclado
                ocultarteclado();
                try {
                    if( mEditTextPeso.length() > 0 && mEditTextestatura.length() > 0){
                        double peso = Double.parseDouble(mEditTextPeso.getText().toString());
                        double estatura = Double.parseDouble(mEditTextestatura.getText().toString());
                        DecimalFormat df = new DecimalFormat("0.00");
                        double IMC = peso / (estatura * estatura);
                        String dato = df.format((Double) IMC);
                        mTextViewindicado.setText(dato);
                        double imc = IMC;
                        if (imc <= 18.49) {
                            mTextViewestado.setText("Peso Bajo ");
                        } else if (imc <= 24.99) {
                            mTextViewestado.setText("Peso Normal ");
                        } else if (imc <= 29.99) {
                            mTextViewestado.setText("SobrePeso ");
                        } else if (imc <= 39.99) {
                            mTextViewestado.setText("Obesidad ");
                        } else if (imc >= 40.0) {
                            mTextViewestado.setText("Obesidad Estrema ");
                        }
                        mButtonlimpiar.setVisibility(View.VISIBLE);
                    }else{
                        mButtonlimpiar.setVisibility(View.INVISIBLE);
                    }

                }catch (NumberFormatException w){

                    mTextViewestado.setText(w.getMessage());
                }
            }
        });
    }
    }

