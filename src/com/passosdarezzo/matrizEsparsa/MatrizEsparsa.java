package com.passosdarezzo.matrizEsparsa;

import javax.swing.JOptionPane;


/**
 *
 * @author passosdarezzo@hotmail.com
 */
public class MatrizEsparsa {
    private No [] vetLinhaNo;
    private No [] vetColunaNo;

    /**
     * Adiciona uma informação na matriz em uma posição determinada.
     * A coluna e a linha são indexadas a partir de 0.
     * @param coluna Índice da coluna, deve ser >= 0.
     * @param linha  Índice da linha, deve ser >= 0.
     * @param Info   Valor que será armazenado.
     * @throws IllegalArgumentException
     */
    public boolean addInfo(int coluna, int linha, int info) throws IllegalArgumentException{
        //Se o argumento não é valido, lança uma excessão
        if(linha < 0 || coluna < 0) throw new IllegalArgumentException();

        //*****************************************************************************
        // Verifica se o vetor linha é grande o suficiente para a nova coordenada
        //*****************************************************************************
        if(vetLinhaNo == null){               //Se o vetor ainda não foi criado
            vetLinhaNo = new No[linha+1];     //Cria com tamanho suficiente
        }else{                                //Se já existe
            if(vetLinhaNo.length-1 < linha ){ //É grande o suficiente para a nova informação?
                expandeTamVetorLinha(linha+1);
            }
        }

        //****************************************************************************
        // Verifica se o vetor coluna é grande o suficiente para a nova coordenada
        //****************************************************************************
        if(vetColunaNo == null){
            vetColunaNo = new No[coluna+1];
        }else{                         
            if(vetColunaNo.length-1 < coluna ){
                expandeTamVetorColuna(coluna+1);
            }
        }

        //----------------------------------------------------------------------
        //Verifica se existe lista de nós na posição informada
        if(vetLinhaNo[linha] != null && vetColunaNo[coluna] != null){//Existe nó na posição informada?
            //------------------------------------------------------------------
            for(int i = 0; i <= vetColunaNo.length-1; i++){
                No temp = vetColunaNo[i];
                if(temp != null){
                    do{
                        if(temp.getColuna() == coluna && temp.getLinha() == linha){
                            int resp = JOptionPane.showConfirmDialog(null, "Deseja sobreescrever a informação?",
                                    "Alerta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(resp == JOptionPane.CANCEL_OPTION)
                                return false;
                            else{
                                temp.setInfo(info);//Sobreescreve a informação
                                return true;
                            }
                        }
                    }
                    while( (temp = temp.getNoAbaixo()) != null);
                }
            }
        }
        //---------------------------------------------------------------------------------------------------*/
        //cria o novo nó que será adicionado
        No aux = new No(info, coluna, linha);

        //***************************************************************************
        // Adiciona o Nó na coluna determinada
        //***************************************************************************
        if(vetColunaNo[coluna] == null)  //Se ainda não armazena nada
           vetColunaNo[coluna] = aux;    //adiciona a ref para o nó
        else{                            //Se já existe
            if(vetColunaNo[coluna].getLinha() > linha){//se o vetor aponta para um nó posterior ao novo nó
                No refTemp = vetColunaNo[coluna];//Guarda a referência do nó no vetor
                vetColunaNo[coluna] = aux;//O nó do vetor aponta para o novo nó
                vetColunaNo[coluna].setNoAbaixo(refTemp, false);//e o novo nó para o nó posterior
            }else{//Se o vetor aponta para um nó anterior ao novo nó
                vetColunaNo[coluna].setNoAbaixo(aux, false);//O nó do vetor aponta para o novo nó
            }
        }

        //***************************************************************************
        // Adiciona o Nó na linha determinada
        //***************************************************************************
        if(vetLinhaNo[linha] == null)
            vetLinhaNo[linha] = aux;
        else{
            if(vetLinhaNo[linha].getColuna() > coluna){
                No refTemp = vetLinhaNo[linha];
                vetLinhaNo[linha] = aux;
                vetLinhaNo[linha].setNoDireito(refTemp, false);
            }else{
                vetLinhaNo[linha].setNoDireito(aux, false);
            }
        }
        return true;
    }

    /**
     * Remove uma informação da matriz
     * @param coluna
     * @param linha
     * @param info
     * @return
     */
    public boolean removeInfo(int coluna, int linha) throws IllegalArgumentException{
        //Se o argumento é menor do que zero
        //Se a posição está fora dos limites do array lança uma exceção!
        if(linha < 0 || coluna < 0 ||vetLinhaNo.length-1 < linha || vetColunaNo.length-1 < coluna){
            JOptionPane.showMessageDialog(null, "Não existe a posição informada.",
                                               "Informação", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        //Não existe o Nó na posição informada
        if(vetLinhaNo[linha] == null || vetColunaNo[coluna] == null){
            JOptionPane.showMessageDialog(null, "Não existe informação na posição informada",
                                               "Informação", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        //***********************************************************************************
        //Situação 1,2 e 3
        //***********************************************************************************
        if(vetColunaNo[coluna].getLinha() == linha && vetLinhaNo[linha].getColuna() == coluna){
            if(vetColunaNo[coluna].getNoAbaixo() != null)
                vetColunaNo[coluna] = vetColunaNo[coluna].getNoAbaixo();
            else
                vetColunaNo[coluna] = null;

            if(vetLinhaNo[linha].getNoDireito() != null)
                vetLinhaNo[linha] = vetLinhaNo[linha].getNoDireito();
            else
                vetLinhaNo[linha] = null;
            
            retrairTamVetorColuna();
            retrairTamVetorLinha();
            return true;
        }
        //***********************************************************************************
        // Situação 4,5 e 6 para as colunas
        //***********************************************************************************
        No auxColuna = vetColunaNo[coluna];
        No temp = auxColuna;
        while( auxColuna.getLinha() != linha ){
            temp = auxColuna;//sempre guarda o nó acima
            auxColuna = auxColuna.getNoAbaixo();//sempre guarda o nó abaixo
        }
        if(auxColuna.getNoAbaixo() == null){
            if(vetColunaNo[coluna] != temp){
                temp.setNoAbaixo(null, true);
            }else {
                if(auxColuna == temp){
                    vetColunaNo[coluna] = null;
                }else{
                    vetColunaNo[coluna].setNoAbaixo(null, true);
                }
            }
        }else{
            temp.setNoAbaixo(auxColuna.getNoAbaixo(), true);
        }
        auxColuna = null;//sem referência o objeto está coletavel e não pode mais
                         //ser referenciado, ou seja, foi excluido da lista encadeada!
        //***********************************************************************************
        // Situação 4,5 e 6 para as linhas
        //***********************************************************************************
        No auxLinha = vetLinhaNo[linha];
        temp = auxLinha;
        while( auxLinha.getColuna() != coluna){
            temp = auxLinha;//Sempre guarda o nó mais a esquerda
            auxLinha = auxLinha.getNoDireito();//Sempre guarda o nó seguinte
        }
        if(auxLinha.getNoDireito() == null){
            if(vetLinhaNo[linha] != temp){
                temp.setNoDireito(null, true);
            }else{
                if(auxLinha == temp){
                    vetLinhaNo[linha] = null;
                }else{
                    vetLinhaNo[linha].setNoDireito(null, true);
                }
            }
        }else{
            temp.setNoDireito(auxLinha.getNoDireito(), true);
        }
        auxLinha = null;
        retrairTamVetorColuna();
        retrairTamVetorLinha();

        return true;
    }

    /**
     * Altera o tamanho do vetor linha para o argumento passado
     * @param novoTamanhoLinha
     */
    private void expandeTamVetorLinha(int novoTamanhoLinha){
        No [] aux = new No[novoTamanhoLinha];
        for(int i = 0; i < vetLinhaNo.length; i++)
            aux[i] = vetLinhaNo[i];
        
        vetLinhaNo = aux;
    }

    /**
     * Altera o tamanho do vetor coluna para o argumento passado
     * @param novoTamanhoColuna
     */
    private void expandeTamVetorColuna(int novoTamanhoColuna){
        No [] aux = new No[novoTamanhoColuna];
        for(int i = 0; i < vetColunaNo.length; i++)
            aux[i] = vetColunaNo[i];

        vetColunaNo = aux;
    }

    /**
     * retrae o vetor Coluna
     */
    private void retrairTamVetorColuna(){
        //int maiorIndiceColuna = vetColunaNo.length-1;

        /**
        if(maiorIndiceColuna == 1 && vetColunaNo[maiorIndiceColuna] == null){
            while(vetColunaNo[maiorIndiceColuna] == null)
                maiorIndiceColuna--;


        No [] temp = new No[maiorIndiceColuna+1];
        for(int i = 0; i < temp.length; i++)
            temp[i] = vetColunaNo[i];

        vetColunaNo = temp;
        }
        //--------------------------------------------------------------------*/
        int maiorIndiceColuna = vetColunaNo.length-1;

        while(vetColunaNo[maiorIndiceColuna] == null){
            if(maiorIndiceColuna == 0){
                vetColunaNo = null;
                break;
            }else
                maiorIndiceColuna--;
        }
            
        if(maiorIndiceColuna != 0){
            No [] temp = new No[maiorIndiceColuna+1];
            for(int i = 0; i < temp.length; i++)
                temp[i] = vetColunaNo[i];

            vetColunaNo = temp;
        }
    }

    /**
     * retrae o vetor Linha
     */
    private void retrairTamVetorLinha(){
        int maiorIndiceLinha = vetLinhaNo.length-1;

        while(vetLinhaNo[maiorIndiceLinha] == null){
            if(maiorIndiceLinha == 0){
                vetLinhaNo = null;
                break;
            }else
                maiorIndiceLinha--;
        }

        if(maiorIndiceLinha != 0){
            No [] temp = new No[maiorIndiceLinha+1];
            for(int i = 0; i < temp.length; i++)
            temp[i] = vetLinhaNo[i];

            vetLinhaNo = temp;
        }
    }

    /**
     * Imprime toda a matriz
     */
    public void imprimeMatriz(){
        String texto = "";
        for(int i = 0; i < vetColunaNo.length-1; i++){
            No temp = vetColunaNo[i];
            do{
                if(temp != null){
                    texto += "\nLinha: " + temp.getLinha() + " Coluna: " + temp.getColuna() + " Info: " + temp.getInfo();
                }else
                    break;
            }
            while( (temp = temp.getNoAbaixo()) != null);
        }
        JOptionPane.showMessageDialog(null, texto, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Remove todos os nós da matriz
     */
    public void limparMatriz(){
        vetLinhaNo = null;
        vetColunaNo = null;
    }

    /**
     *
     * @return vetor de Nó da linha
     */
    public No[] getVetorLinha(){
        return vetLinhaNo;
    }

    /**
     *
     * @return vetor de Nó da coluna
     */
    public No[] getVetorColuna(){
        return vetColunaNo;
    }

    public static void main(String[] args) {
        MatrizEsparsa matriz = new MatrizEsparsa();
        matriz.addInfo(2, 2, 1);
        matriz.addInfo(2, 0, 2);
        matriz.addInfo(0, 2, 3);
        matriz.removeInfo(2, 2);
        
        //matriz.addInfo(2, 2, 2);
        //matriz.addInfo(2, 1, 2);
        //matriz.addInfo(2, 2, 1);
        //matriz.removeInfo(2, 2);
        
        //matriz.addInfo(3, 3, 1);
        //matriz.addInfo(3, 2, 3);
        //matriz.addInfo(2, 3, 3);

        matriz.imprimeMatriz();

        //Situação 1
        //matriz.addInfo(2, 1, 1);
        //matriz.addInfo(2, 3, 2);

        //Situação 2
        //matriz.addInfo(1, 2, 1);
        //matriz.addInfo(3, 2, 2);

        //Situação 3
    }
}