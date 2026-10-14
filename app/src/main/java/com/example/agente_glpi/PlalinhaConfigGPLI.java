package com.example.agente_glpi;
import android.content.Context;
import android.util.Log;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class PlalinhaConfigGPLI {

    private static final String SPREADSHEET_ID = "1A7h66Wxo8hdMwk4NFCQ3VK1bS_VCg_m2Xrv7J3cYDrk";
    private static final org.apache.commons.logging.Log log = LogFactory.getLog(PlalinhaConfigGPLI.class);

    public static void enviarDados(Context context, String Cidade, String Equipamento, String Patrimonio, String NumeroSerie , String codicoglpi){
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {

                    // LE O ARUIVO .JSON MA PASTA /res/raw
                    InputStream in = context.getResources().openRawResource(R.raw.credenciais);
                    GoogleCredentials credentials = GoogleCredentials.fromStream(in).createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

                    //Configura a API google panilhas

                    Sheets sheetsService = new Sheets.Builder(
                            GoogleNetHttpTransport.newTrustedTransport(),
                            GsonFactory.getDefaultInstance(),
                            new HttpCredentialsAdapter(credentials)).setApplicationName("AGENTE GPLI").build();


                    //Monta dos Parametros das Colunas da Palinha

                    List<Object> novaLinha = Arrays.asList(Cidade, Equipamento, Patrimonio, NumeroSerie, codicoglpi);
                    List<List<Object>> dados = Arrays.asList(novaLinha);
                    ValueRange body = new ValueRange().setValues(dados);

                    String range = "pagina1!A:E";

                    sheetsService.spreadsheets().values()
                            .append(SPREADSHEET_ID, range, body)
                            .setValueInputOption("USER_ENTERED").execute();

                    Log.d("SUCESSO","Dados enviado para a planilha");

                } catch (Exception e){
                    Log.e("ERRO", "Faolha ao enviar arquivos" + e.getMessage(), e);
                }

            });

    }
}
