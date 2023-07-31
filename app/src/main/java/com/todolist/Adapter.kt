package com.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val itens: MutableList<ToDoModel>):RecyclerView.Adapter<Adapter.ViewHolder>() {
    fun addItem(item: ToDoModel) {
        itens.add(item)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         val checkBoxTarefa: CheckBox = itemView.findViewById(R.id.chack_todo_item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itens[position]
        holder.checkBoxTarefa.text = currentItem.titulo
    }

    override fun getItemCount() = itens.size
}