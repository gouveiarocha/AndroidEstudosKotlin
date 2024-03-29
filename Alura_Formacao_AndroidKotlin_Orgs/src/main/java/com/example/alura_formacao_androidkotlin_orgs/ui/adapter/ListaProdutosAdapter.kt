package com.example.alura_formacao_androidkotlin_orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alura_formacao_androidkotlin_orgs.R
import com.example.alura_formacao_androidkotlin_orgs.databinding.ProdutoItemBinding
import com.example.alura_formacao_androidkotlin_orgs.utils.extensions.tentaCarregarImagem
import com.example.alura_formacao_androidkotlin_orgs.model.Produto
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    //obs: a classe abaixo foi modificada para adequar a mudança para binding...
    //para funcionar com binding, ela recebe o binding do tipo a classe binding e faz o root na
    //chamada do construtor
    //Antes, recebia e inicializava uma View.

    //Texto com a explicação copiado do artigo:
    //Observe que a grande diferença é que o ViewHolder recebe o View Binding do layout desejado,
    // nesse caso, o ItemNotaBinding. Então, é enviada a view com o binding.root para o construtor
    // do RecyclerView.ViewHolder() e o parâmetro binding pode ser utilizado para buscar as views
    // desejadas.

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(produto: Produto) {

            val nome = itemView.findViewById<TextView>(R.id.produto_item_nome)
            nome.text = produto.nome

            val descricao = itemView.findViewById<TextView>(R.id.produto_item_descricao)
            descricao.text = produto.descricao

            //com binding
            val valor = binding.produtoItemValor
            val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            valor.text = formatador.format(produto.valor)

            //exemplo visibilidade da view, aqui, vamos esconder a imagem caso seja null
            val visibilidade = if (produto.imagem != null) {
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.imageView.visibility = visibilidade

            //exemplo usando o coil para setar a imagem, caso seja null, configura outra img automaticamente
            binding.imageView.tentaCarregarImagem(produto.imagem)

            //trecho refatorado para extension
//            binding.imageView.load(produto.imagem) {
//                //img durante carregamento
//                //placeholder(R.drawable.placeholder)
//                //fallback vai configurar uma imagem caso a imagem seja null.
//                fallback(R.drawable.erro)
//                //error vai configurar uma imagem caso ocorra algum erro ao carrega-la.
//                error(R.drawable.erro)
//            }

        }

    }


    override fun onCreateViewHolder(

        //implementação com binding
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ProdutoItemBinding
            .inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        return ViewHolder(binding)

        //implementação sem binding
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolder {
//        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(
//            R.layout.produto_item,
//            parent,
//            false
//        )
//        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
