package com.noobdev.ciap;

/**
 * Created by GiovanniGomes on 02/03/2015.
 */
public class Categoria {
    public String Grupo;
    public String Categoria;

    public String Titulo;
    public String Descricao;
    public Categoria(){
        super();
    }

    public Categoria(String ID, String Titulo, String Descricao, String Grupo, String Categoria) {
        super();
        this.Titulo = Titulo;
        this.Descricao = Descricao;
        this.Categoria = Categoria;
        this.Grupo = Grupo;
    }
}

