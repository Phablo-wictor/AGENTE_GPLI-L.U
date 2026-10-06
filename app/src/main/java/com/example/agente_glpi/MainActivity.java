package com.example.agente_glpi;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });


        //Adapter do Spinner Menu da cidade
        Spinner SpinnerMenuCity = findViewById(R.id.Menu_City);

        ArrayAdapter<CharSequence> adapterMenuCity = ArrayAdapter.createFromResource(this, R.array.Menu_City, android.R.layout.simple_spinner_item);

        adapterMenuCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerMenuCity.setAdapter(adapterMenuCity);


        //adpater do Spinner Menu tipo de Equipamento

        Spinner SpinnerTipoEquipamento = findViewById(R.id.Tipo_Equipamento);

        ArrayAdapter<CharSequence> adapterTipoEquipamento = ArrayAdapter.createFromResource(this, R.array.Menu_TipoEquipamento, android.R.layout.simple_spinner_dropdown_item);

        adapterTipoEquipamento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerTipoEquipamento.setAdapter(adapterTipoEquipamento);


        // Configuração dos Campos de escrita do Tombamento

        EditText TextoTombo = findViewById(R.id.CampoTextTombo);

        // Configuração do Campo de escrita do Numero de Serie

        EditText TextoNumeroSerie = findViewById(R.id.CampoTextoNS);

        //Configuração do Campo de Texto Resultado

        TextView textMostraResultado = findViewById(R.id.mostra_resultado);

        //Configura o swicth para ATIVA E DESATIVA O CAMPOS DE TOMBAMENTO DE PATRIMONIO

        Switch swich_escolha_tomb = findViewById(R.id.escolha_tomabamento_swicth);

        swich_escolha_tomb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonview, boolean isChecked) {
                if (isChecked) {

                    TextoTombo.setEnabled(true);
                    TextoNumeroSerie.setEnabled(false);
                    TextoNumeroSerie.setText("");

                } else {
                    ;
                    TextoTombo.setEnabled(false);
                    TextoNumeroSerie.setEnabled(true);
                    TextoTombo.setText("");

                }
            }
        });

        // Configuração do butão de Mostra Resultado
        Button ButaoResultado = findViewById(R.id.button_MostraResultado);

        ButaoResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String opcaoEscolidaMenuCity = SpinnerMenuCity.getSelectedItem().toString();
                String textFormatadoMenuCity = opcaoEscolidaMenuCity.substring(0,2);

                String opcaoEsclidaEquiapmento = SpinnerTipoEquipamento.getSelectedItem().toString();
                String textFarmatadoEquipamento = opcaoEsclidaEquiapmento.substring(0,2);

                String TextoDigitadoTombo = TextoTombo.getText().toString().trim();

                String TextoDigitadoNS = TextoNumeroSerie.getText().toString().trim();

                textMostraResultado.setText(textFormatadoMenuCity + textFarmatadoEquipamento + TextoDigitadoTombo + TextoDigitadoNS);

            }

        });

        //Configuração do butão de limpa o resultado

        Button ButaoLimpaResultado = findViewById(R.id.button_limpaResultado);

        ButaoLimpaResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textMostraResultado.setText("");
            }
        });

///

       }
}