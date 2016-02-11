package it.jaschke.alexandria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import org.greenrobot.eventbus.EventBus;

import it.jaschke.alexandria.event.BarcodeScanEvent;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by ivan on 2/8/16.
 */
public class BarcodeScannerActivity extends Activity implements ZXingScannerView.ResultHandler {

    public static final int REQUEST_CODE = 100;

    private static final String TAG = "BarcodeScannerActivity";

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        Intent intent = new Intent();
        intent.putExtra(TAG, rawResult.getText());
        setResult(REQUEST_CODE, intent);

        EventBus.getDefault().postSticky(new BarcodeScanEvent(rawResult.getText()));

        finish();
    }
}
