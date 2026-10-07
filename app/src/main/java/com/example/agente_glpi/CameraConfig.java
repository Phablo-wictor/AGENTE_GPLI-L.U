package com.example.agente_glpi;
import android.app.Activity;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;
public class CameraConfig {
    public interface ScanCallback {
        void onSuccess(String codigoLido);

        void onCanceled();

        void onError(Exception e);

    }
        public static void iniciarLeitura(Activity activity, ScanCallback callback) {

            GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                    .setBarcodeFormats(
                            Barcode.FORMAT_QR_CODE,
                            Barcode.FORMAT_EAN_13,
                            Barcode.FORMAT_CODE_128,
                            Barcode.FORMAT_ALL_FORMATS
                    )
                    .enableAutoZoom()
                    .build();

            GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(activity, options);

            scanner.startScan()
                    .addOnSuccessListener(barcode -> {
                        String valorLido = barcode.getRawValue();
                        if (callback != null) {
                            callback.onSuccess(valorLido);
                        }
                    })
                    .addOnCanceledListener(() -> {
                        if (callback != null) {
                            callback.onCanceled();
                        }
                    })
                    .addOnFailureListener(e -> {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    });
        }

}
