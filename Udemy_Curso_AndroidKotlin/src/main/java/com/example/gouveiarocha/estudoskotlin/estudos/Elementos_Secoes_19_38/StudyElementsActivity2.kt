package com.example.gouveiarocha.estudoskotlin.estudos.Elementos_Secoes_19_38

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gouveiarocha.estudoskotlin.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_study_elements2.*
import java.text.SimpleDateFormat
import java.util.*

class StudyElementsActivity2 : AppCompatActivity(), View.OnClickListener,
    AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener,
    CompoundButton.OnCheckedChangeListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, TimePicker.OnTimeChangedListener {

    private val mDate = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_elements2)

        initElements()
        loadDynamicSpinner()

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_toast_test -> {
                showToast("Teste Toast")
            }
            R.id.btn_toast_test_cust -> {
                val toast = Toast.makeText(this, "Teste!!!", Toast.LENGTH_LONG)

                // Pegando o elemento da Toast para modifica-lo.
                //val textView = toast.view?.findViewById<TextView>(android.R.id.message)
                //textView?.setTextColor(Color.RED)

                // Muda a posição da Toast
                toast.setGravity(Gravity.CENTER, 0, 0)

                // Definindo um layout customizado para a Toast
                val layout = layoutInflater.inflate(R.layout.toast_layout, null)
                toast.view = layout

                toast.show()
            }
            R.id.btn_snack_test -> {
                snack("Teste Snack!!!")
            }
            R.id.btn_spinner_get_test -> {
                val selectedItem = spinner_static_test.selectedItem
                val selectedItemId = spinner_static_test.selectedItemId
                val selectedItemPosition = spinner_static_test.selectedItemPosition
                showToast("[Botões Testes] = Position: $selectedItemPosition - ID: $selectedItemId - Texto: $selectedItem")
            }
            R.id.btn_spinner_set_test -> {
                // Só aceita o set pelo ID
                spinner_static_test.setSelection(3)
            }
            R.id.btn_seekbar_get_test -> {
                showToast(" Seekbar = ${seekbar_test.progress}")

                // Seta o Max
//                seekbar_test.max = 100

                // Seta o Min
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    seekbar_test.min = 10
//                }

            }
            R.id.btn_seekbar_set_test -> {
                seekbar_test.progress = 35
            }
            R.id.btn_datepicker_test -> {
                val calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)
                DatePickerDialog(this, this, year, month, day).show()
            }
            R.id.btn_timerpicker_test -> {
                TimePickerDialog(this, this, 1, 1, true).show()
            }
            R.id.btn_time_get_test -> {
                var hour = 0
                var minute = 0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timepicker_test.hour
                    minute = timepicker_test.minute
                } else {
                    hour = timepicker_test.currentHour
                    minute = timepicker_test.currentMinute
                }
                showToast("$hour:$minute")
            }
            R.id.btn_time_set_test -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    timepicker_test.hour = 5
                    timepicker_test.minute = 59
                } else {
                    timepicker_test.currentHour = 5
                    timepicker_test.currentMinute = 59
                }
            }
            R.id.btn_stop_progress_test -> {
                progress_default_test.visibility = View.GONE
                progress_linear_test.visibility = View.GONE
            }
        }
    }

    // Lembrete: O método onItemSelected, serve p tratar clique nos elementos diferentes, porém do
    // mesmo tipo na nossa View - como é o exemplo do nosso spinner.
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinner_static_test -> {
                val selectedItem = parent.selectedItem
                val selectedItemId = parent.selectedItemId
                val selectedItemPosition = parent.selectedItemPosition
                showToast("[OnItemSelect] = Position: $selectedItemPosition - ID: $selectedItemId - Texto: $selectedItem")
            }
            R.id.spinner_dynamic_test -> {
                //Ações de seleção no Spinner Dinamico.
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast("nothing")
    }

    /**
     * ---> Métodos Seekbar
     */

    override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
        text_seekbar_value_test.text = "Valor Seekbar = $progress"
    }

    override fun onStartTrackingTouch(seekbar: SeekBar?) {
        showToast("Track started")
    }

    override fun onStopTrackingTouch(seekbar: SeekBar?) {
        showToast("Track stoped")
    }

    /**
     * ---> Método Switch e CheckBox
     */

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        // O onCheckedChange pode observar mais elementos que retornam true ou false, daí a
        // importancia de verificar com o When.
        when (buttonView?.id) {
            R.id.switch_test -> {
                showToast("Switch ${if (isChecked) "True" else "False"}")
                // Exemplos alterando o estado\propriedades do nosso Switch programaticamente.
//                buttonView.isChecked = true
//                buttonView.isEnabled = true
//                buttonView.isClickable = true
            }
            R.id.checkbox_test -> {
                showToast("CheckBox ${if (isChecked) "True" else "False"}")
                buttonView.text = if (isChecked) "On" else "Off"
                // Exemplos alterando o estado\propriedades do nosso Checkbox programaticamente.
//                buttonView.isChecked = true
//                buttonView.isEnabled = true
//                buttonView.isClickable = true
            }
            R.id.radio_test_on -> {
                showToast("Radio On ${if (isChecked) "True" else "False"}")
                // Também podemos alterar o estado, assim como o Switch e CheckBox.
            }
            R.id.radio_test_off -> {
                showToast("Radio Off ${if (isChecked) "True" else "False"}")
                // Também podemos alterar o estado, assim como o Switch e CheckBox.
            }
        }
    }

    /**
     * ---> Métodos DatePicker e TimePicker
     */

    // Esse usamos para o DatePicker com Dialog.
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = Calendar.getInstance()
        date.set(year, month, dayOfMonth)
        btn_datepicker_test.text = mDate.format(date.time)
    }

    // Esse usamos para o DatePicker com Dialog.
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        showToast("$hourOfDay:$minute")
    }

    // Esse usamos para o DatePicker direto no Layout.
    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
        showToast("$hourOfDay:$minute")
    }

    /**
     * ---> Métodos Proprios
     */

    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    private fun snack(str: String) {
        // Cria a Snack, observar que obrigatoriamente precisa de um Layout pai. No Caso o linear_root.
        val snack = Snackbar.make(linear_root, str, Snackbar.LENGTH_LONG)
        // Cria ação no Snack.
        snack.setAction("Desfazer", View.OnClickListener {
            showToast("Desfeito...")
        })
        // Customiza cores.
        snack.setActionTextColor(Color.BLUE)
        snack.setBackgroundTint(Color.LTGRAY)

        snack.show()
    }

    private fun initElements() {

        btn_toast_test.setOnClickListener(this)
        btn_toast_test_cust.setOnClickListener(this)

        btn_snack_test.setOnClickListener(this)

        btn_spinner_get_test.setOnClickListener(this)
        btn_spinner_set_test.setOnClickListener(this)

        spinner_static_test.onItemSelectedListener = this

        btn_seekbar_get_test.setOnClickListener(this)
        btn_seekbar_set_test.setOnClickListener(this)

        seekbar_test.setOnSeekBarChangeListener(this)

        switch_test.setOnCheckedChangeListener(this)
        checkbox_test.setOnCheckedChangeListener(this)

        radio_test_on.setOnCheckedChangeListener(this)
        radio_test_off.setOnCheckedChangeListener(this)

        btn_datepicker_test.setOnClickListener(this)
        btn_timerpicker_test.setOnClickListener(this)
        timepicker_test.setOnTimeChangedListener(this)

        btn_time_get_test.setOnClickListener(this)
        btn_time_set_test.setOnClickListener(this)

        btn_stop_progress_test.setOnClickListener(this)

    }

    private fun loadDynamicSpinner() {
        val mList = listOf(
            "Gramas Spinner Dinamico",
            "Kg Spinner Dinamico",
            "Arroba Spinner Dinamico",
            "Tonelada Spinner Dinamico"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinner_dynamic_test.adapter = adapter
    }

}