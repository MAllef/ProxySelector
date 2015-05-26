package nylleksoft.com.br.proxyselector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements View.OnClickListener {

    @ViewById
    ListView lvWifi;

    private WifiManager wifiManager;
    private List<ScanResult> scanResultList;
    private ResultWifiAdapter mAdapter;

    @AfterViews
    void afterViews() {
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        wifiManager.startScan();
    }

    @Receiver(actions = WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
    void scanResults(Intent intent) {
        scanResultList = wifiManager.getScanResults();
        showResultList();
    }

    public void showResultList() {
        mAdapter = new ResultWifiAdapter(this, scanResultList);
        lvWifi.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
