package ir.devalix.konkura.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.devalix.konkura.R
import ir.devalix.konkura.databinding.ItemCardviewFragmentsBinding
import net.cachapa.expandablelayout.ExpandableLayout

class KonkurListAdapter(private val data: ArrayList<KonkurList>) : RecyclerView.Adapter<KonkurListAdapter.KonkurViewHolder>() {

    inner class KonkurViewHolder(val binding: ItemCardviewFragmentsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val item = data[position]

            binding.txtYearMain.text = item.year
            binding.toggleIcon.text = if (item.isExpanded) "-" else "+"

            binding.cardHeaderMain.setOnClickListener {
                item.isExpanded = !item.isExpanded
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonkurViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardviewFragmentsBinding.inflate(inflater, parent, false)
        return KonkurViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: KonkurViewHolder, position: Int) {
        holder.bindData(position)
    }
}
