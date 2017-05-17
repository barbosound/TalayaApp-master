package com.example.pau.talaya;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.pau.talaya.LoginActivity.usuariActiu;

/**
 * Created by Pau on 26/4/17.
 */

public class AdapterReserva extends ArrayAdapter<String> {

    private ArrayList<String> idReserva = new ArrayList<>();
    private ArrayList<String> nom = new ArrayList<>();
    private ArrayList<String> preuReserva = new ArrayList<>();
    private ArrayList<String> diesReserva = new ArrayList<>();
    private ArrayList<String> DataEntrada = new ArrayList<>();
    private ArrayList<String> DataSortida = new ArrayList<>();
    private ArrayList<String> FKUsuari = new ArrayList<>();
    private ArrayList<String> FKCasa = new ArrayList<>();
    private ArrayList<String> Estat = new ArrayList<>();

    String oldFormat, newFormat, strEntrada, strSortida;
    SimpleDateFormat sdf1,sdf2;

    public AdapterReserva(Context context, int layoutResourceId, ArrayList<String> idReserva, ArrayList<String> nom, ArrayList<String> preuReserva, ArrayList<String> diesReserva, ArrayList<String> DataEntrada,ArrayList<String> DataSortida,ArrayList<String> FKCasa,ArrayList<String> Estat, ArrayList<String> FKUsuari) {
        super(context, layoutResourceId, idReserva);

        this.idReserva = idReserva;
        this.nom = nom;
        this.preuReserva = preuReserva;
        this.diesReserva = diesReserva;
        this.DataEntrada = DataEntrada;
        this.DataSortida = DataSortida;
        this.FKUsuari = FKUsuari;
        this.FKCasa = FKCasa;
        this.Estat = Estat;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view,viewLlista,viewNoData;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.row_reserva,null);
        viewLlista = inflater.inflate(R.layout.fragment_pendents,null);

        LinearLayout rowColor = (LinearLayout)view.findViewById(R.id.rowReserva);

        TextView txtnom = (TextView)view.findViewById(R.id.textNom);
        TextView txtdata = (TextView)view.findViewById(R.id.textData);


        if (FKUsuari.get(position).equals(String.valueOf(usuariActiu.getIdUsuari()))){

            switch (Estat.get(position)){

                case ("Pendent"):

                    rowColor.setBackgroundColor(Color.parseColor("#ff6363"));

                    txtnom.setTextColor(Color.WHITE);
                    txtdata.setTextColor(Color.WHITE);

                    oldFormat = "MM/dd/yyyy HH:mm:ss";
                    newFormat = "dd/MM/yyyy";

                    sdf1 = new SimpleDateFormat(oldFormat);
                    sdf2 = new SimpleDateFormat(newFormat);

                    strEntrada = DataEntrada.get(position);
                    strSortida = DataSortida.get(position);

                    try {
                        strEntrada = sdf2.format(sdf1.parse(strEntrada));
                        strSortida = sdf2.format(sdf1.parse(strSortida));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    txtdata.setText(strEntrada +" - "+ strSortida);

                    txtnom.setText(nom.get(Integer.parseInt(FKCasa.get(position)) - 1));

                    break;

                case ("Acceptada"):

                    rowColor.setBackgroundColor(Color.GREEN);

                    txtnom.setTextColor(Color.WHITE);
                    txtdata.setTextColor(Color.WHITE);

                    oldFormat = "MM/dd/yyyy HH:mm:ss";
                    newFormat = "dd/MM/yyyy";

                    sdf1 = new SimpleDateFormat(oldFormat);
                    sdf2 = new SimpleDateFormat(newFormat);

                    strEntrada = DataEntrada.get(position);
                    strSortida = DataSortida.get(position);

                    try {
                        strEntrada = sdf2.format(sdf1.parse(strEntrada));
                        strSortida = sdf2.format(sdf1.parse(strSortida));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    txtdata.setText(strEntrada +" - "+ strSortida);

                    txtnom.setText(nom.get(Integer.parseInt(FKCasa.get(position)) - 1));

                    break;

                case ("Finalitzada"):
                    break;
            }
        }

        return view;

    }

}
