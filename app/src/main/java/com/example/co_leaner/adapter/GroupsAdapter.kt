package com.example.co_leaner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.data.local.Group
import com.example.co_leaner.util.Utils

class GroupsAdapter(val context: Context) : RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var list: List<Group> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        val itemView = layoutInflater.inflate(R.layout.group_item, parent, false)
        return GroupsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        val group = list[position]
        Utils.setImage(context, group.image, holder.groupImage)
        holder.groupName.text = group.name
        holder.groupParticipants.text = group.participants.toString()
    }

    override fun getItemCount(): Int = list.size

    class GroupsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupImage: ImageView = itemView.findViewById(R.id.imggroup)
        val groupName: TextView = itemView.findViewById(R.id.txtgroupname)
        val groupParticipants: TextView = itemView.findViewById(R.id.txtgroupparticipants)
    }

    fun submitData(list: List<Group>) {
        this.list = list
        notifyDataSetChanged()
    }
}