package calculo_de_notas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class NormalizedGradeThread implements Runnable {

	private Socket cliente = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void run() {
		System.out.println("[PROCESSO] - Executando processamento do cliente " + cliente.getInetAddress().getHostAddress());

		try {
			DataInputStream dadosEntrada = new DataInputStream(getSocket().getInputStream());
			String entrada = dadosEntrada.readUTF();
			String[] notas = entrada.split(" ");
			Integer maior = Integer.valueOf(notas[0]);
			
			for (int i = 0; i < notas.length; i++) {
				 if(Integer.valueOf(notas[i])>maior){maior=Integer.valueOf(notas[i]);}
			}
			
			ArrayList notas_normalizadas = new ArrayList();
			
			for (int i = 0; i < notas.length; i++) {
				notas_normalizadas.add((Integer.valueOf(notas[i])*10)/maior);
			}
			
			DataOutputStream dadosSaida = new DataOutputStream(getSocket().getOutputStream());
			dadosSaida.writeUTF(notas_normalizadas.toString());
			getSocket().close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Socket getSocket() {
		return cliente;
	}

	public void setSocket(Socket socket) {
		this.cliente = socket;
	}

}
