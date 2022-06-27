package com.redwork.mercadolibre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redwork.mercadolibre.R
import com.redwork.mercadolibre.databinding.ImageProductItemBinding
import com.redwork.mercadolibre.framework.imagemanager.bindImageUrl
import com.redwork.mercadolibre.framework.requestmanager.PictureServer

class PicturesAdapter(private val pictures: List<PictureServer>): RecyclerView.Adapter<PicturesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = ImageProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        val picture: PictureServer = pictures[position]
        itemViewHolder.bind(picture)
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

    class ItemViewHolder(private val binding: ImageProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(picture: PictureServer?) {

            binding.imgViewMiniatura.bindImageUrl(
                url = picture?.url,
                placeholder = R.drawable.ic_camera_alt_black,
                errorPlaceholder = R.drawable.ic_404
            )
        }

    }
}