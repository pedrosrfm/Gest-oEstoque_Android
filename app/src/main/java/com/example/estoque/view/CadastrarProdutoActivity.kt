package com.example.estoque.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.estoque.R
import com.example.estoque.database.DatabaseController
import com.example.estoque.model.Produto
import com.example.estoque.model.TipoProduto

class CadastrarProdutoActivity : AppCompatActivity() {

    val dbController = DatabaseController()

    private lateinit var edtNome: EditText
    private lateinit var edtQuantidade: EditText
    private lateinit var radioGroupTipoProduto: RadioGroup
    private lateinit var btnCadastrar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastrar_produto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtNome = findViewById(R.id.edtTxtNome)
        edtQuantidade = findViewById(R.id.edtTxtQuantidade)
        radioGroupTipoProduto = findViewById(R.id.radioGrpTipoProduto)
        btnCadastrar = findViewById(R.id.btnCadastrar)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnCadastrar.isEnabled = false
        btnCadastrar.setBackgroundColor(ContextCompat.getColor(this, R.color.disabled))

        radioGroupTipoProduto.setOnCheckedChangeListener {_, checkedId ->
            if (checkedId != 1) {
                btnCadastrar.isEnabled = true
                btnCadastrar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary))
            } else {
                btnCadastrar.isEnabled = false
                btnCadastrar.setBackgroundColor(ContextCompat.getColor(this, R.color.disabled))
            }
        }

        btnCadastrar.setOnClickListener {
            val nome = edtNome.text.toString()
            val quantidade = edtQuantidade.text.toString().toIntOrNull() ?: 0
            val tipoId = radioGroupTipoProduto.checkedRadioButtonId
            val tipoProduto: TipoProduto = when(tipoId) {
                R.id.radioBtnAlimento -> TipoProduto.ALIMENTO
                R.id.radioBtnBebida -> TipoProduto.BEBIDA
                R.id.radioBtnLimpeza -> TipoProduto.PRODUTO_DE_LIMPEZA
                R.id.radioBtnOutro -> TipoProduto.OUTRO
                else -> return@setOnClickListener
            }

            Toast.makeText(this, "Produto cadastrado - $nome", Toast.LENGTH_SHORT).show()
            val produto = Produto(nome = nome, quantidade = quantidade, tipoProduto = tipoProduto)
            dbController.criarProduto(produto)
        }

        btnCancelar.setOnClickListener {
            finish()
        }
   }

    fun onCreateProduct(produto: Produto){
        dbController.criarProduto(produto)
    }

}