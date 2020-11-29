package com.fernando.spacex.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fernando.spacex.R
import com.fernando.spacex.databinding.ItemRocketBinding
import com.fernando.spacex.extension.TAG
import com.fernando.spacex.helpers.CellClickListener
import com.fernando.spacex.model.RocketModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class RocketAdapter @Inject constructor() : RecyclerView.Adapter<RocketAdapter.MyViewHolder>(), Filterable {

    private val rocketListBackup: MutableList<RocketModel> = ArrayList()
    private var rocketListFilter: MutableList<RocketModel> = ArrayList()
    private lateinit var cellClickListener: CellClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRocketBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rocketListFilter.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(rocketListFilter[position])
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_fade_left)
    }

    fun setListener(cellClickListener: CellClickListener) {
        this.cellClickListener = cellClickListener
    }

    fun setRockets(newItems: List<RocketModel>) {
        val result = DiffUtil.calculateDiff(RocketListDiffUtilCallback(this.rocketListFilter, newItems))
        result.dispatchUpdatesTo(this)

        rocketListBackup.clear()
        rocketListFilter.clear()

        rocketListBackup.addAll(newItems)
        rocketListFilter.addAll(newItems)
    }

    inner class MyViewHolder(private val binding: ItemRocketBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rocket: RocketModel) {
            try {
                binding.rocket = rocket
                binding.executePendingBindings()

                itemView.setOnClickListener {
                    cellClickListener.onCellClickListener(rocket)
                }

            } catch (e: Exception) {
                Log.e(TAG, "MyViewHolder:bind ${e.message}")
            }
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val resultList: MutableList<RocketModel> = ArrayList()

                val charSearch = constraint.toString()

                for (row in rocketListBackup)
                    if (row.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)))
                        resultList.add(row)

                rocketListFilter = resultList

                val filterResults = FilterResults()
                filterResults.values = rocketListFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                rocketListFilter = (results?.values as MutableList<RocketModel>)
                notifyDataSetChanged()
            }
        }
    }
}

class RocketListDiffUtilCallback(private var oldItems: List<RocketModel>, private var newItems: List<RocketModel>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]

}


