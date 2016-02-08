package com.passosdarezzo.matrizEsparsa;

/**
 * A classe No.java pode armazenar um valor, bem como referênciar outros
 * dois objetos No.
 * @author passosdarezzo@hotmail.com
 */

public class No {
    private No refNoDireito;
    private No refNoAbaixo;
    private int info,
                coluna,
                linha;

    /**
     * Não permite que seja criado um Nó sem Informação
     * @param info - Valor que será armazenado
     * @param coluna - Coluna onde o Nó será criado
     * @param linha - Linha onde o Nó será criado
     */
    public No(int info, int coluna, int linha){
        this.info = info;
        this.coluna = coluna;
        this.linha = linha;
    }

    /**
     * Obtém a linha do Nó
     * @return linha
     */
    public int getLinha(){
        return linha;
    }

    /**
     * Obtém a coluna do Nó
     * @return coluna
     */
    public int getColuna(){
        return coluna;
    }

    /**
     * Obtém a informação armazenada no Nó
     * @return info
     */
    public int getInfo(){
        return info;
    }

    /**
     * Altera a informação contida no Nó
     * @param novaInfo
     */
    public void setInfo(int novaInfo){
        info = novaInfo;
    }

    /**
     * retorna a referência ao Nó à direita
     * @return refDireito
     */
    public No getNoDireito(){
        return refNoDireito;
    }

    /**
     * Altera a referência ao Nó à direita
     * @param novoNoDireito
     */
    public void setNoDireito(No novoNoDireito, boolean remocao){
        if(refNoDireito == null)    //Senão aponta pra ninguém
            refNoDireito = novoNoDireito; //Passa a apontar para o novoNoDireito
        else                              //Se já aponta
            if(remocao)
                refNoDireito = novoNoDireito;
            else
                refNoDireito.setNoDireito(novoNoDireito, false);//diz para o nó apontado apontar
                                                     //para o novoNoDireito e
                                                     //assim sucessivamente
    }

    /**
     * Obtém uma referência ao Nó abajo
     * @return refNoAbaixo
     */
    public No getNoAbaixo(){
        return refNoAbaixo;
    }

    /**
     * Altera a referência ao Nó abaixo
     * @param novoNoAbaixo
     */
    public void setNoAbaixo(No novoNoAbaixo, boolean remocao){
        if(refNoAbaixo == null)
            refNoAbaixo = novoNoAbaixo;
        else
            if(remocao)
                refNoAbaixo = novoNoAbaixo;
            else
                refNoAbaixo.setNoAbaixo(novoNoAbaixo, false);
    }
}
