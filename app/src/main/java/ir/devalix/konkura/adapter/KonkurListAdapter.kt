package ir.devalix.konkura.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.devalix.konkura.R
import net.cachapa.expandablelayout.ExpandableLayout

class KonkurListAdapter( private val data :ArrayList<KonkurList> ) : RecyclerView.Adapter<KonkurListAdapter.KonkurViewHolder>() {


    inner class KonkurViewHolder( itemView :View ) :RecyclerView.ViewHolder( itemView ){
        var txtTest = itemView.findViewById<TextView>( R.id.txtYearMain )
        fun bindData(position: Int) {
            txtTest.text = data[position].year
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonkurViewHolder {
        val view = LayoutInflater.from( parent.context ).inflate( R.layout.item_cardview_fragments , parent , false)
        return KonkurViewHolder( view )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: KonkurViewHolder, position: Int) {
        holder.bindData(position)

        val expandable = holder.itemView.findViewById<ExpandableLayout>(R.id.expandableMain)
        val toggleIcon = holder.itemView.findViewById<TextView>(R.id.toggleIcon)

        toggleIcon.setOnClickListener {
            expandable.toggle()
            toggleIcon.text = if (expandable.isExpanded) "-" else "+"
        }

        // جلوگیری از بهم ریختن وضعیت expand در بازیابی ویو‌ها:
        expandable.setExpanded(expandable.isExpanded, false)
    }



}