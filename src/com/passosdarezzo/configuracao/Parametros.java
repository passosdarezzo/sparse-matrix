package com.passosdarezzo.configuracao;

import java.awt.Color;

/**
 * Parâmetros utilizados no programa
 * @author passosdarezzo
 */
public class Parametros {
    //cores
    private Color corRegua = Color.BLACK;
    private Color corlinhaHorizontal = Color.RED;
    private Color corlinhaVertical = Color.BLUE;
    private Color corNo = Color.GREEN;
    private Color corInfo = Color.BLACK;

    //Booleanos
    private boolean booPontilhado;

    //Constantes
    public static int LARGURA_DA_COLUNA = 50;
    public static int LARGURA_DA_LINHA = 50;
    public static int MARGEM_SUPERIOR = 12;
    public static int MARGEM_LATERAL = 12;
    public static int FATOR_COL = 5;
    public static int FATOR_LIN = -4;

    /**
     * @return the corRegua
     */
    public Color getCorRegua() {
        return corRegua;
    }

    /**
     * @param corRegua the corRegua to set
     */
    public void setCorRegua(Color corRegua) {
        this.corRegua = corRegua;
    }

    /**
     * @return the corlinhaHorizontal
     */
    public Color getCorlinhaHorizontal() {
        return corlinhaHorizontal;
    }

    /**
     * @param corlinhaHorizontal the corlinhaHorizontal to set
     */
    public void setCorlinhaHorizontal(Color corlinhaHorizontal) {
        this.corlinhaHorizontal = corlinhaHorizontal;
    }

    /**
     * @return the corlinhaVertical
     */
    public Color getCorlinhaVertical() {
        return corlinhaVertical;
    }

    /**
     * @param corlinhaVertical the corlinhaVertical to set
     */
    public void setCorlinhaVertical(Color corlinhaVertical) {
        this.corlinhaVertical = corlinhaVertical;
    }

    /**
     * @return the corNo
     */
    public Color getCorNo() {
        return corNo;
    }

    /**
     * @param corNo the corNo to set
     */
    public void setCorNo(Color corNo) {
        this.corNo = corNo;
    }

    /**
     * @return the corInfo
     */
    public Color getCorInfo() {
        return corInfo;
    }

    /**
     * @param corInfo the corInfo to set
     */
    public void setCorInfo(Color corInfo) {
        this.corInfo = corInfo;
    }

    /**
     * @return the booPontilhado
     */
    public boolean isBooPontilhado() {
        return booPontilhado;
    }

    /**
     * @param booPontilhado the booPontilhado to set
     */
    public void setBooPontilhado(boolean booPontilhado) {
        this.booPontilhado = booPontilhado;
    }
}
