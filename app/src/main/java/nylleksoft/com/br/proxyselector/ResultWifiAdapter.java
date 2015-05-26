package nylleksoft.com.br.proxyselector;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by msilva on 5/26/15.
 */
public class ResultWifiAdapter extends BaseAdapter {

    private List<ScanResult> scanResults;
    private Context mContext;
    private LayoutInflater mInflater;

    public ResultWifiAdapter(Context context, List<ScanResult> results) {
        this.mContext = context;
        this.scanResults = results;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return scanResults.size();
    }

    @Override
    public ScanResult getItem(int position) {
        return scanResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = mInflater.inflate(R.layout.list_item_wifi_result, parent, false);

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        if(viewHolder == null) {
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        ScanResult scanResult = getItem(position);

        viewHolder.name.setText(scanResult.SSID);
        viewHolder.signal.setImageResource(getResourceSignal(WifiManager.calculateSignalLevel(scanResult.level, 4)));

        return convertView;
    }

    private int getResourceSignal(int level) {
        switch (level) {
            case 0:
                return  R.drawable.ic_wifi_strength_0;

            case 1:
                return  R.drawable.ic_wifi_strength_1;

            case 2:
                return  R.drawable.ic_wifi_strength_2;

            case 3:
                return  R.drawable.ic_wifi_strength_3;

            default:
                break;
        }

        return 0;
    }

    class ViewHolder {
        TextView name;
        ImageView signal;

        ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            signal = (ImageView) view.findViewById(R.id.signal);
        }
    }
}
