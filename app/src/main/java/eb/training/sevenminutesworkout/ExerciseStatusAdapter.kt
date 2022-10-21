package eb.training.sevenminutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import eb.training.sevenminutesworkout.databinding.ItemExecriseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>):RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemExecriseStatusBinding ):RecyclerView.ViewHolder(binding.root) {
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExecriseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()
        when{
            model.getIsSelected()->{
                holder.tvItem.background = ContextCompat.getDrawable(holder.tvItem.context,R.drawable.item_background_white)
                holder.tvItem.setTextColor(Color.parseColor("#000000"))
            }
            model.getIsComplete()->{
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_background_blue)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else ->{
                holder.tvItem.background = ContextCompat
                    .getDrawable(holder.itemView.context,R.drawable.item_background_gray)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }


        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}