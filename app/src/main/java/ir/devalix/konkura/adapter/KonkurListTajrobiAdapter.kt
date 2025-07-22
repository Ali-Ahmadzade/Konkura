package ir.devalix.konkura.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import ir.devalix.konkura.R
import ir.devalix.konkura.databinding.ItemCardviewFragmentsBinding

class KonkurListTajrobiAdapter(private val data: ArrayList<KonkurListTajrobi>) :
    RecyclerView.Adapter<KonkurListTajrobiAdapter.KonkurViewHolder>() {

    inner class KonkurViewHolder(val binding: ItemCardviewFragmentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: KonkurListTajrobi, position: Int) {
            binding.txtYearMain.text = item.year
            binding.toggleIcon.text = if (item.isExpanded) "-" else "+"

            val container = (binding.expandableMain.getChildAt(0) as? LinearLayout)

            container?.removeAllViews()

            item.subButtons.forEach { sub ->
                val btn = MaterialButton(binding.root.context).apply {
                    text = sub.text
                    setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    background = ContextCompat.getDrawable(context, R.drawable.button_gradient)
                    ViewCompat.setBackgroundTintList(this, null)
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(16, 8, 16, 0)
                    }

                    setOnClickListener {
                        Toast.makeText(context, "Clicked: ${sub.id}", Toast.LENGTH_SHORT).show()
                    }
                }
                container?.addView(btn)
            }


            // باز یا بسته کردن بسته به isExpanded
            // در bindData:
            if (item.isExpanded) {
                binding.expandableMain.expand()
                binding.toggleIcon.text = "-"
            } else {
                binding.expandableMain.collapse()
                binding.toggleIcon.text = "+"
            }

// در listener:
            binding.cardHeaderMain.setOnClickListener {
                item.isExpanded = !item.isExpanded
                binding.expandableMain.toggle()
                binding.toggleIcon.text = if (item.isExpanded) "-" else "+"
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonkurViewHolder {
        val binding =
            ItemCardviewFragmentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KonkurViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KonkurViewHolder, position: Int) {
        holder.binding.cardHeaderMain.startAnimation( android.view.animation.AnimationUtils.loadAnimation( holder.itemView.context , R.anim.recycler_anim ) )
        holder.bindData(data[position], position)
    }

    override fun getItemCount(): Int = data.size
}

