package com.hatz.trafficker;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusRecViewAdapter extends  RecyclerView.Adapter<BusRecViewAdapter.ViewHolder>{
    @NonNull

    private Context mContext;
    private ArrayList<Business> businesses =new ArrayList<>();

    public BusRecViewAdapter(@NonNull Context mContext) {
        this.mContext = mContext;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_business,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d("@@@@","OnBindViewHolder called");
        holder.productiontextview.setText("Production: " +businesses.get(position).getProduction() +" /month");
        holder.updatelevel.setText("Upgrade level :"+businesses.get(position).getLevel());
        holder.buybuss.setText("BUY FOR " + businesses.get(position).getPrice()/1000+"K $");
        if (businesses.get(position).getPrice()>999999)
            holder.buybuss.setText("BUY FOR "+businesses.get(position).getPrice()/1000000+"M $");
        holder.upgradebtn.setText("UPGRADE FOR "+(businesses.get(position).getUpgradePriceLvl()/1000)+"K $");
        if (businesses.get(position).getUpgradePriceLvl()>999999)
            holder.upgradebtn.setText("UPGRADE FOR "+businesses.get(position).getUpgradePriceLvl()/1000000+"M $");
        holder.businessname.setText(businesses.get(position).getName());
        if(holder.businessname.getText().equals("Weed Farm"))
                   holder.businessimage.setImageResource(R.mipmap.weedbusiness);
        if(holder.businessname.getText().equals("PCP Lab"))
            holder.businessimage.setImageResource(R.mipmap.pcplab);
        if(holder.businessname.getText().equals("LSD Lab"))
            holder.businessimage.setImageResource(R.mipmap.lsdlab);
        if(holder.businessname.getText().equals("Heroin Lab"))
            holder.businessimage.setImageResource(R.mipmap.heroinlab);
        if(holder.businessname.getText().equals("Crack Lab"))
            holder.businessimage.setImageResource(R.mipmap.cracklab);
        int pos=MainActivity.havethisbusiness(businesses.get(position));
        if(pos!=-1)
        {
            holder.productiontextview.setText("Production: " +MainActivity.businesses.get(pos).getProduction() +" /month");
            holder.updatelevel.setText("Upgrade level :"+ MainActivity.businesses.get(pos).getLevel());
            int upgradeprice=MainActivity.businesses.get(pos).getUpgradePriceLvl();
            if(upgradeprice>999)
                holder.upgradebtn.setText("UPGRADE FOR "+(upgradeprice/1000)+"K $");
            if (upgradeprice>999999)
                holder.upgradebtn.setText("UPGRADE FOR "+upgradeprice/1000000+"M $");
            holder.buybuss.setVisibility(View.GONE);
            holder.upgradebtn.setVisibility(View.VISIBLE);

            if (MainActivity.businesses.get(pos).getLevel()==3)
            {
                holder.upgradebtn.setText("MAX UPGRADE");
                holder.upgradebtn.setEnabled(false);
            }
        }
        holder.buybuss.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(MainActivity.buybusiness(businesses.get(position)))
                {
                    holder.buybuss.setVisibility(View.GONE);
                    holder.upgradebtn.setVisibility(View.VISIBLE);
                   // Toast.makeText(mContext, businesses.get(position).getName()+" successfully purchased.", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }

            }
        });
        holder.upgradebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.upgradebusiness(businesses.get(position)))
                {
                    //Toast.makeText(mContext, businesses.get(position).getName()+" successfully upgraded", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }

    public void setBusinesses(ArrayList<Business> businesses) {
        this.businesses = businesses;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button buybuss,upgradebtn;
        private CardView cardView;
        private TextView businessname,productiontextview,updatelevel;
        private ImageView businessimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            upgradebtn=itemView.findViewById(R.id.upgradebtn);
            buybuss=itemView.findViewById(R.id.buybus);
            productiontextview=itemView.findViewById(R.id.productiontextview);
            updatelevel=itemView.findViewById(R.id.upgradelevel);
            cardView=itemView.findViewById(R.id.parent);
            businessimage=itemView.findViewById(R.id.businessimage);
            businessname=itemView.findViewById(R.id.businessname);
        }
    }
}
