package snake;

//Importa��es necess�rias para rodar o jogo
import javax.swing.JFrame;


public class Snake extends JFrame
{

 // M�todo construtor da classe
 public Snake ()
 {
     // Adiciona o JPanel do jogo
     add(new Grade());

     // Define a saida da aplica��o
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     // Define o tamanho da janela

     setSize(820, 640);
     
     // A localiza��o
     setLocationRelativeTo(null);
     // O titulo da janela
     setTitle("Jogo da Cobrinha");

     // Impede o redimensionamento da janela
     setResizable(false);
     // Mostra a janela
     setVisible(true);
 }

 public void initializing(String[] args)
 {
     // Cria o novo JFrame
     new Snake();
 }

}
