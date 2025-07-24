package ir.devalix.konkura.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import ir.devalix.konkura.PdfActivity
import ir.devalix.konkura.R
import ir.devalix.konkura.databinding.ItemCardviewFragmentsBinding
import java.io.File

class KonkurListTajrobiAdapter(private val data: ArrayList<KonkurListTajrobi>) :
    RecyclerView.Adapter<KonkurListTajrobiAdapter.KonkurViewHolder>() {

    inner class KonkurViewHolder(val binding: ItemCardviewFragmentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: KonkurListTajrobi, position: Int) {
            binding.txtYearMain.text = item.year

            binding.toggleIcon1.rotation = if (item.isExpanded) 180f else 0f


            val container = (binding.expandableMain.getChildAt(0) as? LinearLayout)

            container?.removeAllViews()

            item.subButtons.forEach { sub ->
                Log.v("testV" , sub.toString() )
                val btn = MaterialButton(binding.root.context).apply {
                    text = sub.text
                    setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    background = ContextCompat.getDrawable(context, R.drawable.button_gradient)
                    ViewCompat.setBackgroundTintList(this, null)
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(16, 8, 16, 8)
                    }

                    setOnClickListener {
                        Toast.makeText(context, "Clicked: ${sub.id}", Toast.LENGTH_SHORT).show()
                        val uniqueID = sub.id
                        val intent = Intent( context , PdfActivity::class.java )
                        intent.putExtra( "UNIQUE_ID" , uniqueID )
                        context.startActivity(intent)
                        saveSelectedKonkur( uniqueID )
                    }
                }
                container?.addView(btn)
            }


            if (item.isExpanded) {
                binding.expandableMain.expand()

            } else {
                binding.expandableMain.collapse()

            }


            binding.cardHeaderMain.setOnClickListener {

                val targetRotation = if (!item.isExpanded) 180f else 0f
                binding.toggleIcon1.animate().rotation(targetRotation).setDuration(370).start()

                item.isExpanded = !item.isExpanded
                binding.expandableMain.toggle()

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

    private fun saveSelectedKonkur(uniqueID :String){
        val fileName = "$uniqueID.pdf"

    }

}

