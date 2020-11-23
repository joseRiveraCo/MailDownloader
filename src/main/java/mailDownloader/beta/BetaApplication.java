package mailDownloader.beta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

@SpringBootApplication
public class BetaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BetaApplication.class, args);
	}

	@Value("${pathdownload}")
	private String pathdownload;

	@Value("${Host}")
	private String Host;

	@Value("${user}")
	private String user;

	@Value("${pass}")
	private String pass;

	@Value("${folder}")
	private String folder;

	@Override
		public void run(String... args) throws Exception {
				Store store;

				analizaParteDeMensaje analizador = new analizaParteDeMensaje();

				Properties props = System.getProperties();
				props.setProperty("outlook.office365.com", "imaps");

				Session session = Session.getInstance(props);
				//session.setDebug(true);

				store = session.getStore("imaps");
				store.connect(Host, user, pass);
				Folder folder = store.getFolder(this.folder);

				folder.open(Folder.READ_ONLY);

				Message[] mensajes = folder.getMessages();

				// Se escribe from y subject de cada mensaje
				for (int i = 0; i < mensajes.length; i++) {
					System.out.println("From:" + mensajes[i].getFrom()[0].toString());
					System.out.println("Subject:" + mensajes[i].getSubject());

					// Se visualiza, si se sabe como, el contenido de cada mensaje
					analizador.analizaParteDeMensaje(mensajes[i], pathdownload);

				}

				folder.close(false);
				store.close();

			}
	}
