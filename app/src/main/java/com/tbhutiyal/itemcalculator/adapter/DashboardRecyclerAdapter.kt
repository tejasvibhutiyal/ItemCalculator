package com.tbhutiyal.itemcalculator.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tbhutiyal.itemcalculator.R
import com.tbhutiyal.itemcalculator.database.ListEntity
import com.tbhutiyal.itemcalculator.model.listItems


class DashboardRecyclerAdapter (val context: Context, private val itemList:ArrayList<listItems>):
    RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {
    //array for spinners
    val listQuantity= arrayOf("0","1","2","3","4","5")
    val listQuality= arrayOf("per 100 gram","per kg","per litre")
    //price variable will calculate the price of items as per the unit selected
    var price:Int=0
    //total that will count the total price
    var t:Int=0
    //shared preference
    var sp:SharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefrence), MODE_PRIVATE)
    class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view){
        //initializing the variables for each recycler view
        val txtItemName: TextView =view.findViewById(R.id.txtItemname)
        val txtItemPrice: TextView =view.findViewById(R.id.txtItemPrice)
        //val llayout: LinearLayout =view.findViewById(R.id.llayout)
        val spnQuantity: Spinner = view.findViewById(R.id.spnQuantity)
        val spnQuality: Spinner =view.findViewById(R.id.spnQuality)
        val imgItem: ImageView = view.findViewById(R.id.imgItems)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val items=itemList[position]
        //putting the value of total=0
        sp.edit().putInt("total",0).commit()
        var quantity:String
        var quality: String
        //adding the data of each row
        holder.txtItemPrice.text=items.Price.toString()
        holder.txtItemName.text=items.name
        holder.spnQuality.adapter=
            ArrayAdapter<String>(context,R.layout.spinner_quality_list,listQuality)
        //adding spinner listener for units
        holder.spnQuality.onItemSelectedListener= object :
        AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
                holder.txtItemPrice.text="Rs ${items.Price.toString()}"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //changing the price of items as per the quantity selected
                 price=items.Price
                if(listQuality[position]=="per kg")
                {
                    price=price*10

                }
                else if(listQuality[position]=="per litre")
                {
                    price=price*2

                }
                holder.txtItemPrice.text="Rs ${price.toString()}"
            }
        }
        holder.spnQuantity.adapter=
            ArrayAdapter<String>(context,R.layout.spinner_quantity_list,listQuantity)
        holder.spnQuantity.onItemSelectedListener= object :
        AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                t=0
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //adding the total
                var quant=position
                t=price*quant
                holder.txtItemPrice.text="Rs ${t.toString()}"
                t=t+sp.getInt("total",0)
                println("$t")
                sp.edit().putInt("total",t).apply()


            }
        }

        holder.imgItem.setImageResource(R.drawable.grocery_item_image)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}