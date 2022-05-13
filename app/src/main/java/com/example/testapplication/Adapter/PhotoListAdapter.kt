package com.example.testapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.databinding.ItemPhotoViewBinding
import com.example.testapplication.domain.PhotoListResponse

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.PhotoItemViewHolder>() {

    var photoList : List<PhotoListResponse.Hit?> ? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemPhotoViewBinding.inflate(inflater, parent, false)
        return PhotoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bind(photoList?.getOrNull(position)?.userImageURL)
    }

    override fun getItemCount(): Int {
        return photoList?.size?:0
    }
    fun setData(photoList : List<PhotoListResponse.Hit?>?){
        this.photoList = photoList
        notifyDataSetChanged()
    }

    inner class PhotoItemViewHolder(val binding : ItemPhotoViewBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(photoUrl : String?) {
              Glide.with(binding.root.context).load(photoUrl).into(binding.photoItem)
        }
    }
}