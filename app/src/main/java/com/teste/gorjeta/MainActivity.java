package com.teste.gorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText    editValor;
    private SeekBar     seekBarGorjeta;
    private TextView    textPorcentagem;
    private TextView    textGorjeta;
    private TextView    textTotal;
    private double porcentagem = 0;

    private CheckBox checkBoxDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor             = findViewById(R.id.editValor);
        seekBarGorjeta        = findViewById(R.id.seekBarGorjeta);
        textPorcentagem       = findViewById(R.id.textPorcentagem);
        textGorjeta           = findViewById(R.id.textGorjeta);
        textTotal             = findViewById(R.id.textTotal);

        //CHECKBOX
        checkBoxDouble        = findViewById(R.id.checkBoxDouble);

        //ADICIONAR LISTENER
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                textPorcentagem.setText(Math.round(porcentagem) + " %");
                calcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    public void calcular() {
        String valorRecuperado = editValor.getText().toString();

        //Adicionando float
        String valorTotal = textTotal.getText().toString();
        String valorGorjeta = textGorjeta.getText().toString();

        if (checkBoxDouble.isChecked()) {
            double valorDigitado = Double.parseDouble(valorRecuperado);

            double gorjeta = valorDigitado * (porcentagem/100);
            double total = gorjeta + valorDigitado;

            textGorjeta.setText("R$ " + gorjeta);
            textTotal.setText("R$ " + total);

        }else if (valorRecuperado == null || valorRecuperado.equals("")) {
            Toast.makeText(this, "Digite algo", Toast.LENGTH_SHORT).show();

        }else{
            double valorDigitado = Double.parseDouble(valorRecuperado);
            double gorjeta = valorDigitado * (porcentagem/100);
            double total = gorjeta + valorDigitado;

            textGorjeta.setText("R$ " + Math.round(gorjeta));
            textTotal.setText("R$ " + Math.round(total));

        }
    }

}

