package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import camera.ObjRecognitionController;
import main.PointerPosition;

public class Grade extends JPanel implements ActionListener
{

    // Tamanho do JPanel em Largura x Altura
    private final int WIDTH_ = 800;
    private final int HEIGHT_ = 600;

    // Tamanho de cada ponto na tela
    private final int TAMANHO_PONTO = 20;
    // Tamanho total de pontos, multiplicando a largura e altura
    private final int TODOS_PONTOS = 4800;

    // Um valor aleat�ria para gerar posi��o
    private final int RAND_POSICAO = 29;
    // Um delay para o tempo de execu��o do jogo
    private int DELAY = 200; //<---- Era private final int
    private boolean acelerar1 = false;
    private boolean acelerar2 = false;
    private boolean acelerar3 = false;

    // Defini��o do plano cartesiano (x,y) do jogo
    private int[] x = new int[TODOS_PONTOS];
    private int[] y = new int[TODOS_PONTOS];

    // Pontos da cobrinha
    private int pontos;
    // Posi��o (x,y) da comida
    private int comida_x;
    private int comida_y;

    // Contar pontua��o
    private int PONTUA��O = 0;
    // Mensagem da pontua��o
    String SCORE = "PONTUA��O: " + PONTUA��O;
    // Fonte para escrever a pontua��o, estilo da fonte
    Font SCORE_FONT = new Font("Consolas", Font.BOLD, 12);
    // Tamanho total da escrita na tela
    FontMetrics SCORE_METRICA = this.getFontMetrics(SCORE_FONT);

    // Defini��o dos movimentos
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    // Denifi��o do status do jogo
    private boolean estaJogando = true;

    // Tempo de execu��o do jogo
    private Timer tempo;

    // Imagens da cabe�a e corpo da cobrinha, e comida
    private Image bola;
    private Image comida;
    private Image cabe�a;

    // M�todo construtor da classe
    public Grade ()
    {
        initGame();
    }
    
    public void initGame()
    {

        // Seta o plano de fundo como preto
        setBackground(Color.BLACK);

        // Cria um icone do arquivo png e seta na imagem correspondente
        ImageIcon bola_ = new ImageIcon("bola.png");
        bola = bola_.getImage();

        // Cria um icone do arquivo png e seta na imagem correspondente
        ImageIcon comida_ = new ImageIcon("comida.png");
        comida = comida_.getImage();

        // Cria um icone do arquivo png e seta na imagem correspondente
        ImageIcon cabe�a_ = new ImageIcon("cabe�a.png");
        cabe�a = cabe�a_.getImage();

        // Define o foco para o JPanel
        setFocusable(true);
        // Define o tamanho da tela
        setSize(WIDTH_, HEIGHT_);

        // Inicializa do jogo
        initGameVariables();
    }
    
    // M�todo para inicializar as vari�veis o jogo
    public void initGameVariables()
    {
        // Define quantidade de pontos iniciais
        pontos = 3;
        PONTUA��O = 0;

        // Define a posi��o em (x,y) de cada ponto
        for (int i = 0; i < pontos; i++)
        {
            x[i] = 100 - i*10;
            y[i] = 100;
        }

        // Gera a primeira comida
        localComida();

        // Inicia o tempo de execu��o do jogo
        tempo = new Timer(DELAY, this);
        tempo.start();

    }
    
    

    // M�todo para desenhar elementos na tela do jogo
    @Override
    public void paint (Graphics g)
    {
        // Define o atribuito para a classe pr�pria
        super.paint(g);

        //verifica se a camera esta ativa
        if (ObjRecognitionController.cameraActive)
        {
	        // Analisa se o jogo esta em andamento, se estiver desenha na tela,
        	// se n�o estiver, o jogo � dado como o fim	
	        if (estaJogando)
	        {
	            // Desenha a comida no plano (x,y) do jogo
	            g.drawImage(comida, comida_x, comida_y, this);
	
	            switch(PONTUA��O){
	            case 5:
	            	if(!acelerar1) {
		            	tempo.setDelay(100);
		            	System.out.println("Delay 1");
		            	acelerar1 = true;
	            	}
	            	break;
	            case 10:
	            	if(!acelerar2) {
	            		tempo.setDelay(70);
	                	System.out.println("Delay 2");
	                	acelerar2 = true;
	            	}
	            	break;
	            case 15:
	            	if(!acelerar3){
	                	tempo.setDelay(50);
	                	System.out.println("Delay 3");
	                	acelerar3 = true;
	            	}
	            	break;
	            }
	
	            // Para cada ponto da cobrinha, desenha a cabe�a e o corpo
	            // em (x,y)
	            for (int i = 0; i < pontos; i++)
	            {
	                if (i == 0)
	                { g.drawImage(cabe�a, x[i], y[i], this); }
	                else
	                { g.drawImage(bola, x[i], y[i], this); }
	            }
	
	            // Desenha a pontua��o na tela
	            desenharPontuacao(g);
	
	            // Executa a sincronia de dados
	            Toolkit.getDefaultToolkit().sync();
	
	            // Pausa os gr�ficos
	            g.dispose();
	        }
	        else
	        {
	            // Executa o fim de jogo
	        	String msg = "FIM DE JOGO! Sua pontua��o: " + PONTUA��O;
	        	printOnScreen(g, msg);
	        }
        }
        else
        {
        	
        	String msg = "Por favor, inicie a camera";
        	printOnScreen(g, msg);
        	
        }
        
    }

    // M�todo para desenhar a pontua��o na tela
    public void desenharPontuacao (Graphics g)
    {
        // Define a frase para escrever
        SCORE = "PONTUA��O: " + PONTUA��O;
        // Define o tamanho da fonte
        SCORE_METRICA = this.getFontMetrics(SCORE_FONT);

        // Define a cor da fonte
        g.setColor(Color.white);
        // Seta a fonte para o gr�fico
        g.setFont(SCORE_FONT);
        // Desenha a fonte na tela
        g.drawString(SCORE, (WIDTH_ - SCORE_METRICA.stringWidth(SCORE)) - 10, HEIGHT_ - 10);
    }

    public void printOnScreen (Graphics g, String textToPrintOnscreen)
    {
        // Define o estilo da fonte
        Font pequena = new Font("Consolas", Font.BOLD, 14);
        // Define o tamanho da fonte
        FontMetrics metrica = this.getFontMetrics(pequena);

        // Define a cor da fonte
        g.setColor(Color.white);
        // Seta a fonte para o gr�fico
        g.setFont(pequena);
        // Desenha a fonte na tela
        g.drawString(textToPrintOnscreen, (WIDTH_ - metrica.stringWidth(textToPrintOnscreen)) / 2, HEIGHT_ / 2);
    }

    // M�todo para checar se a cobrinha comeu a comida
    public void checarComida ()
    {
        // Se ele comer na mesma posi��o (x,y) ent�o aumenta o corpo da cobrinha
        // aumenta a pontua��o e gera uma nova comida
        if ((x[0] == comida_x) && (y[0] == comida_y))
        {
            pontos++;
            PONTUA��O++;
            localComida();

        }
    }

    // M�todo para mover a cobrinha na tela
    public void mover ()
    {
    	
    	updatePosition();
    	
        // Para cada ponto da cobrinha desenha em (x,y)
        for (int i = pontos; i > 0; i--)
        {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        // Se for para esquerda decrementa em x
        if (left)
        {
            x[0] -= TAMANHO_PONTO;
        }

        // Se for para direita incrementa em x
        if (right)
        {
            x[0] += TAMANHO_PONTO;
        }

        // Se for para cima decrementa em y
        if (up)
        {
            y[0] -= TAMANHO_PONTO;
        }

        // Se for para baixo incrementa em y
        if (down)
        {
            y[0] += TAMANHO_PONTO;
        }

    }

    // M�todo para checar colis�o entre a cobrinha e as bordas do jogo
    public void checarColis�o ()
    {
        // Para cada ponto, verifica se este est� em posi��o com outro ponto
        // se estiver ele avista que o jogador parou de jogar devido a colis�o
        for (int i = pontos; i > 0; i--)
        {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i]))
            { estaJogando = false; }

        }

        // Verifica se a cabe�a da cobrinha encostou em algum ponto (x,y)
        // nas bordas (width,height) da tela
        if (y[0] > HEIGHT_)
        { estaJogando = false; }

        if (y[0] < 0)
        { estaJogando = false; }

        if (x[0] > WIDTH_)
        { estaJogando = false; }

        if (x[0] < 0)
        { estaJogando = false; }
    }

    // M�todo que gera uma comida na tela
    public void localComida ()
    {
        // Define um valor aleat�rio e atribui a uma posi��o x na tela para a
        // comida
        int random = (int) (Math.random() * RAND_POSICAO);
        comida_x = (random * TAMANHO_PONTO);

        // Define um valor aleat�rio e atribui a uma posi��o y na tela para a
        // comida
        random = (int) (Math.random() * RAND_POSICAO);
        comida_y = (random * TAMANHO_PONTO);
    }
    
    
    public void updatePosition()
    {
    	String key = PointerPosition.playerKey();
    	
    	if(key != null)
    	{
	    	if(key.equals("up") && !up)
	    	{
	    		up = false;
	    		down = true;
	            left = false;
	            right = false;
	    	}
	    	else if(key.equals("down") && !down)
	    	{
	    		up = true;
	    		down = false;
	            left = false;
	            right = false;
	    	}
	    	else if(key.equals("left") && !left)
	    	{
	    		up = false;
	    		down = false;
	            left = false;
	            right = true;
	    	}
	    	else if(key.equals("right") && !right)
	    	{
	    		up = false;
	    		down = false;
	            left = true;
	            right = false;
	    	}
    	}
    	
    }


    // M�todo de a��es durante a execu��o do jogo
    public void actionPerformed (ActionEvent e)
    {
        // Se estiver jogando ent�o j� realiza a checagem da comida, depois
        // verifica se existe colis�o, s� ent�o depois, realiza o movimento
        // da cobrinha no jogo, por fim, redesenha os resultados
        if (estaJogando)
        {
            checarComida();
            checarColis�o();
            mover();
        }
        
        repaint();

    }

    
    
    
}
