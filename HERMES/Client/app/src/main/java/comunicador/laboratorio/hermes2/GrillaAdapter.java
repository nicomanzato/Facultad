package comunicador.laboratorio.hermes2;
import android.graphics.Color;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.GridView;
import android.app.*;
import java.util.*;
import android.view.*;

/**
 * Created by nicolasmanzato on 13/12/15.
 */
public class GrillaAdapter extends BaseAdapter {

    private Activity _activity;

    private List<Pictograma> listaPictograma = new ArrayList<Pictograma>();
    private int imageWidth;

    public GrillaAdapter(Activity activity, List<Pictograma> listaPictograma,
                         int imageWidth) {
        this._activity = activity;
        this.listaPictograma = listaPictograma;
        this.imageWidth = imageWidth;
    }

    @Override
    public int getCount() {
        return this.listaPictograma.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaPictograma.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(_activity);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        imageView.setLayoutParams(new GridView.LayoutParams((int)Math.round(imageWidth), (int)Math.round(imageWidth)));
        imageView.setImageDrawable(_activity.getResources().getDrawable(this.listaPictograma.get(position).getImagen()));

        return imageView;

    }

    public void removeItem(int position){
        this.listaPictograma.remove(position);
    }

    public void addItem(Pictograma pictograma){
        this.listaPictograma.add(pictograma);
    }

    public void colorItem(int position){

    }

}
