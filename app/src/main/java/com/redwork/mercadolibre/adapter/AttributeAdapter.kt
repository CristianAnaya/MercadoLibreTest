package com.redwork.mercadolibre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redwork.mercadolibre.databinding.AttributeProductItemBinding
import com.redwork.mercadolibre.framework.requestmanager.AttributeServer

class AttributeAdapter(
    private val attributes: List<AttributeServer>):
    RecyclerView.Adapter<AttributeAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = AttributeProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        val attribute: AttributeServer = attributes[position]
        itemViewHolder.bind(attribute)
    }

    override fun getItemCount(): Int {
        return attributes.size
    }

    class ItemViewHolder(private val binding: AttributeProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(attribute: AttributeServer?) {
            binding.attribute = attribute
            binding.executePendingBindings()
        }
    }

}