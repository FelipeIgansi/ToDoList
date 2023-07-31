package com.todolist

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val list = mutableListOf(
            ToDoModel("Tarefa 01"),
            ToDoModel("Tarefa 02"),
            ToDoModel("Tarefa 03")
        )

        val adapter = Adapter(list)
        recyclerView.adapter = adapter

        binding.buttonAdd.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button_add -> {
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.dialog_cadastro)

                val editTextTitle = dialog.findViewById<EditText>(R.id.editTextTask)
                val buttonAddTodo = dialog.findViewById<Button>(R.id.button_register)
                val buttonCancelarTodo = dialog.findViewById<Button>(R.id.button_cancel)

                buttonAddTodo.setOnClickListener {
                    val title = editTextTitle.text.toString().trim()
                    if (title.isNotEmpty()) {
                        // Adicione o novo item à lista
                        val newItem = ToDoModel(title)
                        (binding.recyclerView.adapter as? Adapter)?.addItem(newItem)

                        // Feche a Dialog após cadastrar
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this, "Insira um título válido.", Toast.LENGTH_SHORT).show()
                    }
                }
                buttonCancelarTodo.setOnClickListener{
                    dialog.dismiss()
                }

                dialog.show()
            }
        }
    }
}