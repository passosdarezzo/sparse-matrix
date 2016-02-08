package com.passosdarezzo.configuracao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author passosdarezzo
 */
public class JanelaConfiguracao extends JFrame implements ActionListener{
    private JCheckBox pontilhado;
    private Container tela;
    private JButton bOk;
    private JButton bCancel;
    private Parametros param;
    private JButton lRegua;
    private JButton lLinhaH;
    private JButton lLinhaV;
    private JButton lNo;
    private JButton lInfo;
    private JPanel ref;
    private Color regua;
    private Color linhaH;
    private Color linhaV;
    private Color no;
    private Color info;
    private Border bordaCores = BorderFactory.createTitledBorder(
            BorderFactory.createLoweredBevelBorder(), "Cores Utilizadas no Programa");


    /**
     * Construtor da classe
     * @param param
     */
    public JanelaConfiguracao(Parametros param, JPanel ref){
        super("Configurações");
        tela = getContentPane();
        tela.setLayout(new BorderLayout());

        this.param = param;
        this.ref = ref;

        regua = param.getCorRegua();
        linhaH = param.getCorlinhaHorizontal();
        linhaV = param.getCorlinhaVertical();
        no = param.getCorNo();
        info = param.getCorInfo();
        initComponents();

        setSize(300, 300);
        setVisible(true);
    }

    /**
     * Inicia os componentes da Janela
     */
    public void initComponents(){
        //Objeto Cores
        lRegua = new JButton("Régua");
        lRegua.setBackground(param.getCorRegua());
        lRegua.setForeground(Color.WHITE);
        lRegua.addActionListener(this);

        lLinhaH = new JButton("Linha Horizontal");
        lLinhaH.setBackground(param.getCorlinhaHorizontal());
        lLinhaH.setForeground(Color.WHITE);
        lLinhaH.addActionListener(this);

        lLinhaV = new JButton("Linha Vertical");
        lLinhaV.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        lLinhaV.setForeground(Color.WHITE);
        lLinhaV.setBackground(param.getCorlinhaVertical());
        lLinhaV.addActionListener(this);

        lNo = new JButton("Nó");
        lNo.setForeground(Color.WHITE);
        lNo.setBackground(param.getCorNo());
        lNo.addActionListener(this);

        lInfo = new JButton("Informação");
        lInfo.setBackground(param.getCorInfo());
        lInfo.setForeground(Color.WHITE);
        lInfo.addActionListener(this);

        pontilhado = new JCheckBox("Utiliza linhas Pontilhadas", param.isBooPontilhado());

        //Botões
        bOk = new JButton("Ok");
        bOk.addActionListener((ActionListener)
                EventHandler.create(ActionListener.class, JanelaConfiguracao.this, "efetiva"));
        bCancel = new JButton("Cancelar");
        bCancel.addActionListener((ActionListener)
                EventHandler.create(ActionListener.class, JanelaConfiguracao.this, "sair"));

        JPanel painelCentral = new JPanel();
        painelCentral.setBorder(bordaCores);
        painelCentral.setLayout(new GridLayout(6, 1, 0, 8));
        //painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.add(lRegua);
        painelCentral.add(lLinhaH);
        painelCentral.add(lLinhaV);
        painelCentral.add(lNo);
        painelCentral.add(lInfo);
        painelCentral.add(pontilhado);

        JPanel painelInferior = new JPanel();
        painelInferior.add(bOk);
        painelInferior.add(bCancel);

        tela.add(painelCentral, BorderLayout.CENTER);
        tela.add(painelInferior, BorderLayout.SOUTH);
    }

    /**
     * Efetiva as alterações
     */
    public void efetiva(){
        param.setCorRegua(regua);
        param.setCorlinhaVertical(linhaV);
        param.setCorInfo(info);
        param.setCorlinhaHorizontal(linhaH);
        param.setCorNo(no);
        param.setBooPontilhado(pontilhado.isSelected());
        sair();
    }

    public void sair(){
        dispose();
        ref.revalidate();
        ref.repaint();
    }

    /**
     * Abre o seletor de cores e guarda a cor selecionada pelo usuário
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e){
        Color temp = JColorChooser.showDialog(this, "Seletor de Cores", param.getCorRegua());
        if(temp != null){
            if(e.getSource() == lRegua){
                regua = temp;
                lRegua.setBackground(temp);
            }
            if (e.getSource() == lLinhaH){
                linhaH = temp;
                lLinhaH.setBackground(temp);
            }
            if (e.getSource() == lLinhaV){
                linhaV = temp;
                lLinhaV.setBackground(temp);
            }
            if (e.getSource() == lNo){
                no = temp;
                lNo.setBackground(temp);
            }
            if (e.getSource() == lInfo){
                info = temp;
                lInfo.setBackground(temp);
            }
        }
    }

    /**
    public static void main(String [] args){
        JanelaConfiguracao app = new JanelaConfiguracao(new Parametros(), new JPanel());
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    */
}