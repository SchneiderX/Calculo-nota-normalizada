package calculo_de_notas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientSide {

	public static void main(String[] args) throws InterruptedException {
		Socket servidor = null;
		try {
			String mensagem = (String) JOptionPane.showInputDialog(null, "Escreva as notas em numeros inteiros separadas por espaço:",
					"Notas do Estudante", JOptionPane.PLAIN_MESSAGE, null, null, "escreva aqui");

			System.out.println("[CLIENT] - Conectando no servidor");
			servidor = new Socket("localhost", 1234);
			System.out.println("[CLIENT] - Conexão com sucesso !!!");

			DataOutputStream dataOutputStream = new DataOutputStream(servidor.getOutputStream());
			dataOutputStream.writeUTF(mensagem);
			System.out.println("[CLIENT] - Mensagem enviada: " + mensagem);

			DataInputStream dataInputStream = new DataInputStream(servidor.getInputStream());
			String dadosRecebidos = dataInputStream.readUTF();
			System.out.println("[CLIENT] - Mensagem recebida: " + dadosRecebidos);

			Thread.sleep(5000);

			servidor.close();
			System.out.println("[CLIENT] - Acabou ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				servidor.close();
			} catch (IOException e) {
			}
		}
	}

}
