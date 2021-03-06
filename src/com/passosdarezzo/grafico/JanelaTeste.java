package com.passosdarezzo.grafico;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.passosdarezzo.matrizEsparsa.MatrizEsparsa;
import com.passosdarezzo.matrizEsparsa.No;
import com.passosdarezzo.configuracao.Parametros;
import com.passosdarezzo.configuracao.JanelaConfiguracao;
import javax.swing.ImageIcon;

public class JanelaTeste extends JPanel{
    private Dimension area;
    private JPanel desenho;
    private JTextField fieldInfo;
    private JTextField fieldColuna;
    private JTextField fieldLinha;
    private JButton bInserir;
    private JButton bRemover;
    private JButton bLimpar;
    private JLabel lColuna;
    private JLabel lLinha;
    private JLabel lInfo;
    private MatrizEsparsa matriz;
    private JButton bConf;
    private Parametros conf;

    /**
     * Construtor da classe
     */
    public JanelaTeste() {
        super(new BorderLayout());

        area = new Dimension();
        matriz = new MatrizEsparsa();
        conf = new Parametros();

        //Campos e Labels para entrada da matriz
        lColuna = new JLabel("Col");
        lLinha = new JLabel("Lin");
        lInfo = new JLabel("Info");
        fieldInfo = new JTextField(8);
        fieldInfo.setFocusable(true);
        fieldColuna = new JTextField(8);
        fieldLinha = new JTextField(8);

        //Botões
        bInserir = new JButton("Inserir");
        bInserir.addActionListener((ActionListener)
                EventHandler.create(ActionListener.class, JanelaTeste.this, "inserirNo"));

        bRemover = new JButton("Remover");
        bRemover.addActionListener((ActionListener)
                EventHandler.create(ActionListener.class, JanelaTeste.this, "removerNo"));

        bLimpar = new JButton("Limpar");
        bLimpar.addActionListener((ActionListener)
                EventHandler.create(ActionListener.class, JanelaTeste.this, "limparMatriz"));

        bConf = new JButton("Configurações");
        bConf.addActionListener((ActionListener)
                EventHandler.create(ActionListener.class, JanelaTeste.this, "abrirConf"));

        //Contém os botões e os campos
        JPanel camposPainel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        camposPainel.setFocusable(false);
        camposPainel.add(lInfo);
        camposPainel.add(fieldInfo);
        camposPainel.add(lColuna);
        camposPainel.add(fieldColuna);
        camposPainel.add(lLinha);
        camposPainel.add(fieldLinha);
        camposPainel.add(bInserir);
        camposPainel.add(bRemover);
        camposPainel.add(bLimpar);
        camposPainel.add(bConf);

        //Set up the drawing area.
        desenho = new Desenho();
        desenho.setBackground(Color.white);

        //Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(desenho);
        scroller.setPreferredSize(new Dimension(600, 400));

        //Lay out this demo.
        add(camposPainel, BorderLayout.PAGE_END);
        add(scroller, BorderLayout.CENTER);
    }

    /**
     * Insere um Nó na matriz
     */
    public void inserirNo(){
        if(!validaEntrada()){
            JOptionPane.showMessageDialog(this, "Informe o conteúdo, a linha e a "
                    + "coluna do Nó", "Alerta", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int coluna = Integer.parseInt(fieldColuna.getText().trim());
            int linha = Integer.parseInt(fieldLinha.getText().trim());
            int info = Integer.parseInt(fieldInfo.getText().trim());

            //Código que redimensiona a tela
            matriz.addInfo(coluna, linha, info);
            //desenho.setPreferredSize(area);
            desenho.revalidate();
            desenho.repaint();
            limparCampos();
            fieldInfo.requestFocusInWindow();
        }
    }

    /**
     * Remove um Nó
     */
    public void removerNo(){
        if(!validaRemocao()){
            JOptionPane.showMessageDialog(this, "Informe o conteúdo, a linha e a "
                    + "coluna do Nó", "Alerta", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int coluna = Integer.parseInt(fieldColuna.getText());
            int linha = Integer.parseInt(fieldLinha.getText());
            
            matriz.removeInfo(coluna, linha);
            limparCampos();
            desenho.repaint();
        }
    }

    /**
     * Retira todos os nós da matriz
     */
    public void limparMatriz(){
        matriz.limparMatriz();
        desenho.repaint();
    }

    /**
     * Escolhe as cores
     */
    public void abrirConf(){
        JanelaConfiguracao app = new JanelaConfiguracao(conf, new JPanel());
        app.setLocationRelativeTo(null);
        //desenho.paintComponents(desenho.getGraphics());
        desenho.repaint();
        desenho.updateUI();

    }

    /**
     * Limpa os campos de entrada de dados
     */
    public void limparCampos(){
        fieldInfo.setText("");
        fieldLinha.setText("");
        fieldColuna.setText("");
        fieldInfo.setFocusable(true);
    }

    /**
     * retorna true se a entrada é válida
     * @return
     */
    public boolean validaEntrada(){
        if(fieldInfo.getText().isEmpty() || fieldColuna.getText().isEmpty() || fieldLinha.getText().isEmpty()){
            return false;
        }else
            return true;
    }

    /**
     * Valida a remoção de um nó
     * @return
     */
    public boolean validaRemocao(){
        if(fieldColuna.getText().isEmpty() || fieldLinha.getText().isEmpty())
            return false;
        else
            return true;
    }

    private static void createAndShowGUI() {
        //Cria a janela
        JFrame frame = new JFrame("UniSant'Anna - Matriz Esparsa - PassosDarezzo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Icone que aparece na barra de titulo
        ImageIcon icone = new ImageIcon("imagens/logo.png");
        frame.setIconImage(icone.getImage());

        //Create and set up the content pane.
        JComponent newContentPane = new JanelaTeste();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Tela de abertura
        SplashScreen abertura = new SplashScreen(frame);
        abertura.setLocationRelativeTo(null);

        //Display the window.
        //frame.pack();
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //--------------------------------------------------------------------------
    // Área de desenho da tela
    //--------------------------------------------------------------------------
    class Desenho extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            //Altera a fonte
            g2d.setFont(new Font("Arial", Font.BOLD, 14));

            //Utiliza linha pontilhada?
            if(conf.isBooPontilhado()){
                // Cria um stroke pontilhado.
                Stroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 10, new float[] { 4, 4 }, 0);
                g2d.setStroke(stroke);
            }

            //Obtém a lista de nós
            No [] vetTemp = matriz.getVetorColuna();
            if(matriz.getVetorColuna() != null && matriz.getVetorLinha() != null){
                //Desenha a régua
                desenhaGrid(g2d, matriz.getVetorColuna().length, matriz.getVetorLinha().length);
                for(int i = 0; i < vetTemp.length; i++){
                    No temp = vetTemp[i];
                    do{
                        if(temp != null){
                            //Desenha as linhas
                            desenhaLinhas(g2d, temp);
                        }else
                            break;
                    }while((temp = temp.getNoAbaixo()) != null);
                }
                //------------------------------------------------------------------------------
                desenhaGrid(g2d, matriz.getVetorColuna().length, matriz.getVetorLinha().length);
                for(int i = 0; i < vetTemp.length; i++){
                    No temp = vetTemp[i];
                    do{
                        if(temp != null){
                            //Desenha os Nós
                            desenhaNos(g2d, temp);
                        }else
                            break;
                    }while((temp = temp.getNoAbaixo()) != null);
                }
                //-------------------------------------------------------------------------------
            }

            if(matriz.getVetorColuna() != null && matriz.getVetorLinha() != null){
                desenhaGrid(g2d, matriz.getVetorColuna().length, matriz.getVetorLinha().length);
            }
        }

        /**
         * Desenha as linhas verticais e horizontais da matriz
         * @param g2d
         * @param no
         */
        private void desenhaLinhas(Graphics2D g2d, No no){
            int xA = (1 + no.getColuna()) * Parametros.LARGURA_DA_COLUNA + Parametros.FATOR_COL;
            int yA = Parametros.MARGEM_SUPERIOR;
            int xC = (1 + no.getColuna()) * Parametros.LARGURA_DA_COLUNA + Parametros.FATOR_COL;
            int yC = (1 + no.getLinha()) * Parametros.LARGURA_DA_LINHA + Parametros.FATOR_LIN;
            int xB = Parametros.MARGEM_LATERAL;
            int yB = (1 + no.getLinha()) * Parametros.LARGURA_DA_LINHA + Parametros.FATOR_LIN;

            //Desenha linha vertical
            g2d.setPaint(conf.getCorlinhaVertical());
            g2d.drawLine(xA, yA, xC, yC);
            //Desenha linha horizontal
            g2d.setPaint(conf.getCorlinhaHorizontal());
            g2d.drawLine(xB, yB, xC, yC);
        }

        /**
         * Desenha a régua da matriz
         * @param g2d
         * @param qtdeColuna
         * @param qtdeLinha
         */
        private void desenhaGrid(Graphics2D g2d, int qtdeColuna, int qtdeLinha){
            //Altera a cor da régua
            g2d.setPaint(conf.getCorRegua());

            //seta o tamanho da área visível
            area.setSize(new Dimension(Parametros.LARGURA_DA_COLUNA +
                                       qtdeColuna * Parametros.LARGURA_DA_COLUNA,
                                       Parametros.LARGURA_DA_LINHA +
                                       qtdeLinha * Parametros.LARGURA_DA_LINHA));

            //Desenha a régua horizontal
            for(int i = 0; i < qtdeColuna; i++)
                g2d.drawString("" + i, (i + 1) * Parametros.LARGURA_DA_COLUNA, 10);
            
            
            //Desenha a régua vertical
            for(int i = 0; i < qtdeLinha; i++)
                g2d.drawString("" + i, 0, (i + 1) * Parametros.LARGURA_DA_LINHA);
            
            //Atualiza o tamanho da área de desenho
            desenho.setPreferredSize(area);

            //Let the scroll pane know to update itself
            //and its scrollbars.
            desenho.revalidate();
        }

        /**
         * Desenha os nós com as informações
         * @param g2d
         * @param no
         */
        private void desenhaNos(Graphics2D g2d, No no){
            g2d.setPaint(conf.getCorNo());

            //
            int xC = (1 + no.getColuna()) * Parametros.LARGURA_DA_COLUNA + Parametros.FATOR_COL;
            int yC = (1 + no.getLinha()) * Parametros.LARGURA_DA_LINHA + Parametros.FATOR_LIN;
            //
            //tamanho da informação guardada no Nó
            int altura = 30;
            int largura = (String.valueOf(no.getInfo()).length() * 30)/30 + 30;
            //int largura = 30;

            g2d.fillOval(xC - (largura/2), yC - (altura/2), largura, altura);

            g2d.setPaint(conf.getCorInfo());
            g2d.drawString(""+ no.getInfo(), xC-(largura/4), yC + (altura/4) );
        }

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}