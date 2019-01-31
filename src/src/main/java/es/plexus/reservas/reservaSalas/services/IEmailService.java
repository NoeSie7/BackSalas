package es.plexus.reservas.reservaSalas.services;

public interface IEmailService {
	
	
	public boolean sendMail(String para, String texto, String asunto);
}
