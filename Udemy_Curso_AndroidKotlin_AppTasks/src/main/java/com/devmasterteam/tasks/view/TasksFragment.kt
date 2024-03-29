package com.devmasterteam.tasks.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmasterteam.tasks.databinding.FragmentTasksBinding
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.TaskListener
import com.devmasterteam.tasks.view.adapter.TaskAdapter
import com.devmasterteam.tasks.viewmodel.TaskListViewModel

class TasksFragment : Fragment() {

    private lateinit var viewModel: TaskListViewModel
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    private val adapter = TaskAdapter()
    private var taskfilter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)
        _binding = FragmentTasksBinding.inflate(inflater, container, false)

        binding.recyclerAllTasks.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllTasks.adapter = adapter

        // Atribuir nosso taskfilter. Utilizado para tratar a navegação entre os filtros de tarefas.
        taskfilter = requireArguments().getInt(TaskConstants.BUNDLE.TASKFILTER, 0)

        // Método para instanciar o listener e passar para o Adapter.
        initListener()

        // Init Observer(s).
        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.list(taskfilter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.updateTasks(it)
        }

        viewModel.delete.observe(viewLifecycleOwner) {
            when (it.status()) {
                true -> Toast.makeText(context, "Tarefa deletada com sucesso!!!", Toast.LENGTH_LONG)
                    .show()
                false -> Toast.makeText(context, it.message(), Toast.LENGTH_LONG).show()
            }
        }

        viewModel.status.observe(viewLifecycleOwner) {
            when (it.status()) {
                true -> Toast.makeText(context, "Tarefa Atualizada!!!", Toast.LENGTH_SHORT).show()
                false -> Toast.makeText(context, it.message(), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun initListener() {
        val listener = object : TaskListener {
            override fun onListClick(id: Int) {
                val intent = Intent(context, TaskFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(TaskConstants.BUNDLE.TASKID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDeleteClick(id: Int) {
                viewModel.delete(id)
            }

            override fun onCompleteClick(id: Int) {
                viewModel.status(id, true)
            }

            override fun onUndoClick(id: Int) {
                viewModel.status(id, false)
            }
        }
        adapter.attachListener(listener)
    }

}